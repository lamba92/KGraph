package com.github.lamba92.data

interface Graph<T, R : Comparable<R>> : Iterable<Node<T>> {

    val nodes: Iterator<Node<T>>
    val edges: Iterator<Edge<T, R>>

    fun getEdgesFrom(node: Node<T>): List<Edge<T, R>>

    operator fun contains(node: Node<T>): Boolean
    operator fun contains(edge: Edge<T, R>): Boolean

}

interface MutableGraph<T, R : Comparable<R>> : Graph<T, R> {

    fun addNode(node: Node<T>): Boolean
    fun addEdge(edge: Edge<T, R>): Boolean
    fun removeNode(node: Node<T>): Boolean
    fun removeEdge(edge: Edge<T, R>): Boolean

}

data class Edge<T, R : Comparable<R>>(
    val initialNode: Node<T>,
    val arrivalNode: Node<T>,
    val cost: R
)

data class Node<T>(val content: T)

operator fun <T, R : Comparable<R>> Edge<T, R>.contains(node: Node<T>) =
    node == initialNode || node == arrivalNode

@BuilderInference
fun <T, R : Comparable<R>> buildGraph(action: MutableGraph<T, R>.() -> Unit): Graph<T, R> =
    HashMapGraph<T, R>().apply(action)

fun <T, R : Comparable<R>> MutableGraph<T, R>.node(value: T): Node<T> =
    Node(value).also { addNode(it) }

fun <T, R : Comparable<R>> MutableGraph<T, R>.edge(initialNode: Node<T>, arrivalNode: Node<T>, cost: R): Edge<T, R> =
    Edge(initialNode, arrivalNode, cost).also { addEdge(it) }

fun <T, R : Comparable<R>> MutableGraph<T, R>.bidirectionalEdge(
    initialNode: Node<T>,
    arrivalNode: Node<T>,
    cost: R,
    returnCost: R = cost
): Pair<Edge<T, R>, Edge<T, R>> =
    Edge(initialNode, arrivalNode, cost).also { addEdge(it) } to Edge(arrivalNode, initialNode, returnCost).also { addEdge(it) }
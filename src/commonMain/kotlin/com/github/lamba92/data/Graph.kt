package com.github.lamba92.data

interface Graph<T, R : Comparable<R>> : Iterable<Node<T>> {

    val nodes: Set<Node<T>>
    val edges: Set<Edge<T, R>>

    fun getEdgesFrom(node: Node<T>): Set<Edge<T, R>>
    fun getEdgeBetweenNodes(from: Node<T>, to: Node<T>): Set<Edge<T, R>>

    operator fun contains(node: Node<T>): Boolean
    operator fun contains(edge: Edge<T, R>): Boolean

}

interface MutableGraph<T, R : Comparable<R>> : Graph<T, R> {

    fun addEdge(n1: Node<T>, n2: Node<T>, cost: R): Edge<T, R>
    fun addBidirectionalEdge(
        n1: Node<T>,
        n2: Node<T>,
        costN1toN2: R,
        costN2toN1: R = costN1toN2
    ): Pair<Edge<T, R>, Edge<T, R>>

    fun removeNode(node: Node<T>): Boolean
    fun addNode(value: T): Node<T>
    fun removeEdge(edge: Edge<T, R>): Boolean

}

data class Edge<T, R : Comparable<R>>(
    val initialNode: Node<T>,
    val arrivalNode: Node<T>,
    val cost: R
) {
    operator fun contains(node: Node<T>) =
        node == initialNode || node == arrivalNode
}

data class Node<T>(val content: T) {
    fun <R : Comparable<R>> Graph<T, R>.getEdges() =
        getEdgesFrom(this@Node)
}

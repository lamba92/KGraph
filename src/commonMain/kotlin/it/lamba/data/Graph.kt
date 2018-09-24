package it.lamba.data

import it.lamba.kgraph.KGraphNode
import it.lamba.utils.randomString
import kotlin.random.Random

interface Graph<T> {

    fun addEdge(n1: Node<T>, n2: Node<T>, cost: Double): Edge<T>
    fun addBidirectionalEdge(n1: Node<T>, n2: Node<T>, costN1toN2: Double, costN2toN1: Double = costN1toN2): Pair<Edge<T>, Edge<T>>
    fun removeNode(id: String): Node<T>?
    fun removeNode(node: Node<T>) = removeNode(node.id)
    fun addNode(value: T, id: String = Random.randomString()): KGraphNode<T>
    fun removeEdge(id: String): Edge<T>?
    fun removeEdge(edge: Edge<T>) = removeEdge(edge.id)
    fun removeEdgeIf(function: (id: String, edge: Edge<T>)-> Boolean)
    fun removeNodeIf(function: (id: String, node: Node<T>) -> Boolean)
    fun getNodes(): Collection<Node<T>>
    fun getEdges(): Collection<Edge<T>>
    fun getNode(id: String): Node<T>?
    fun getEdge(from: Node<T>, to: Node<T>): Collection<Edge<T>>

    operator fun contains(node: Node<T>?): Boolean
    operator fun contains(id: String): Boolean
    operator fun contains(edge: Edge<T>?): Boolean
}
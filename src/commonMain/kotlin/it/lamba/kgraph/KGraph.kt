package it.lamba.kgraph

import it.lamba.data.Edge
import it.lamba.data.Graph
import it.lamba.data.Node
import it.lamba.utils.removeIf

class KGraph<T> : Graph<T> {

    private val nodes = HashMap<String, Node<T>>()
    private val edges = HashMap<String, Edge<T>>()

    override fun removeNode(id: String): Node<T>? {
        removeEdgeIf { _, edge -> nodes[id] in edge }
        return nodes.remove(id)
    }

    override fun removeEdge(id: String) = edges.remove(id)

    override fun removeNodeIf(function: (id: String, node: Node<T>) -> Boolean)
            = nodes.entries.removeIf(function)

    override fun removeEdgeIf(function: (id: String, edge: Edge<T>) -> Boolean)
            = edges.entries.removeIf(function)

    override operator fun contains(node: Node<T>?) = node in nodes.values

    override operator fun contains(edge: Edge<T>?) = edge in edges.values

    override operator fun contains(id: String) = id in nodes.keys || id in edges.keys

    override fun addNode(value: T, id: String) = KGraphNode<T>(value, id).apply { nodes[this.id] = this }

    override fun addEdge(n1: Node<T>, n2: Node<T>, cost: Double): Edge<T> {
        if(n1 !in this || n2 !in this) throw IllegalArgumentException("One of the nodes is not inside the graph.")
        return KGraphEdge<T>(n1, n2, cost).apply { edges[this.id] = this }
    }

    override fun addBidirectionalEdge(n1: Node<T>, n2: Node<T>, costN1toN2: Double, costN2toN1: Double)
            = Pair(addEdge(n1, n2, costN1toN2), addEdge(n2, n1, costN2toN1))

    override fun getNode(id: String) = nodes[id]

    override fun getEdge(from: Node<T>, to: Node<T>) = edges.values.filter { it.initialNode == from && it.arrivalNode == to }

    override fun getNodes(): Collection<Node<T>> = nodes.values.toList()

    override fun getEdges(): Collection<Edge<T>> = edges.values.toList()


}
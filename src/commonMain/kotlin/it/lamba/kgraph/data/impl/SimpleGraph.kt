package it.lamba.kgraph.data.impl

import it.lamba.kgraph.data.Edge
import it.lamba.kgraph.data.MutableGraph
import it.lamba.kgraph.data.Node
import it.lamba.utils.removeIf

class SimpleGraph(val allowNodeOverride: Boolean = false) : MutableGraph {

    override fun iterator() = nodes.values.iterator()

    private val nodes = HashMap<String, Node>()
    private val edges = HashMap<String, Edge>()
    override fun removeNode(id: String): Node? {
        removeEdgeIf { _, edge -> nodes[id] in edge }
        return nodes.remove(id)
    }
    override fun removeEdge(id: String) = edges.remove(id)
    override fun removeNodeIf(function: (id: String, node: Node) -> Boolean)
            = nodes.entries.removeIf(function)
    override fun removeEdgeIf(function: (id: String, edge: Edge) -> Boolean)
            = edges.entries.removeIf(function)
    override operator fun contains(node: Node?) = node in nodes.values

    override operator fun contains(edge: Edge?) = edge in edges.values

    override operator fun contains(id: String) = id in nodes.keys || id in edges.keys

    override fun addNode(value: Any?, id: String) = SimpleNode(id, value).apply {
        if(nodes.containsKey(id)) throw IllegalArgumentException("Node with $id already exists")
        nodes[this.id] = this
    }

    override fun addEdge(n1: Node, n2: Node, cost: Double): Edge {
        if(n1 !in this || n2 !in this) throw IllegalArgumentException("One of the nodes is not inside the graph.")
        return SimpleEdge(n1, n2, cost).apply { edges[this.id] = this }
    }

    override fun addBidirectionalEdge(n1: Node, n2: Node, costN1toN2: Double, costN2toN1: Double)
            = Pair(addEdge(n1, n2, costN1toN2), addEdge(n2, n1, costN2toN1))

    override fun getNode(id: String) = nodes[id]

    override fun getEdge(from: Node, to: Node) = edges.values.filter { it.initialNode == from && it.arrivalNode == to }

    override fun getNodes(): Collection<Node> = nodes.values.toList()

    override fun getEdges(): Collection<Edge> = edges.values.toList()

    override fun getEdgesFrom(node: Node) = edges.values.filter { it.initialNode == node }
}

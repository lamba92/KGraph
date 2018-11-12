@file:Suppress("MemberVisibilityCanBePrivate")

package it.lamba.kgraph.data.impl

import it.lamba.kgraph.data.Edge
import it.lamba.kgraph.data.MutableGraph
import it.lamba.kgraph.data.Node
import it.lamba.utils.removeIf

class SimpleGraph(val allowNodeOverride: Boolean = false) : MutableGraph {

    private val nodesMap = HashMap<String, Node>()

    private val edgesMap = HashMap<String, Edge>()

    override fun iterator() = nodesMap.values.iterator()
    override val nodes: Collection<Node>
        get() = nodesMap.values.toList()
    override val edges: Collection<Edge>
        get() = edgesMap.values.toList()

    override fun removeNode(id: String): Node? {
        removeEdgeIf { _, edge -> nodesMap[id] in edge }
        return nodesMap.remove(id)
    }

    override fun removeEdge(id: String) = edgesMap.remove(id)

    override fun removeNodeIf(function: (id: String, node: Node) -> Boolean) = nodesMap.entries.removeIf(function)

    override fun removeEdgeIf(function: (id: String, edge: Edge) -> Boolean) = edgesMap.entries.removeIf(function)

    override operator fun contains(node: Node?) = node in nodesMap.values

    override operator fun contains(edge: Edge?) = edge in edgesMap.values

    override operator fun contains(id: String) = id in nodesMap.keys || id in edgesMap.keys

    override fun addNode(value: Any?, id: String) = SimpleNode(id, value).apply {
        if (!allowNodeOverride && nodesMap.containsKey(id)) throw IllegalArgumentException("Node with $id already exists")
        nodesMap[this.id] = this
    }

    override fun addEdge(n1: Node, n2: Node, cost: Double): Edge {
        if (n1 !in this || n2 !in this) throw IllegalArgumentException("One of the nodesMap is not inside the graph.")
        return SimpleEdge(n1, n2, cost).apply { edgesMap[this.id] = this }
    }

    override fun addBidirectionalEdge(n1: Node, n2: Node, costN1toN2: Double, costN2toN1: Double) =
        Pair(addEdge(n1, n2, costN1toN2), addEdge(n2, n1, costN2toN1))

    override fun getNodeById(id: String) = nodesMap[id]

    override fun getEdgeByNodes(from: Node, to: Node) =
        edgesMap.values.filter { it.initialNode == from && it.arrivalNode == to }

    override fun getEdgesFrom(node: Node) = edgesMap.values.filter { it.initialNode == node }
}

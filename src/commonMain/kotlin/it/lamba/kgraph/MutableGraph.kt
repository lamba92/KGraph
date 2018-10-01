package it.lamba.kgraph

import it.lamba.kgraph.impl.SimpleNode
import it.lamba.utils.randomString
import kotlin.random.Random

interface MutableGraph: Graph {
    fun addEdge(n1: Node, n2: Node, cost: Double): Edge
    fun addBidirectionalEdge(n1: Node, n2: Node, costN1toN2: Double, costN2toN1: Double = costN1toN2): Pair<Edge, Edge>
    fun removeNode(id: String): Node?
    fun removeNode(node: Node) = removeNode(node.id)
    fun addNode(value: Any, id: String = Random.randomString()): SimpleNode
    fun removeEdge(id: String): Edge?
    fun removeEdge(edge: Edge) = removeEdge(edge.id)
    fun removeEdgeIf(function: (id: String, edge: Edge)-> Boolean)
    fun removeNodeIf(function: (id: String, node: Node) -> Boolean)
}
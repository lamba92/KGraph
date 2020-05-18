package com.github.lamba92.data

import com.github.lamba92.data.impl.SimpleNode

interface MutableGraph: Graph {

    fun addEdge(n1: Node, n2: Node, cost: Double): Edge
    fun addBidirectionalEdge(n1: Node, n2: Node, costN1toN2: Double, costN2toN1: Double = costN1toN2): Pair<Edge, Edge>
    fun removeNode(node: Node): Boolean
    fun addNode(value: Any?, id: String): SimpleNode
    fun removeEdge(id: String): Edge?
    fun removeEdge(edge: Edge) = removeEdge(edge.id)

}

fun MutableGraph.removeEdgeIf(function: (edge: Edge)-> Boolean) =

fun MutableGraph.removeNodeIf(function: (id: String, node: Node) -> Boolean)

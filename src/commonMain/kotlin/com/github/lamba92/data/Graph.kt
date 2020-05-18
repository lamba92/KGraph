package com.github.lamba92.data

interface Graph: Iterable<Node> {

    val nodes: Set<Node>
    val edges: Set<Edge>

    fun getEdgesFrom(node: Node): Set<Edge>
    fun getNodeById(id: String): Node?
    fun getEdgeByNodes(from: Node, to: Node): Set<Edge>

    operator fun contains(node: Node?): Boolean
    operator fun contains(id: String): Boolean
    operator fun contains(edge: Edge?): Boolean

}

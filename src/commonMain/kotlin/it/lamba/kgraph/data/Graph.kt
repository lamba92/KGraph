package it.lamba.kgraph.data

interface Graph: Iterable<Node> {

    val nodes: Collection<Node>
    val edges: Collection<Edge>
    fun getEdgesFrom(node: Node): Collection<Edge>
    fun getNodeById(id: String): Node?
    fun getEdgeByNodes(from: Node, to: Node): Collection<Edge>

    operator fun contains(node: Node?): Boolean
    operator fun contains(id: String): Boolean
    operator fun contains(edge: Edge?): Boolean

}
package it.lamba.kgraph.data

interface Graph: Iterable<Node> {

    fun getNodes(): Collection<Node>
    fun getEdges(): Collection<Edge>
    fun getEdgesFrom(node: Node): Collection<Edge>
    fun getNode(id: String): Node?
    fun getEdge(from: Node, to: Node): Collection<Edge>

    operator fun contains(node: Node?): Boolean
    operator fun contains(id: String): Boolean
    operator fun contains(edge: Edge?): Boolean

}
package it.lamba.kgraph.data

interface Node {
    val id: String
    fun <T> getValue(): T?
    fun setValue(value: Any)
    fun getEdges(graph: Graph) = graph.getEdgesFrom(this)
    operator fun contains(value: Any?) = value == getValue()
}
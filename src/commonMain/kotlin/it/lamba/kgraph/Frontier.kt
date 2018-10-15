package it.lamba.kgraph

interface Frontier: MutableMap<Node, Frontier.Element> {

    fun next(): Element
    fun hasNext(): Boolean

    interface Element {
        val node: Node
        val depth: Int
        val parentNode: Node
        val edgeBetween: Edge
        val costUntilHere: Double
        val heuristic: Double
    }
}

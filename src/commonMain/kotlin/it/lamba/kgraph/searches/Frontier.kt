package it.lamba.kgraph.searches

import it.lamba.kgraph.data.Edge
import it.lamba.kgraph.data.Node

interface Frontier: MutableMap<Node, Frontier.Element> {

    val nextElementEvaluator: (Element) -> Double

    fun next(): Element
    fun hasNext(): Boolean

    interface Element {
        val node: Node
        val depth: Int
        val path: ArrayList<Edge>
        val costUntilHere: Double
        val parentNode: Node?
        val heuristic: Double?
    }
}

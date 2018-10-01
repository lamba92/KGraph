package it.lamba.kgraph
import it.lamba.utils.Queue

interface Frontier: Queue<Frontier.Element> {

    interface Element {
        val node: Node
        val depth: Int
        val parentNode: Node
        val edgeBetween: Edge
        val costUntilHere: Double
        val heuristic: Double
    }
}

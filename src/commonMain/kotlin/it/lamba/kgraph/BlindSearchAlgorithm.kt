package it.lamba.kgraph

interface Algorithm {
    val graph: Graph
    fun compute(): Result

    interface Result {
        val successful: Boolean
        val timeElapsed: Long
    }
}

interface BlindSearchAlgorithm: Algorithm {

    val initialNode: Node
    val targetValue: Any
    override fun compute(): SearchResult

    interface SearchResult: Algorithm.Result {
        val path: ArrayList<Edge>
        val visitedNodes: Set<Node>
        val pathCost: Double
    }
}

interface SearchAlgorithm: BlindSearchAlgorithm{
    val heuristic: (initialNode: Node, targetValue: Any, graph: Graph) -> Double
}


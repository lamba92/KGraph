package it.lamba.kgraph.searches

import it.lamba.kgraph.data.Edge
import it.lamba.kgraph.data.Graph
import it.lamba.kgraph.data.Node

interface Algorithm {
    val graph: Graph
    fun compute(): Pair<Result, Long>

    interface Result {
        val successful: Boolean
    }
}

interface BlindSearchAlgorithm: Algorithm {

    val initialNode: Node
    val targetValue: Any?
    override fun compute(): Pair<SearchResult, Long>

    interface SearchResult: Algorithm.Result {
        val path: List<Edge>
        val visitedNodes: Set<Node>
        val pathCost: Double
    }
}

interface SearchAlgorithm: BlindSearchAlgorithm {
    val heuristic: (initialNode: Node, targetValue: Any, graph: Graph) -> Double
}


package it.lamba.kgraph.searches.impl

import it.lamba.kgraph.data.Edge
import it.lamba.kgraph.data.Node
import it.lamba.kgraph.searches.BlindSearchAlgorithm
import it.lamba.utils.appendTabbedln
import it.lamba.utils.appendln

data class KBlindSearchResult(
    override val successful: Boolean,
    override val path: List<Edge>,
    override val visitedNodes: Set<Node>,
    override val pathCost: Double
) : BlindSearchAlgorithm.SearchResult{

    override fun toString() = buildString {
        appendln("Search results: ")
        appendTabbedln("successful: $successful")
        appendTabbedln("path: $path")
        appendTabbedln("visited nodes: $visitedNodes")
        appendTabbedln("path cost: $pathCost")
    }
}
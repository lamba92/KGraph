package it.lamba.kgraph.searches.impl

import it.lamba.kgraph.data.Edge
import it.lamba.kgraph.data.Node
import it.lamba.kgraph.searches.BlindSearchAlgorithm

data class KBlindSearchResult(
    override val successful: Boolean,
    override val path: List<Edge>,
    override val visitedNodes: Set<Node>,
    override val pathCost: Double
) : BlindSearchAlgorithm.SearchResult
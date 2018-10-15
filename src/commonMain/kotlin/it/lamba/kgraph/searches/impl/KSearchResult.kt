package it.lamba.kgraph.searches.impl

import it.lamba.kgraph.data.Edge
import it.lamba.kgraph.data.Node
import it.lamba.kgraph.searches.BlindSearchAlgorithm

data class KSearchResult(
    override val successful: Boolean,
    override val timeElapsed: Long,
    override val path: ArrayList<Edge>,
    override val visitedNodes: Set<Node>,
    override val pathCost: Double
) : BlindSearchAlgorithm.SearchResult
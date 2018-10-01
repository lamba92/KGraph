package it.lamba.kgraph.impl

import it.lamba.kgraph.Graph
import it.lamba.kgraph.Node
import it.lamba.kgraph.BlindSearchAlgorithm
import it.lamba.kgraph.Edge

class BreathSearch(override val initialNode: Node,
                   override val targetValue: Any,
                   override val graph: Graph
    ) : BlindSearchAlgorithm {

    private val frontier = ArrayList<Node>().apply { add(initialNode) }

    override fun compute(): BlindSearchAlgorithm.SearchResult {
        TODO()
    }

}
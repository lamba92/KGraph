package it.lamba.kgraph.impl

import it.lamba.kgraph.*

abstract class AbstractSearch(override val graph: Graph,
                        override val initialNode: Node,
                        override val targetValue: Any)
    : BlindSearchAlgorithm {


    fun expandNode(node: Node)
            = graph.getEdgesFrom(node).map { Pair(it, it.arrivalNode) }


}
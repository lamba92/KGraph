package it.lamba.kgraph.searches.impl

import it.lamba.kgraph.data.*
import it.lamba.kgraph.data.impl.KFrontier
import it.lamba.kgraph.searches.BlindSearchAlgorithm
import it.lamba.kgraph.searches.Frontier
import it.lamba.utils.getCurrentTimeInMillis
import it.lamba.utils.sumBy

abstract class AbstractBlindSearchAlgorithm(
    override val graph: Graph,
    override val initialNode: Node,
    override val targetValue: Any?
) : BlindSearchAlgorithm {

    abstract val frontier: KFrontier
    private var startTime: Long = 0

    override fun compute(): BlindSearchAlgorithm.SearchResult {
        frontier[initialNode] = KFrontier.KElement(
            initialNode,
            0,
            ArrayList(),
            0.0
        )
        startTime = getCurrentTimeInMillis()
        return iterate(frontier.next())
    }

    private fun iterate(element: Frontier.Element): BlindSearchAlgorithm.SearchResult {

        if(element.node.getValue<Any?>() == targetValue){
            val elapsed = startTime - getCurrentTimeInMillis()
            val nodes = element.path
                .map { it.initialNode }
                .toMutableSet()
                .apply { add(element.node) }
            val cost = element.path
                .sumBy { it.cost }
            return KSearchResult(true, elapsed, element.path, nodes, cost)
        }
        graph.getEdgesFrom(element.node).forEach {
            frontier[it.arrivalNode] = KFrontier.KElement(
                it.arrivalNode,
                element.depth + 1,
                ArrayList(element.path).apply { add(it) },
                element.costUntilHere + it.cost,
                element.node
            )
        }
        return if(frontier.hasNext()) iterate(frontier.next())
            else
            KSearchResult(
                false,
                startTime - getCurrentTimeInMillis(),
                ArrayList(), HashSet(),
                0.0
            )
    }
}

class BreathWidthSearch(graph: Graph, initialNode: Node, targetValue: Any?) :
    AbstractBlindSearchAlgorithm(graph, initialNode, targetValue){

    override val frontier = KFrontier { -it.depth.toDouble() }

}

class DepthWidthSearch(graph: Graph, initialNode: Node, targetValue: Any?) :
    AbstractBlindSearchAlgorithm(graph, initialNode, targetValue){

    override val frontier = KFrontier { it.depth.toDouble() }

}
package it.lamba.kgraph.searches.impl

import it.lamba.kgraph.data.Edge
import it.lamba.kgraph.data.Graph
import it.lamba.kgraph.data.Node
import it.lamba.kgraph.searches.BlindSearchAlgorithm
import it.lamba.utils.measureTimeMillis
import it.lamba.utils.sumBy

class DepthFirstSearch(
    override val graph: Graph,
    override val initialNode: Node,
    override val targetValue: Any?
) : BlindSearchAlgorithm {

    private val visitedEdges by lazy { ArrayList<Edge>() }
    private val toReturnNodesNodes by lazy { HashSet<Node>() }
    private val visitedNodes by lazy { HashSet<Node>() }

    override fun compute(): Pair<KBlindSearchResult, Long> {
        clean()
        return measureTimeMillis {
            KBlindSearchResult(
                iterate(initialNode),
                visitedEdges.reversed(),
                toReturnNodesNodes.apply { add(initialNode) },
                visitedEdges.sumBy { it.cost })
        }
    }

    private fun clean(){
        visitedNodes.clear()
        toReturnNodesNodes.clear()
        visitedNodes.clear()
    }

    private fun iterate(currentNode: Node): Boolean {
        visitedNodes.add(currentNode)
        if(targetValue in currentNode)
            return true
        for(edge in currentNode.getEdges(graph)){
            if (edge.arrivalNode !in visitedNodes && iterate(edge.arrivalNode)){
                visitedEdges.add(edge)
                toReturnNodesNodes.add(edge.arrivalNode)
                return true
            }
        }
        return false
    }
}
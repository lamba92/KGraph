package com.github.lamba92.searches.impl

import com.github.lamba92.data.Edge
import com.github.lamba92.data.Graph
import com.github.lamba92.data.Node
import com.github.lamba92.searches.BlindSearchAlgorithm
import com.github.lamba92.utils.measureTimeMillis
import com.github.lamba92.utils.sumBy

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

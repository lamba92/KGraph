package com.github.lamba92.searches.impl

import com.github.lamba92.data.Edge
import com.github.lamba92.data.Graph
import com.github.lamba92.data.Node
import com.github.lamba92.searches.SearchAlgorithm
import com.github.lamba92.searches.SearchResult
import com.github.lamba92.utils.measureTimeMillis

class DepthFirstSearch<T, K : Comparable<K>, R : Comparable<R>>(
    override val graph: Graph<T, R>,
    override val initialNode: Node<T>,
    override val targetValue: Node<T>,
) : SearchAlgorithm<T, R> {

    override fun compute(): SearchResult<T, R> {
        val visitedEdges: MutableList<Edge<T, R>> =
            mutableListOf()

        val toReturnNodesNodes: MutableSet<Node<T>> =
            mutableSetOf()

        val visitedNodes: MutableSet<Node<T>> =
            mutableSetOf()

        fun iterate(currentNode: Node<T>): Boolean {
            visitedNodes.add(currentNode)
            if (targetValue == currentNode)
                return true
            graph.getEdgesFrom(currentNode).forEach { edge ->
                if (edge.arrivalNode !in visitedNodes && iterate(edge.arrivalNode)) {
                    visitedEdges.add(edge)
                    toReturnNodesNodes.add(edge.arrivalNode)
                    return true
                }
            }
            return false
        }
    }

}


//    override fun compute(): Pair<KBlindSearchResult, Long> {
//        clean()
//        return measureTimeMillis {
//            KBlindSearchResult(
//                iterate(initialNode),
//                visitedEdges.reversed(),
//                toReturnNodesNodes.apply { add(initialNode) },
//                visitedEdges.sumBy { it.cost })
//        }
//    }


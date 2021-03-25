package com.github.lamba92.searches.impl

import com.github.lamba92.data.Edge
import com.github.lamba92.data.Graph
import com.github.lamba92.data.Node
import com.github.lamba92.searches.Frontier
import com.github.lamba92.searches.FrontierElement
import com.github.lamba92.searches.SearchAlgorithm
import com.github.lamba92.searches.SearchResult

class DepthFirstSearch<T, R : Comparable<R>, K : Comparable<K>>(
    override val graph: Graph<T, R>,
    override val initialNode: Node<T>,
    override val targetNode: Node<T>,
) : SearchAlgorithm<T, R> {

    override fun compute(): SearchResult<T, R> {
        val visitedEdges: MutableList<Edge<T, R>> =
            mutableListOf()

        val toReturnNodesNodes: MutableSet<Node<T>> =
            mutableSetOf()

        val visitedNodes: MutableSet<Node<T>> =
            mutableSetOf()

        var currentNode = initialNode

        while (currentNode != targetNode) {

        }

        fun iterate(currentNode: Node<T>): Boolean {
            visitedNodes.add(currentNode)
            if (targetNode == currentNode)
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

    private fun frontierIterator() = object : Frontier<T, R, K> {

        val currentFrontier = mutableSetOf<Frontier.Element<T, R, K>>(
            Frontier.Element(initialNode, 0, emptyList(), null, null, null)
        )

        val visited = mutableSetOf<Node<T>>()

        override val duplicateElementBehaviour
            get() = Frontier.DuplicationBehaviour.KEEP_LOWER_COST

        override fun hasNext() =
            currentFrontier.isNotEmpty()

        override fun next(): Frontier.Element<T, R, K>  =
            currentFrontier.maxByOrNull { it.depth }
                ?.also { extractedElement ->
                    graph.getEdgesFrom(extractedElement.node).forEach { edge ->
                        
                    }
                }
                ?: error("The frontier is empty")

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


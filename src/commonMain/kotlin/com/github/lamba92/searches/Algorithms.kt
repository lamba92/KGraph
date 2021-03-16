package com.github.lamba92.searches

import com.github.lamba92.data.Edge
import com.github.lamba92.data.Graph
import com.github.lamba92.data.Node
import kotlin.time.Duration

interface Algorithm<T, R : Comparable<R>> {

    val graph: Graph<T, R>
    fun compute(): Result

    interface Result {
        val successful: Boolean
        val timeElapsed: Duration
    }

}

interface SearchAlgorithm<T, R : Comparable<R>> : Algorithm<T, R> {

    val initialNode: Node<T>
    val targetValue: Node<T>

    override fun compute(): SearchResult<T, R>

}

interface WithHeuristicSearchAlgorithm<T, R : Comparable<R>, K : Comparable<K>> : Algorithm<T, R> {
    val heuristicEvaluator: (Node<T>, Node<T>) -> K
}

data class SearchResult<T, R: Comparable<R>>(
    val path: List<Edge<T, R>>,
    val visitedNodes: Set<Node<T>>,
    val pathCost: R,
    override val successful: Boolean,
    override val timeElapsed: Duration
) : Algorithm.Result
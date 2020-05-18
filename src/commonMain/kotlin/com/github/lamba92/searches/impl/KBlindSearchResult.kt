package com.github.lamba92.searches.impl

import com.github.lamba92.data.Edge
import com.github.lamba92.data.Node
import com.github.lamba92.searches.BlindSearchAlgorithm
import com.github.lamba92.utils.appendTabbedln
import com.github.lamba92.utils.appendln

data class KBlindSearchResult(
    override val successful: Boolean,
    override val path: List<Edge>,
    override val visitedNodes: Set<Node>,
    override val pathCost: Double
) : BlindSearchAlgorithm.SearchResult{

    override fun toString() = buildString {
        appendln("Search results: ")
        appendTabbedln("successful: $successful")
        appendTabbedln("path: $path")
        appendTabbedln("visited nodes: $visitedNodes")
        appendTabbedln("path cost: $pathCost")
    }
}

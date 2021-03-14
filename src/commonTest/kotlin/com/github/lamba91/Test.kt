package com.github.lamba91

import com.github.lamba92.data.Node
import com.github.lamba92.data.impl.SetGraph
import com.github.lamba92.data.impl.dsl.graphBuilder
import com.github.lamba92.searches.impl.DepthFirstSearch
import kotlin.test.Test
import kotlin.test.assertEquals

class KGraphTests{

    @Test
    fun graphGeneration(){
        val graph = buildTestGraph()
        assertEquals(graph.nodes.size, 10)
        assertEquals(graph.edges.size, 18)
    }

    @Test
    fun blindSearch(){
        val graph = buildTestGraph()
        val search = DepthFirstSearch(graph, graph.nodes.first(), 7)
        val (result, timeElapsed) = search.compute()
        println("search time: $timeElapsed\n$result")
        assertEquals(result.successful, true)
    }

    private fun buildTestGraph() = graphBuilder {
        val nodes = ArrayList<Node>()
        (0..9).forEach { nodes.add(generateTestNode(it)) }
        addBidirectionalEdge(nodes[0], nodes[2], 1.0)
        addBidirectionalEdge(nodes[0], nodes[1], 2.0)
        addBidirectionalEdge(nodes[1], nodes[3], 3.0)
        addBidirectionalEdge(nodes[2], nodes[3], 4.0)
        addBidirectionalEdge(nodes[3], nodes[5], 5.0)
        addBidirectionalEdge(nodes[3], nodes[4], 6.0)
        addBidirectionalEdge(nodes[4], nodes[6], 7.0)
        addBidirectionalEdge(nodes[4], nodes[7], 8.0)
        addBidirectionalEdge(nodes[5], nodes[6], 9.0)
    }

}

fun SetGraph.generateTestNode(x: Int) = node {
    id = "$x"
    value = x
}

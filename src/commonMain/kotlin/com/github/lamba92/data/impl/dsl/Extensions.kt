package com.github.lamba92.data.impl.dsl

import com.github.lamba92.data.impl.SimpleGraph

fun graphBuilder(allowNodeOverride: Boolean = false, block: SimpleGraph.() -> Unit)
        = SimpleGraph(allowNodeOverride).apply(block)

fun SimpleGraph.node(block: NodeConfiguration.() -> Unit)
        = NodeConfiguration().apply(block).let { addNode(it.value, it.id) }

fun SimpleGraph.edge(block: EdgeConfiguration.() -> Unit)
        = EdgeConfiguration().apply(block).let { addEdge(it.initialNode!!, it.arrivalNode!!, it.cost!!) }

fun SimpleGraph.bidirectionEdge(block: EdgeConfiguration.() -> Unit)
        = EdgeConfiguration().apply(block).let { addBidirectionalEdge(it.initialNode!!, it.arrivalNode!!, it.cost!!) }

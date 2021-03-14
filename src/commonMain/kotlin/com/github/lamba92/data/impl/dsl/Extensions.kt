package com.github.lamba92.data.impl.dsl

import com.github.lamba92.data.impl.SetGraph

fun graphBuilder(allowNodeOverride: Boolean = false, block: SetGraph.() -> Unit)
        = SetGraph(allowNodeOverride).apply(block)

fun SetGraph.node(block: NodeConfiguration.() -> Unit)
        = NodeConfiguration().apply(block).let { addNode(it.value, it.id) }

fun SetGraph.edge(block: EdgeConfiguration.() -> Unit)
        = EdgeConfiguration().apply(block).let { addEdge(it.initialNode!!, it.arrivalNode!!, it.cost!!) }

fun SetGraph.bidirectionEdge(block: EdgeConfiguration.() -> Unit)
        = EdgeConfiguration().apply(block).let { addBidirectionalEdge(it.initialNode!!, it.arrivalNode!!, it.cost!!) }

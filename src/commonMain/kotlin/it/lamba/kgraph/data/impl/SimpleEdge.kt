package it.lamba.kgraph.data.impl

import it.lamba.kgraph.data.Edge
import it.lamba.kgraph.data.Node
import it.lamba.utils.randomString
import kotlin.random.Random

data class SimpleEdge(override val initialNode: Node, override val arrivalNode: Node,
                      override val cost: Double,
                      override val id: String = Random.randomString()): Edge {
    override fun contains(node: Node?) = node == initialNode || node == arrivalNode
}

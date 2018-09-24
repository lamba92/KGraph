package it.lamba.kgraph

import it.lamba.data.Edge
import it.lamba.data.Node
import it.lamba.utils.randomString
import kotlin.random.Random

data class KGraphEdge<T>(override val initialNode: Node<T>, override val arrivalNode: Node<T>,
                         override val cost: Double,
                         override val id: String = Random.randomString()): Edge<T> {
    override fun contains(node: Node<T>?) = node == initialNode || node == arrivalNode
}

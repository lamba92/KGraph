package com.github.lamba92.data.impl

import com.github.lamba92.data.Edge
import com.github.lamba92.data.Node
import com.github.lamba92.utils.randomString
import kotlin.random.Random

data class SimpleEdge(
    override val initialNode: Node, override val arrivalNode: Node,
    override val cost: Double,
    override val id: String = Random.randomString()
) : Edge {
    override fun contains(node: Node?) = node == initialNode || node == arrivalNode
    override fun toString() = "${initialNode.id} --$cost-> ${arrivalNode.id}"
}

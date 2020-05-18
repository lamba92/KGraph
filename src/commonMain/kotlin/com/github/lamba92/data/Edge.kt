package com.github.lamba92.data

interface Edge {

    val initialNode: Node
    val arrivalNode: Node

    val cost: Double

    operator fun contains(node: Node?): Boolean

    operator fun component1() = initialNode
    operator fun component2() = arrivalNode

}

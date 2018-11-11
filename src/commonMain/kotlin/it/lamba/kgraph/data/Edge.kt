package it.lamba.kgraph.data

interface Edge {
    val initialNode: Node
    val arrivalNode: Node
    val cost: Double
    val id: String
    operator fun contains(node: Node?): Boolean
    operator fun component1(): Node
    operator fun component2(): Node
}
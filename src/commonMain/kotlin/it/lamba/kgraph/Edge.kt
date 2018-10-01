package it.lamba.kgraph

interface Edge {
    val initialNode: Node
    val arrivalNode: Node
    val cost: Double
    val id: String
    operator fun contains(node: Node?): Boolean
}
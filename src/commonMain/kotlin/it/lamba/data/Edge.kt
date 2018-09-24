package it.lamba.data

interface Edge<T> {
    val initialNode: Node<T>
    val arrivalNode: Node<T>
    val cost: Double
    val id: String
    operator fun contains(node: Node<T>?): Boolean
}
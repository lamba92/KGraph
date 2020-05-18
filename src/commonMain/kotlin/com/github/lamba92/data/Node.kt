package com.github.lamba92.data

interface Node<T> {
    val id: String
    fun <T> getValue(): T?
    fun setValue(value: Any)
}

fun Node.getEdges(graph: Graph) =
    graph.getEdgesFrom(this)

operator fun Node.contains(value: Any?) =
    value == getValue()

package it.lamba.kgraph.data

interface Node {
    val id: String
    fun <T> getValue(): T?
    fun setValue(value: Any)
}
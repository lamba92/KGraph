package it.lamba.kgraph

interface Node {
    val id: String
    fun <T> getValue(): T
    fun setValue(value: Any)
}
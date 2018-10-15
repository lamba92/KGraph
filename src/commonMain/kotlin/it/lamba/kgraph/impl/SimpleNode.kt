package it.lamba.kgraph.impl

import it.lamba.kgraph.Node

data class SimpleNode(override val id: String, var nodeValue: Any?): Node {

    @Suppress("UNCHECKED_CAST")
    override fun <T> getValue() = nodeValue as T

    override fun setValue(value: Any){ this.nodeValue = value }
}
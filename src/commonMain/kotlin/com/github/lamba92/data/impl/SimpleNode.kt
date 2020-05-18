package com.github.lamba92.data.impl

import com.github.lamba92.data.Node

data class SimpleNode(override val id: String, var nodeValue: Any?): Node {

    @Suppress("UNCHECKED_CAST")
    override fun <T> getValue() = nodeValue as T

    override fun setValue(value: Any){ this.nodeValue = value }
    override fun toString() = "Node [ id = $id | value = $nodeValue ]"
}

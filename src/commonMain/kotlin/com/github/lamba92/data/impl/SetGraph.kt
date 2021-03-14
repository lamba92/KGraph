@file:Suppress("MemberVisibilityCanBePrivate")

package com.github.lamba92.data.impl

import com.github.lamba92.data.Edge
import com.github.lamba92.data.MutableGraph
import com.github.lamba92.data.Node

class SetGraph<T, R : Comparable<R>> : MutableGraph<T, R> {

    private val nodesSet =
        mutableSetOf<Node<T>>()

    private val edgesSet =
        mutableSetOf<Edge<T, R>>()

    override fun iterator() =
        nodesSet.iterator()

    override val nodes: Set<Node<T>>
        get() = nodesSet

    override val edges: Set<Edge<T, R>>
        get() = edgesSet

    override operator fun contains(node: Node<T>) =
        node in nodesSet

    override operator fun contains(edge: Edge<T, R>) =
        edge in edgesSet

    override fun addNode(value: T) =
        Node(value).also { nodesSet.add(it) }

    override fun addEdge(n1: Node<T>, n2: Node<T>, cost: R): Edge<T, R> {
        if (n1 !in this || n2 !in this) {
            throw IllegalArgumentException("One of the nodes is not inside the graph.")
        }
        return Edge(n1, n2, cost).also { edgesSet.add(it) }
    }

    override fun getEdgesFrom(node: Node<T>): Set<Edge<T, R>> =
        edgesSet.asSequence().filter { it.initialNode == node }.toSet()

    override fun getEdgeBetweenNodes(from: Node<T>, to: Node<T>): Set<Edge<T, R>> =
        edgesSet.asSequence()
            .filter {
                it.initialNode == from && it.arrivalNode == to ||
                        it.arrivalNode == from && it.initialNode == to
            }
            .toSet()

    override fun addBidirectionalEdge(
        n1: Node<T>,
        n2: Node<T>,
        costN1toN2: R,
        costN2toN1: R
    ): Pair<Edge<T, R>, Edge<T, R>> =
        addEdge(n1, n2, costN1toN2) to addEdge(n2, n1, costN2toN1)

    override fun removeNode(node: Node<T>): Boolean {
        return if (node in this) {
            nodesSet.remove(node)
            edgesSet.removeAll { it.initialNode == node && it.arrivalNode == node }
            true
        } else false
    }

    override fun removeEdge(edge: Edge<T, R>): Boolean =
        edgesSet.remove(edge)

}

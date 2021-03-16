@file:Suppress("MemberVisibilityCanBePrivate")

package com.github.lamba92.data

/**
 * [Graph] implementation backed by [MutableMap]s. This implementation do not allow duplicated [Node]s.
 */
class HashMapGraph<T, R: Comparable<R>> : MutableGraph<T, R> {

    private val data: MutableMap<Node<T>, MutableList<Edge<T, R>>> =
        mutableMapOf()

    // index for fast removal of nodes
    private val reverseData: MutableMap<Node<T>, MutableList<Node<T>>> =
        mutableMapOf()

    override val nodes: Iterator<Node<T>>
        get() = data.keys.iterator()

    override val edges: Iterator<Edge<T, R>>
        get() = data.values.asSequence().flatMap { it }.iterator()

    override fun getEdgesFrom(node: Node<T>): List<Edge<T, R>> =
        data[node] ?: error("Node not in graph.")

    override fun contains(node: Node<T>): Boolean =
        node in data

    override fun contains(edge: Edge<T, R>) =
        data[edge.initialNode]?.contains(edge) ?: false

    override fun addNode(node: Node<T>): Boolean {
        return if (node in data) false
        else {
            data[node] = mutableListOf()
            reverseData[node] = mutableListOf()
            true
        }
    }

    override fun addEdge(edge: Edge<T, R>): Boolean {
        if (edge.initialNode !in data) error("Initial node not in graph.")
        if (edge.arrivalNode !in data) error("Arrival node not in graph.")
        reverseData.getValue(edge.initialNode).add(edge.arrivalNode)
        return data.getValue(edge.initialNode).add(edge)
    }

    override fun removeNode(node: Node<T>): Boolean {
        return if (node in data) {
            reverseData.getValue(node)
                .map { data.getValue(it) }
                .forEach { it.removeAll { it.arrivalNode == node } }
            reverseData.remove(node)
            data.remove(node)
            true
        } else false
    }

    override fun removeEdge(edge: Edge<T, R>): Boolean {
        val wasPresent = data[edge.initialNode]?.remove(edge) ?: false
        if (wasPresent) reverseData.getValue(edge.arrivalNode).remove(edge.arrivalNode)
        return wasPresent
    }

    override fun iterator(): Iterator<Node<T>> =
        data.keys.iterator()

}
package com.github.lamba92.searches

import com.github.lamba92.data.Edge
import com.github.lamba92.data.Node

interface Frontier: MutableMap<Node, Frontier.Element> {

    val nextElementEvaluator: (Element) -> Double

    fun next(): Element
    fun hasNext(): Boolean

    interface Element {
        val node: Node
        val depth: Int
        val path: ArrayList<Edge>
        val costUntilHere: Double
        val parentNode: Node?
        val heuristic: Double?
    }
}

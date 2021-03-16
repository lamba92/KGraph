package com.github.lamba92.searches

import com.github.lamba92.data.Edge
import com.github.lamba92.data.Graph
import com.github.lamba92.data.Node

interface Frontier<T, R : Comparable<R>, K : Comparable<K>> : Iterator<Frontier.Element<T, R, K>> {

    interface Element<T, R : Comparable<R>, K : Comparable<K>> {
        val node: Node<T>
        val depth: Int
        val path: List<Edge<T, R>>
        val costUntilHere: R
        val parentNode: Node<T>?
        val heuristic: K?
    }

}

package it.lamba.kgraph

import it.lamba.data.Node
import it.lamba.utils.randomString
import kotlin.random.Random

data class KGraphNode<T>(override var value: T?, override val id: String): Node<T>
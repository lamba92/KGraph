package it.lamba.kgraph.data.impl.dsl

import it.lamba.utils.randomString
import kotlin.random.Random

class NodeConfiguration(var value: Any? = null, var id: String = Random.randomString())
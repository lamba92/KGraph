package it.lamba.kgraph.data.impl.dsl

import it.lamba.kgraph.data.Node

class EdgeConfiguration(var initialNode: Node? = null, var arrivalNode: Node? = null,
                        var cost: Double? = null)
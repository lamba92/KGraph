package it.lamba.kgraph.impl.dsl

import it.lamba.kgraph.Node

class EdgeConfiguration(var initialNode: Node? = null, var arrivalNode: Node? = null,
                        var cost: Double? = null)
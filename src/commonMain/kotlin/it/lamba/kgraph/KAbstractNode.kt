package it.lamba.kgraph

abstract class KAbstractNode(val name: String) {

    val parentsNodes = HashSet<KAbstractNode>()
    val childrenNodes = HashSet<KAbstractNode>()

}
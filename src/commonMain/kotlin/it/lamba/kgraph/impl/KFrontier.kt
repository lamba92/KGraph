package it.lamba.kgraph.impl

import it.lamba.kgraph.Frontier
import it.lamba.kgraph.Node
import it.lamba.utils.forEach

class KFrontier(val nextElementEvaluator: (Frontier.Element) -> Double): Frontier {

    private val data = HashMap<Node, Frontier.Element>()

    override fun next(): Frontier.Element {
        var bestNode: Node? = null
        forEach { node, element ->
            if(bestNode == null || nextElementEvaluator(element) > nextElementEvaluator(this[bestNode!!]!!))
                bestNode = node
        }
        return remove(bestNode)!!
    }

    override fun hasNext() = isNotEmpty()

    override val entries: MutableSet<MutableMap.MutableEntry<Node, Frontier.Element>>
        get() = data.entries
    override val keys: MutableSet<Node>
        get() = data.keys
    override val size: Int
        get() = data.size
    override val values: MutableCollection<Frontier.Element>
        get() = data.values

    override fun clear() = data.clear()

    override fun containsKey(key: Node) = data.containsKey(key)

    override fun containsValue(value: Frontier.Element) = data.containsValue(value)

    override fun get(key: Node) = data[key]

    override fun isEmpty() = data.isEmpty()

    override fun put(key: Node, value: Frontier.Element): Frontier.Element? {
        val previous = data[key]
        if(previous == null || value.costUntilHere < previous.costUntilHere)
            data[key] = value
        return previous
    }

    override fun putAll(from: Map<out Node, Frontier.Element>)
            = from.forEach { put(it.key, it.value)}

    override fun remove(key: Node) = data.remove(key)




}

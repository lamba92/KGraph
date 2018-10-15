package it.lamba.utils

class PriorityQueueSet<E, T>(val caseComparator: (a: E, b: E) -> CompareCase): Queue<E>, MutableSet<E> {

    enum class CompareCase {
        DIFFERENT, SUBSTITUTE, IGNORE
    }

    private val data = ArrayList<E>()

    override fun addAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(element: E): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(element: E): Boolean {
        loop@ for(i in 0 until data.size){
            when(caseComparator(element, data[i])){
                CompareCase.DIFFERENT -> {}
                CompareCase.SUBSTITUTE -> {
                    data.removeAt(i)
                    break@loop
                }
                CompareCase.IGNORE -> return false
            }
        }
        return true //TODO wrong
    }

    override fun offer(e: E): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun poll(): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun element(): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun peek(): E {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val size: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun contains(element: E): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): MutableIterator<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
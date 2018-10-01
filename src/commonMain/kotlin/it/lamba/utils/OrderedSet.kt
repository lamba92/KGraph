package it.lamba.utils

interface Queue<E>: MutableCollection<E> {

    fun offer(e: E): Boolean
    fun remove(): E
    fun poll(): E
    fun element(): E
    fun peek(): E

}

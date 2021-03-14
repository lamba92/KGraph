package com.github.lamba92.utils

import kotlin.random.Random

fun <K, V> MutableSet<MutableMap.MutableEntry<K, V>>.removeIf(filter: (key: K, value: V) -> Boolean){
    val iter = iterator()
    while (iter.hasNext()){
        val current = iter.next()
        if(filter(current.key, current.value))
            iter.remove()
    }
}

fun Random.randomString(length: Int = nextInt(15, 21)): String {
    var s = ""
    for(i in 0 until length){
        val nextInt = nextInt(1, 4)
        s += when(nextInt){
            1 -> nextInt(48, 58).toChar()
            2 -> nextInt(65, 91).toChar()
            else -> nextInt(97, 123).toChar()
        }
    }
    return s
}

fun <T> ArrayList<T>.orderedInsert(item: T, comparator: Comparator<T>) {
    for(i in 0 until size)
        if(comparator.compare(item, this[i]) > 0) add(i, item)
}

fun <E> Collection<E>.sumBy(selector: (E) -> Double): Double {
    var sum = 0.0
    forEach { sum += selector(it) }
    return sum
}

fun <K, V> Map<K, V>.forEach(block: (K, V) -> Unit)
        = forEach { block(it.key, it.value) }

expect fun getCurrentTimeInMillis(): Long

inline fun <T> measureTimeMillis(block: () -> T): Pair<T, Long> {
    val start = getCurrentTimeInMillis()
    val res = block()
    return Pair(res, getCurrentTimeInMillis() - start)
}

fun StringBuilder.appendln(obj: Any?) = append(obj.toString() + "\n")
fun StringBuilder.appendTabbedln(obj: Any?) = append("    ${obj.toString()}\n")

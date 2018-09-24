package it.lamba.utils

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
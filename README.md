[![](https://jitpack.io/v/lamba92/kgraph.svg)](https://jitpack.io/#lamba92/kgraph)

# KGraph

KGraph is a library that handles grpah data structures using only the Kotlin common standard library. 
At the moment, it compiles for:

 - JVM
 - JS
 - Android Native ARM32
 - Android Native ARM64
 - Linux X86_64
 
 Other native builds will come as i figure out how to compile multi-platform.

## Goals

- add search algorithms, blind and heuristic driven
- learn and add multi-platform tests
- allow serialization of nodes, edge and whole graph
- save on file and load from file
- platform specif graphic visualizations (help reeeaaaally wanted)
- add Dokka wherever
- who knows!

## Installing [![](https://jitpack.io/v/lamba92/kgraph.svg)](https://jitpack.io/#lamba92/kgraph)

Add the [JitPack.io](http://jitpack.io) repository to the project `build.grade`:
```
repositories {
    maven { url 'https://jitpack.io' }
}
```

Then import the latest version in the `build.gradle` of the modules you need:

```
dependencies {
    implementation 'com.github.lamba92:kgraph:{latest_version}'
}
```

If using Gradle Kotlin DSL:
```
repositories {
    maven(url = "https://jitpack.io")
}
...
dependencies {
    implementation("com.github.Lamba92", "kgraph", "{latest_version}")
}
```

## Usage

KGraph is made by 3 main interfaces: [Graph](https://github.com/lamba92/KGraph/blob/master/src/commonMain/kotlin/it/lamba/data/Graph.kt), [Node](https://github.com/lamba92/KGraph/blob/master/src/commonMain/kotlin/it/lamba/data/Node.kt) and [Edge](https://github.com/lamba92/KGraph/blob/master/src/commonMain/kotlin/it/lamba/data/Edge.kt).

To create an instance of a KGraph which nodes contains an Integer value:

```
import it.lamba.kgraph.SimpleGraph

val g = KGraph<Int>()
val n1 = g.addNode(value = 2)
val n2 = g.addNode(value = 4)

val (e1, e2) = g.addBidirectionEdge(n1, n2, costN2toN1 = 1.0, costN1toN2 = 1.5)
```
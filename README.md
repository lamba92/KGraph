[![](https://jitpack.io/v/lamba92/kgraph.svg)](https://jitpack.io/#lamba92/kgraph)

# KGraph

KGraph provides an easy to use platform independent graph library. It bundles a convenient DSL to handel the creation of the graph on the go. 
At the moment, it compiles for:

 - JVM
 - JS
 - Android Native ARM32
 - Android Native ARM64
 - Linux X86_64
 
 Other native builds will come as I figure out how to compile multi-platform.

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

Here an example for creating a graph using the provided DSL:

```
import it.lamba.kgraph.impl.dsl.bidirectionEdge
import it.lamba.kgraph.impl.dsl.edge
import it.lamba.kgraph.impl.dsl.graphBuilder
import it.lamba.kgraph.impl.dsl.node
import it.lamba.utils.randomString
import kotlin.random.Random

val g = graphBuilder {

    val n1 = node {
        id = "ciao"
        value = 20
    }

    val n2 = node {
        id = Random.randomString()
        value = HashMap<Int, String>()
    }

    bidirectionEdge {
        initialNode = n1
        arrivalNode = n2
        cost = 20.0
    }

    edge {
        initialNode = n1
        arrivalNode = n1
        cost = 13.123
    }
}
```
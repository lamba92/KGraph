[![](https://jitpack.io/v/lamba92/kgraph.svg)](https://jitpack.io/#lamba92/kgraph)

# KGraph

KGraph provides an easy to use platform independent graph library. It bundles a convenient DSL to handle the creation of the graph on the go. 
At the moment, it compiles for:

 - JVM
 - JS
 - Android Native ARM32
 - Android Native ARM64
 - Linux X86_64
 
 Other native builds will come as I figure out how to compile multi-platform.

## Goals

- [ ] add search algorithms, blind and heuristic driven
- [x] add multi-platform tests
- [ ] allow serialization of nodes, edge and whole graph
- [ ] save on file and load from file
- [ ] platform specif graphic visualizations (help reeeaaaally wanted)
- [ ] add Dokka wherever

## Usage

KGraph is made by 3 main interfaces: [Graph](https://github.com/lamba92/KGraph/blob/master/src/commonMain/kotlin/it/lamba/kgraph/data/Graph.kt), [Node](https://github.com/lamba92/KGraph/blob/master/src/commonMain/kotlin/it/lamba/kgraph/data/Node.kt) and [Edge](https://github.com/lamba92/KGraph/blob/master/src/commonMain/kotlin/it/lamba/kgraph/data/Edge.kt).

Here an example for creating a graph using the provided DSL:

```
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

val (result, timeElapsed) = DepthFirstSearch(g, g.nodes.first(), "targetValue").compute()

```

## Installing [![](https://jitpack.io/v/lamba92/kgraph.svg)](https://jitpack.io/#lamba92/kgraph)

Add the [JitPack.io](http://jitpack.io) repository to the project `build.gradle`:
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
    implementation("com.github.lamba92.kgraph", "kgraph{-platform}", "{latest_version}")
}
```

The `-platform` placeholder should be replaced with:
 - `-jvm`
 - `-js`
 - `-android_native_32`
 - `-android_native_64`
 - `-linuxx86_64`
 
Leave it empty for common sources in common library.

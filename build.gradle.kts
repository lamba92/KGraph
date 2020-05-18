@file:Suppress("UNUSED_VARIABLE")

import com.github.lamba92.gradle.utils.*
import org.gradle.internal.os.OperatingSystem

buildscript {
    repositories {
        maven("https://dl.bintray.com/lamba92/com.github.lamba92")
        google()
    }
    dependencies {
        classpath("com.github.lamba92", "lamba-gradle-utils", "1.0.6")
//        classpath("com.android.tools.build", "gradle", "3.6.0")
    }
}

plugins {
    id("com.android.library") version "3.6.0"
    kotlin("multiplatform") version "1.4-M1"
    id("com.jfrog.bintray") version "1.8.5"
    `maven-publish`
}

repositories {
    mavenCentral()
    jcenter()
    google()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}

group = "com.github.lamba92"
version = TRAVIS_TAG ?: "1.0.5"

android {

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    compileSdkVersion(29)
    buildToolsVersion("30-rc4")

    defaultConfig {
        minSdkVersion(14)
    }

    alignSourcesForKotlinMultiplatformPlugin(project)
}

kotlin {
    android()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        nodejs()
    }

    val mainNativeTarget = mingwX64()
    val otherNativeTargets = listOf(
        mingwX86(),
        androidNativeArm32(),
        androidNativeArm64(),
        androidNativeX86(),
        androidNativeX64(),
        linuxMips32(),
        linuxMipsel32(),
        linuxX64(),
        linuxArm32Hfp(),
        macosX64(),
        iosArm32(),
        iosArm64(),
        iosX64(),
        watchosArm32(),
        watchosArm64(),
        watchosX86(),
        tvosArm64(),
        tvosX64(),
        wasm32()
    )

    val commonNativeSourcesPath = file("$buildDir/generated/nativeSourceCopy").absolutePath

    val nativeSourceCopy by tasks.creating(Sync::class) {
        from(mainNativeTarget.compilations["main"].defaultSourceSet.kotlin.asPath)
        into(commonNativeSourcesPath)
    }

    configure(otherNativeTargets) {
        compilations["main"].apply {
            defaultSourceSet.kotlin.srcDir(commonNativeSourcesPath)
            compileKotlinTask.dependsOn(nativeSourceCopy)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

prepareForPublication(publicationNames = when {
    OperatingSystem.current().isWindows -> publishing.publications.names.filter { "mingw" in it }
    OperatingSystem.current().isMacOsX -> publishing.publications.names.filter { "os" in it }
    OperatingSystem.current().isLinux -> publishing.publications.names.filter { "mingw" !in it && "os" in it }
    else -> throw IllegalStateException("OS unknown")
})

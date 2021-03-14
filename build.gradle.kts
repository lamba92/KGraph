@file:Suppress("UNUSED_VARIABLE")

plugins {
    id("com.android.library") version if (System.getenv("CI")?.toBoolean() == true) "4.1.2" else "4.0.2"
    kotlin("multiplatform") version "1.4.31"
    id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
    `maven-publish`
}

repositories {
    mavenCentral()
    google()
}

version = System.getenv("GITHUB_REF")?.split("/")?.lastOrNull().takeIf { it != "master" }
    ?: "1.0.0-SNAP"

group = "com.github.lamba92"

android {

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    compileSdkVersion(30)
    buildToolsVersion("31.0.0-rc1")

    defaultConfig {
        minSdkVersion(14)
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

kotlin {

    android()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js(BOTH) {
        nodejs()
    }

    mingwX64()
    ios()
    watchos()
    tvos()
    linuxX64()
    linuxArm64()
    linuxArm32Hfp()

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.1.0")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmCommon by creating {
            dependsOn(commonMain.get())
        }
        val commonNative by creating {
            dependsOn(commonMain.get())
        }
        val androidMain by getting {
            dependsOn(jvmCommon)
        }
        val jvmMain by getting {
            dependsOn(jvmCommon)
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
        val mingwX64Main by getting {
            dependsOn(commonNative)
        }

    }
}

nexusPublishing {
    repositories {
        sonatype {
            username.set("Lamba92")
            password.set(System.getenv("SONTYPE_PASSWORD"))
        }
    }
}
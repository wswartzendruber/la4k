/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

import java.net.URI

import org.jetbrains.dokka.gradle.DokkaTask

val mavenUrlBase: String? by project
val mavenUsername: String? by project
val mavenPassword: String? by project

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.dokka").version("0.10.1")
    id("maven-publish")
}

repositories {
    google()
}

tasks {

    val dokka by getting(DokkaTask::class) {
        outputDirectory = "$buildDir/dokka"
        outputFormat = "html"
        multiplatform {
            register("jvm") {
                targets = listOf("JVM", "Android")
            }
        }
    }

    register<Jar>("dokkaJar") {
        classifier = "dokka"
        from(dokka)
    }
}

kotlin {
    explicitApi()
    metadata {
        mavenPublication {
            artifact(tasks["dokkaJar"])
        }
    }
    jvm { }
    android { }
}

android {
    compileSdkVersion(23)
    defaultConfig.minSdkVersion(9)
    sourceSets["main"].java.srcDirs(File("src/jvmMain/kotlin"))
    sourceSets["main"].manifest.srcFile(File("src/androidMain/AndroidManifest.xml"))
}

dependencies {
    // COMMON
    commonMainImplementation(kotlin("stdlib-common"))
    // JVM
    "jvmMainImplementation"(kotlin("stdlib-jdk8"))
    // Android
    "androidMainImplementation"(kotlin("stdlib-jdk8"))
}

publishing {
    repositories {
        maven {
            url = URI("$mavenUrlBase/la4k-api;publish=1")
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}

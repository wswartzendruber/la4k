/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
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
    sourceSets["test"].resources.srcDirs(File("src/jvmTest/resources"))
}

dependencies {
    // COMMON
    commonMainImplementation(kotlin("stdlib-common"))
    commonTestImplementation(kotlin("test-common"))
    commonTestImplementation(kotlin("test-annotations-common"))
    // JVM
    "jvmMainImplementation"(kotlin("stdlib-jdk8"))
    "jvmTestImplementation"(kotlin("test-junit"))
    // Android
    "androidMainImplementation"(kotlin("stdlib-jdk8"))
    "androidTestImplementation"(kotlin("test-junit"))
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

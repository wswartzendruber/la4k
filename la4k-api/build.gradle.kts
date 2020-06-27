/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

import org.jetbrains.dokka.gradle.DokkaTask

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
    compileSdkVersion(9)
    sourceSets["main"].java.srcDirs(File("src/jvmMain/kotlin"))
    sourceSets["main"].manifest.srcFile(File("src/androidMain/AndroidManifest.xml"))
    defaultConfig.minSdkVersion(9)
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
}

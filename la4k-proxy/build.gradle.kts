/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka").version("0.10.1")
    id("maven-publish")
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
}

dependencies {
    // COMMON
    commonMainImplementation(project(":la4k-api"))
    commonMainImplementation(kotlin("stdlib-common"))
    commonTestImplementation(kotlin("test-common"))
    commonTestImplementation(kotlin("test-annotations-common"))
    // JVM
    "jvmMainImplementation"(project(":la4k-api"))
    "jvmMainImplementation"(kotlin("stdlib-jdk8"))
    "jvmTestImplementation"(kotlin("test-junit"))
}

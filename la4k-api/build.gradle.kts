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

kotlin {
    jvm()
}

dependencies {
    // COMMON
    commonMainImplementation(kotlin("stdlib-common"))
    // JVM
    "jvmMainImplementation"(kotlin("stdlib-jdk8"))
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
    }
}

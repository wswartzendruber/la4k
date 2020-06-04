/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

kotlin {
    jvm()
}

dependencies {
    // COMMON
    commonMainImplementation(project(":la4k"))
    commonMainImplementation(kotlin("stdlib-common"))
    commonTestImplementation(project(":la4k-proxy"))
    commonTestImplementation(kotlin("test-common"))
    commonTestImplementation(kotlin("test-annotations-common"))
    // JVM
    "jvmMainImplementation"(project(":la4k"))
    "jvmMainImplementation"(kotlin("stdlib-jdk8"))
    "jvmTestImplementation"(project(":la4k-proxy"))
    "jvmTestImplementation"(kotlin("test-junit"))
}

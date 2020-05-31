/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

plugins {
    kotlin("jvm")
    id("java")
    id("maven-publish")
}

dependencies {
    implementation(project(":la4k"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.apache.logging.log4j:log4j-api:2.12.0")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.apache.logging.log4j:log4j-core:2.12.0")
}

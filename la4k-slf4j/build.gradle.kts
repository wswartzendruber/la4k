/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka").version("0.10.1")
    id("maven-publish")
}

tasks {

    val dokka by getting(DokkaTask::class) {
        outputDirectory = "$buildDir/dokka"
        outputFormat = "html"
    }

    register<Jar>("sourcesJar") {
        classifier = "sources"
        from(sourceSets.main.get().allSource)
    }

    register<Jar>("dokkaJar") {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        classifier = "dokka"
        from(dokka)
    }
}

dependencies {
    implementation(project(":la4k-api"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.slf4j:slf4j-api:1.7.26")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
    testImplementation(kotlin("test-junit"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

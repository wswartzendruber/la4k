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
    kotlin("android")
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
    }

    register<Jar>("sourcesJar") {
        classifier = "sources"
        from("src/main/kotlin")
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
}

android {
    compileSdkVersion(9)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["release"])
                artifact(tasks["sourcesJar"])
                artifact(tasks["dokkaJar"])
            }
        }
        repositories {
            maven {
                url = URI("$mavenUrlBase/la4k-android;publish=1")
                credentials {
                    username = mavenUsername
                    password = mavenPassword
                }
            }
        }
    }
}

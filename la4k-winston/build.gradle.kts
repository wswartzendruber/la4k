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
    kotlin("js")
    id("org.jetbrains.dokka")
    id("maven-publish")
}

tasks {

    register<Jar>("sourcesJar") {
        group = "Build"
        description = "Packages all sources into a JAR."
        classifier = "sources"
        from("src/main/kotlin")
    }

    register<Jar>("dokkaHtmlJar") {
        group = "Build"
        description = "Packages dokkaHtml output into a JAR."
        classifier = "dokka"
        from(dokkaHtml)
    }
}

kotlin {
    explicitApi()
    js {
        nodejs { }
    }
}

dependencies {
    implementation(project(":la4k-api"))
    implementation(npm("winston", ">=3.0.0"))
    testImplementation(kotlin("test-common"))
    testImplementation(kotlin("test-annotations-common"))
    testImplementation(kotlin("test-js"))
}

publishing {
    repositories {
        maven {
            url = URI("$mavenUrlBase/la4k-test;publish=1")
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}

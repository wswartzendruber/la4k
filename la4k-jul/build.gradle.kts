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
    kotlin("jvm")
    id("org.jetbrains.dokka").version("1.4.10")
    id("maven-publish")
}

tasks {

    register<Jar>("sourcesJar") {
        classifier = "sources"
        from(sourceSets["main"].allSource)
    }

    register<Jar>("dokkaJar") {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        classifier = "dokka"
        from(dokkaHtml)
    }
}

kotlin {
    explicitApi()
}

dependencies {
    implementation(project(":la4k-api"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
    testImplementation(kotlin("test-junit"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["dokkaJar"])
        }
    }
    repositories {
        maven {
            url = URI("$mavenUrlBase/la4k-jul;publish=1")
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}

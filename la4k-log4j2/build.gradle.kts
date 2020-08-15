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
        from(sourceSets["main"].allSource)
    }

    register<Jar>("dokkaJar") {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        classifier = "dokka"
        from(dokka)
    }
}

kotlin {
    explicitApi()
}

dependencies {
    implementation(project(":la4k-api"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.apache.logging.log4j:log4j-api:2.12.0")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-annotations-common"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.apache.logging.log4j:log4j-core:2.12.0")
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
            url = URI("$mavenUrlBase/la4k-log4j2;publish=1")
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}

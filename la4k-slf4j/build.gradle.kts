/*
 * SPDX-FileCopyrightText: 2021 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: CC0-1.0
 */

import java.net.URI

val mavenUrlBase: String? by project
val mavenUsername: String? by project
val mavenPassword: String? by project

plugins {
    kotlin("jvm")
    // TODO: Re-enable Dokka when it becomes fit for service.
    // id("org.jetbrains.dokka")
    id("maven-publish")
}

tasks {

    register<Jar>("sourcesJar") {
        group = "Build"
        description = "Packages all sources into a JAR."
        classifier = "sources"
        from(sourceSets["main"].allSource)
    }

    // TODO: Re-enable Dokka when it becomes fit for service.
    // register<Jar>("dokkaHtmlJar") {
    //     group = "Build"
    //     description = "Packages dokkaHtml output into a JAR."
    //     classifier = "dokka"
    //     from(dokkaHtml)
    // }
}

kotlin {
    explicitApi()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(project(":la4k-api"))
    implementation("org.slf4j:slf4j-api:1.7.26")
    testImplementation(kotlin("test"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            // TODO: Re-enable Dokka when it becomes fit for service.
            // artifact(tasks["dokkaHtmlJar"])
        }
    }
    repositories {
        maven {
            url = URI("$mavenUrlBase/la4k-slf4j;publish=1")
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}

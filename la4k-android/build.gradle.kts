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
    id("com.android.library")
    kotlin("android")
    id("org.jetbrains.dokka").version("1.4.0-rc")
    id("maven-publish")
}

repositories {
    google()
}

tasks {

    register<Jar>("sourcesJar") {
        classifier = "sources"
        from("src/main/kotlin")
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

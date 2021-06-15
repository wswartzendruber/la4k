/*
 * SPDX-FileCopyrightText: 2021 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: CC0-1.0
 */

import java.net.URI

// TODO: Re-enable Dokka when it becomes fit for service.
// import org.jetbrains.dokka.Platform

val mavenUrlBase: String? by project
val mavenUsername: String? by project
val mavenPassword: String? by project

repositories {
    google()
}

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    // TODO: Re-enable Dokka when it becomes fit for service.
    // id("org.jetbrains.dokka")
    id("maven-publish")
}

tasks {

    // TODO: Re-enable Dokka when it becomes fit for service.
    // dokkaHtml {
    //     dokkaSourceSets {
    //         named("commonMain") {
    //             displayName.set("Common")
    //             platform.set(Platform.common)
    //         }
    //     }
    // }
    //
    // register<Jar>("dokkaHtmlJar") {
    //     group = "Build"
    //     description = "Packages dokkaHtml output into a JAR."
    //     classifier = "dokka"
    //     from(dokkaHtml)
    // }
}

kotlin {
    explicitApi()
    metadata {
        mavenPublication {
            // TODO: Re-enable Dokka when it becomes fit for service.
            // artifact(tasks["dokkaHtmlJar"])
        }
    }
    jvm { }
    android { }
    js {
        browser {
            testTask {
                enabled = false
            }
        }
        nodejs { }
    }
}

android {
    compileSdkVersion(23)
    defaultConfig.minSdkVersion(9)
    sourceSets["main"].java.srcDirs(File("src/jvmMain/kotlin"))
    sourceSets["main"].manifest.srcFile(File("src/androidMain/AndroidManifest.xml"))
    sourceSets["main"].resources.srcDirs(File("src/jvmMain/resources"))
}

dependencies {
    // Common
    commonMainImplementation(platform(kotlin("bom")))
    commonMainImplementation(project(":la4k-api"))
}

publishing {
    repositories {
        maven {
            url = URI("$mavenUrlBase/la4k-console;publish=1")
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}

/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

import java.net.URI

import org.jetbrains.dokka.Platform
import org.jetbrains.dokka.gradle.DokkaTask

val mavenUrlBase: String? by project
val mavenUsername: String? by project
val mavenPassword: String? by project

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.dokka")
    id("maven-publish")
}

repositories {
    google()
}

tasks {

    dokkaHtml {
        dokkaSourceSets {
            named("commonMain") {
                displayName.set("Common")
                platform.set(Platform.common)
            }
            named("jsMain") {
                displayName.set("JS")
                platform.set(Platform.js)
            }
        }
    }

    register<Jar>("dokkaJar") {
        classifier = "dokka"
        from(dokkaHtml)
    }
}

kotlin {
    explicitApi()
    metadata {
        mavenPublication {
            artifact(tasks["dokkaJar"])
        }
    }
    jvm { }
    android { }
    js {
        browser {
            testTask {
                useKarma {
                    useFirefox()
                }
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
}

publishing {
    repositories {
        maven {
            url = URI("$mavenUrlBase/la4k-api;publish=1")
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}

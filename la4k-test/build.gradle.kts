/*
 * Copyright 2021 William Swartzendruber
 *
 * To the extent possible under law, the person who associated CC0 with this file has waived all
 * copyright and related or neighboring rights to this file.
 *
 * You should have received a copy of the CC0 legalcode along with this work. If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 *
 * SPDX-License-Identifier: CC0-1.0
 */

import java.net.URI

import org.jetbrains.dokka.Platform

val mavenUrl: String? by project
val mavenUsername: String? by project
val mavenPassword: String? by project

repositories {
    google()
}

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.dokka")
    id("signing")
    id("maven-publish")
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

    register<Jar>("dokkaHtmlJar") {
        group = "Build"
        description = "Packages dokkaHtml output into a JAR."
        classifier = "dokka"
        from(dokkaHtml)
    }
}

kotlin {
    explicitApi()
    metadata {
        mavenPublication {
            artifact(tasks["dokkaHtmlJar"])
        }
    }
    jvm { }
    android {
        publishLibraryVariants("release")
    }
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
    defaultConfig {
        minSdkVersion(9)
    }
    compileSdkVersion(23)
    sourceSets["main"].java.srcDirs(File("src/jvmMain/kotlin"))
    sourceSets["main"].manifest.srcFile(File("src/androidMain/AndroidManifest.xml"))
    sourceSets["main"].resources.srcDirs(File("src/jvmMain/resources"))
}

dependencies {
    // Common
    commonMainImplementation(platform(kotlin("bom")))
    commonMainImplementation(project(":la4k-api"))
    commonTestImplementation(kotlin("test"))
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifact(tasks["dokkaHtmlJar"])
            pom {
                name.set("LA4K Bridge: Unit Testing")
                description.set("LA4K bridge for unit testing libraries")
                url.set("https://github.com/wswartzendruber/la4k")
                developers {
                    developer {
                        id.set("wswartzendruber")
                        name.set("William Swartzendruber")
                        email.set("wswartzendruber@gmail.com")
                    }
                }
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/wswartzendruber/la4k.git")
                    developerConnection.set("scm:git:git://github.com/wswartzendruber/la4k.git")
                    url.set("https://github.com/wswartzendruber/la4k")
                }
            }
        }
    }
    repositories {
        maven {
            url = URI(mavenUrl.toString())
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}

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

val mavenUrl: String? by project
val mavenUsername: String? by project
val mavenPassword: String? by project

repositories {
    google()
}

plugins {
    id("com.android.library")
    kotlin("android")
    id("org.jetbrains.dokka")
    id("signing")
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
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(project(":la4k-api"))
}

android {
    defaultConfig {
        minSdkVersion(9)
    }
    compileSdkVersion(9)
}

afterEvaluate {
    signing {
        useGpgCmd()
        sign(publishing.publications)
    }
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["release"])
                artifact(tasks["sourcesJar"])
                artifact(tasks["dokkaHtmlJar"])
                pom {
                    name.set("LA4K Bridge: Android")
                    description.set("LA4K bridge for Android's native logger")
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
                        developerConnection.set(
                            "scm:git:git://github.com/wswartzendruber/la4k.git"
                        )
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
}

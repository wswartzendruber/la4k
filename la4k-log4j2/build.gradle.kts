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

plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("signing")
    id("maven-publish")
}

tasks {

    register<Jar>("sourcesJar") {
        group = "Build"
        description = "Packages all sources into a JAR."
        classifier = "sources"
        from(sourceSets["main"].allSource)
    }

    register<Jar>("dokkaHtmlJar") {
        group = "Build"
        description = "Packages dokkaHtml output into a JAR."
        classifier = "dokka"
        from(dokkaHtml)
    }

    withType<JavaCompile>() {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
}

kotlin {
    explicitApi()
}

dependencies {
    implementation(platform(kotlin("bom")))
    implementation(project(":la4k-api"))
    implementation("org.apache.logging.log4j:log4j-api:2.16.0")
    testImplementation(kotlin("test"))
    testImplementation("org.apache.logging.log4j:log4j-core:2.16.0")
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["dokkaHtmlJar"])
            pom {
                name.set("LA4K Bridge: Log4j 2")
                description.set("LA4K bridge for Log4j 2")
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

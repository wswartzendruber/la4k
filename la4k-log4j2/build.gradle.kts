/*
 * SPDX-FileCopyrightText: 2021 William Swartzendruber <wswartzendruber@gmail.com>
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
        classifier = "javadoc"
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
    implementation("org.apache.logging.log4j:log4j-api:2.12.0")
    testImplementation(kotlin("test"))
    testImplementation("org.apache.logging.log4j:log4j-core:2.12.0")
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

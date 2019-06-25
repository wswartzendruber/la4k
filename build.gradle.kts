val group: String by project
val version: String by project

plugins {
    kotlin("multiplatform").version("1.3.31")
    id("maven-publish")
}

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    js()
    jvm()
}

dependencies {

    commonMainImplementation(kotlin("stdlib-common"))
    commonTestImplementation(kotlin("test-common"))
    commonTestImplementation(kotlin("test-annotations-common"))
    commonTestImplementation("org.la4k:la4k-test-metadata:0.0.1")

    "jsMainImplementation"(kotlin("stdlib-js"))

    "jvmMainImplementation"(kotlin("stdlib-jdk8"))
}

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

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("org.la4k:la4k-test-metadata:0.0.1")
            }
        }

        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        js().compilations["test"].defaultSourceSet {
            dependencies {
                implementation("org.la4k:la4k-test-js:0.0.1")
            }
        }

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("org.la4k:la4k-test-jvm:0.0.1")
            }
        }
    }
}

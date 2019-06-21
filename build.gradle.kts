val group: String by project
val version: String by project

plugins {
    kotlin("multiplatform").version("1.3.31")
    `maven-publish`
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

        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
    }
}

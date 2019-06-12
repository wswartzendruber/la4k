plugins {
    kotlin("multiplatform").version("1.3.31")
}

repositories {
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

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
    }
}

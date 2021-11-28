/*
 * SPDX-FileCopyrightText: 2021 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: CC0-1.0
 */

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.1")
    }
}

plugins {
    kotlin("multiplatform").version("1.6.0").apply(false)
    kotlin("jvm").version("1.6.0").apply(false)
    kotlin("android").version("1.6.0").apply(false)
    kotlin("js").version("1.6.0").apply(false)
    id("org.jetbrains.dokka").version("1.6.0").apply(false)
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

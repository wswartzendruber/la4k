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

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.2")
    }
}

plugins {
    kotlin("multiplatform").version("1.6.20").apply(false)
    kotlin("jvm").version("1.6.20").apply(false)
    kotlin("android").version("1.6.20").apply(false)
    kotlin("js").version("1.6.20").apply(false)
    id("org.jetbrains.dokka").version("1.6.10").apply(false)
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

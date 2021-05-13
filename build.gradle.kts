/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

buildscript {
   repositories {
      google()
   }
   dependencies {
      classpath("com.android.tools.build:gradle:4.0.0")
   }
}

plugins {
   kotlin("multiplatform").version("1.5.0").apply(false)
   kotlin("jvm").version("1.5.0").apply(false)
   kotlin("android").version("1.5.0").apply(false)
   kotlin("js").version("1.5.0").apply(false)
   // TODO: Re-enable Dokka when it becomes fit for service.
   // id("org.jetbrains.dokka").version("1.4.32").apply(false)
}

allprojects {
   repositories {
      mavenCentral()
      maven("https://plugins.gradle.org/m2/")
   }
}

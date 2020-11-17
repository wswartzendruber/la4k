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
   kotlin("multiplatform").version("1.4.10").apply(false)
   kotlin("jvm").version("1.4.10").apply(false)
   kotlin("android").version("1.4.10").apply(false)
   kotlin("js").version("1.4.10").apply(false)
   id("org.jetbrains.dokka").version("1.4.10").apply(false)
}

allprojects {
   repositories {
      jcenter()
   }
}

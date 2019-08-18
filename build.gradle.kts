/*
 * LA4K - Logging API for Kotlin
 * Copyright (C) 2019 William Swartzendruber
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program. If not, see <https://www.gnu.org/licenses/>.
 */

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
    jvm()
}

dependencies {

    commonMainImplementation(kotlin("stdlib-common"))

    "jvmMainImplementation"(kotlin("stdlib-jdk8"))
}

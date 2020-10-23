/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

package org.la4k.test

import kotlin.test.assertTrue
import kotlin.test.Test

import org.la4k.logger

val packageLogger = logger()

class NameDetectionTests {

    val classLogger = logger()
    val className = "org.la4k.test.NameDetectionTests"

    @Test
    fun package_logger() {
        assertTrue(packageLogger.name == "${className}Kt")
    }

    @Test
    fun class_logger() {
        assertTrue(classLogger.name == className)
    }

    @Test
    fun function_logger() {
        assertTrue(logger().name == className)
    }

    @Test
    fun lambda_logger() {
        "".let {
            assertTrue(logger().name == className)
        }
    }

    @Test
    fun companion_logger() {
        assertTrue(companionLogger.name == className)
    }

    companion object {

        val companionLogger = logger()
    }
}

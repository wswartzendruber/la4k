/*
 * Copyright 2020 William Swartzendruber
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.la4k.slf4j.test

import org.la4k.Logger

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail
import kotlin.test.BeforeTest
import kotlin.test.Test

class Slf4jTests {

    @Test
    fun `null throwable, null tag`() {

        Logger("test").info("test-message")

        assertTrue(messages["test"]!!.any({
            it.level == "INFO" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker == null
        }))
    }

    @Test
    fun `non-null throwable, null tag`() {

        val throwable = Exception("Test exception.")

        Logger("test").info("test-message", throwable)

        assertTrue(messages["test"]!!.any({
            it.level == "INFO" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker == null
        }))
    }

    @Test
    fun `null throwable, non-null tag`() {

        val tag = "TEST_TAG"

        Logger("test").info("test-message", null, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "INFO" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `non-null throwable, non-null tag`() {

        val throwable = Exception("Test exception.")
        val tag = "TEST_TAG"

        Logger("test").info("test-message", throwable, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "INFO" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker.toString() == tag
        }))
    }

    @BeforeTest
    fun prepare() = messages.clear()
}

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
    fun `fatal, null throwable, null tag`() {

        Logger("test").fatal("test-message")

        assertTrue(messages["test"]!!.any({
            it.level == "ERROR" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker == null
        }))
    }

    @Test
    fun `fatal, non-null throwable, null tag`() {

        val throwable = Exception("Test exception.")

        Logger("test").fatal("test-message", throwable)

        assertTrue(messages["test"]!!.any({
            it.level == "ERROR" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker == null
        }))
    }

    @Test
    fun `fatal, null throwable, non-null tag`() {

        val tag = "TEST_TAG"

        Logger("test").fatal("test-message", null, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "ERROR" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `fatal, non-null throwable, non-null tag`() {

        val throwable = Exception("Test exception.")
        val tag = "TEST_TAG"

        Logger("test").fatal("test-message", throwable, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "ERROR" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `error, null throwable, null tag`() {

        Logger("test").error("test-message")

        assertTrue(messages["test"]!!.any({
            it.level == "ERROR" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker == null
        }))
    }

    @Test
    fun `error, non-null throwable, null tag`() {

        val throwable = Exception("Test exception.")

        Logger("test").error("test-message", throwable)

        assertTrue(messages["test"]!!.any({
            it.level == "ERROR" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker == null
        }))
    }

    @Test
    fun `error, null throwable, non-null tag`() {

        val tag = "TEST_TAG"

        Logger("test").error("test-message", null, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "ERROR" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `error, non-null throwable, non-null tag`() {

        val throwable = Exception("Test exception.")
        val tag = "TEST_TAG"

        Logger("test").error("test-message", throwable, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "ERROR" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `warn, null throwable, null tag`() {

        Logger("test").warn("test-message")

        assertTrue(messages["test"]!!.any({
            it.level == "WARN" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker == null
        }))
    }

    @Test
    fun `warn, non-null throwable, null tag`() {

        val throwable = Exception("Test exception.")

        Logger("test").warn("test-message", throwable)

        assertTrue(messages["test"]!!.any({
            it.level == "WARN" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker == null
        }))
    }

    @Test
    fun `warn, null throwable, non-null tag`() {

        val tag = "TEST_TAG"

        Logger("test").warn("test-message", null, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "WARN" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `warn, non-null throwable, non-null tag`() {

        val throwable = Exception("Test exception.")
        val tag = "TEST_TAG"

        Logger("test").warn("test-message", throwable, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "WARN" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `info, null throwable, null tag`() {

        Logger("test").info("test-message")

        assertTrue(messages["test"]!!.any({
            it.level == "INFO" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker == null
        }))
    }

    @Test
    fun `info, non-null throwable, null tag`() {

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
    fun `info, null throwable, non-null tag`() {

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
    fun `info, non-null throwable, non-null tag`() {

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

    @Test
    fun `debug, null throwable, null tag`() {

        Logger("test").debug("test-message")

        assertTrue(messages["test"]!!.any({
            it.level == "DEBUG" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker == null
        }))
    }

    @Test
    fun `debug, non-null throwable, null tag`() {

        val throwable = Exception("Test exception.")

        Logger("test").debug("test-message", throwable)

        assertTrue(messages["test"]!!.any({
            it.level == "DEBUG" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker == null
        }))
    }

    @Test
    fun `debug, null throwable, non-null tag`() {

        val tag = "TEST_TAG"

        Logger("test").debug("test-message", null, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "DEBUG" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `debug, non-null throwable, non-null tag`() {

        val throwable = Exception("Test exception.")
        val tag = "TEST_TAG"

        Logger("test").debug("test-message", throwable, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "DEBUG" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `trace, null throwable, null tag`() {

        Logger("test").trace("test-message")

        assertTrue(messages["test"]!!.any({
            it.level == "TRACE" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker == null
        }))
    }

    @Test
    fun `trace, non-null throwable, null tag`() {

        val throwable = Exception("Test exception.")

        Logger("test").trace("test-message", throwable)

        assertTrue(messages["test"]!!.any({
            it.level == "TRACE" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker == null
        }))
    }

    @Test
    fun `trace, null throwable, non-null tag`() {

        val tag = "TEST_TAG"

        Logger("test").trace("test-message", null, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "TRACE" &&
            it.message == "test-message" &&
            it.throwable == null &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `trace, non-null throwable, non-null tag`() {

        val throwable = Exception("Test exception.")
        val tag = "TEST_TAG"

        Logger("test").trace("test-message", throwable, tag)

        assertTrue(messages["test"]!!.any({
            it.level == "TRACE" &&
            it.message == "test-message" &&
            it.throwable == throwable &&
            it.marker.toString() == tag
        }))
    }

    @Test
    fun `fatal is enabled`() {
        assertTrue(Logger("test").isFatalEnabled())
    }

    @Test
    fun `error is enabled`() {
        assertTrue(Logger("test").isErrorEnabled())
    }

    @Test
    fun `warn is enabled`() {
        assertTrue(Logger("test").isWarnEnabled())
    }

    @Test
    fun `info is enabled`() {
        assertTrue(Logger("test").isInfoEnabled())
    }

    @Test
    fun `debug is enabled`() {
        assertTrue(Logger("test").isDebugEnabled())
    }

    @Test
    fun `trace is enabled`() {
        assertTrue(Logger("test").isTraceEnabled())
    }

    @Test
    fun `fatal with incorrect tag length is disabled`() {
        assertFalse(Logger("test").isFatalEnabled("X"))
    }

    @Test
    fun `error with incorrect tag length is disabled`() {
        assertFalse(Logger("test").isErrorEnabled("X"))
    }

    @Test
    fun `warn with incorrect tag length is disabled`() {
        assertFalse(Logger("test").isWarnEnabled("X"))
    }

    @Test
    fun `info with incorrect tag length is disabled`() {
        assertFalse(Logger("test").isInfoEnabled("X"))
    }

    @Test
    fun `debug with incorrect tag length is disabled`() {
        assertFalse(Logger("test").isDebugEnabled("X"))
    }

    @Test
    fun `trace with incorrect tag length is disabled`() {
        assertFalse(Logger("test").isTraceEnabled("X"))
    }

    @BeforeTest
    fun prepare() = messages.clear()
}

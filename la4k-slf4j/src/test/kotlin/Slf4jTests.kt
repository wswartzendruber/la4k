/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

package org.la4k.slf4j.test

import org.la4k.logger

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class Slf4jTests {

    @Test
    fun `logger separation`() {

        logger("fatal").fatal("fatal-test-message")
        logger("error").error("error-test-message", exception)
        logger("warn").warn("warn-test-message", null, "tag")
        logger("info").info("info-test-message", exception, "tag")

        assertTrue(entries.size == 4)

        entries[0].let {
            assertTrue(it.name == "fatal")
            assertTrue(it.level == "ERROR")
            assertTrue(it.message == "fatal-test-message")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == null)
        }
        entries[1].let {
            assertTrue(it.name == "error")
            assertTrue(it.level == "ERROR")
            assertTrue(it.message == "error-test-message")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == null)
        }
        entries[2].let {
            assertTrue(it.name == "warn")
            assertTrue(it.level == "WARN")
            assertTrue(it.message == "warn-test-message")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == "tag")
        }
        entries[3].let {
            assertTrue(it.name == "info")
            assertTrue(it.level == "INFO")
            assertTrue(it.message == "info-test-message")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == "tag")
        }
    }

    @Test
    fun `FATAL maps correctly`() {

        logger("test").error("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "ERROR")
        }
    }

    @Test
    fun `ERROR maps correctly`() {

        logger("test").error("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "ERROR")
        }
    }

    @Test
    fun `WARN maps correctly`() {

        logger("test").warn("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "WARN")
        }
    }

    @Test
    fun `INFO maps correctly`() {

        logger("test").info("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "INFO")
        }
    }

    @Test
    fun `DEBUG maps correctly`() {

        logger("test").debug("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "DEBUG")
        }
    }

    @Test
    fun `TRACE maps correctly`() {

        logger("test").trace("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "TRACE")
        }
    }

    @Test
    fun `FATAL and ERROR levels disable correctly`() {

        TestLoggerAdapter.errorEnabled = false

        logger("test").let {
            assertFalse(it.isFatalEnabled())
            assertFalse(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertTrue(it.isDebugEnabled())
            assertTrue(it.isTraceEnabled())
        }
    }

    @Test
    fun `WARN level disables correctly`() {

        TestLoggerAdapter.warnEnabled = false

        logger("test").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertFalse(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertTrue(it.isDebugEnabled())
            assertTrue(it.isTraceEnabled())
        }
    }

    @Test
    fun `INFO level disables correctly`() {

        TestLoggerAdapter.infoEnabled = false

        logger("test").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertFalse(it.isInfoEnabled())
            assertTrue(it.isDebugEnabled())
            assertTrue(it.isTraceEnabled())
        }
    }

    @Test
    fun `DEBUG level disables correctly`() {

        TestLoggerAdapter.debugEnabled = false

        logger("test").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertFalse(it.isDebugEnabled())
            assertTrue(it.isTraceEnabled())
        }
    }

    @Test
    fun `TRACE level disables correctly`() {

        TestLoggerAdapter.traceEnabled = false

        logger("test").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertTrue(it.isDebugEnabled())
            assertFalse(it.isTraceEnabled())
        }
    }

    @Test
    fun `FATAL with an empty tag is enabled`() {
        assertTrue(logger("test").isFatalEnabled(""))
    }

    @Test
    fun `ERROR with an empty tag is enabled`() {
        assertTrue(logger("test").isFatalEnabled(""))
    }

    @Test
    fun `WARN with an empty tag is enabled`() {
        assertTrue(logger("test").isFatalEnabled(""))
    }

    @Test
    fun `INFO with an empty tag is enabled`() {
        assertTrue(logger("test").isFatalEnabled(""))
    }

    @Test
    fun `DEBUG with an empty tag is enabled`() {
        assertTrue(logger("test").isFatalEnabled(""))
    }

    @Test
    fun `TRACE with an empty tag is enabled`() {
        assertTrue(logger("test").isFatalEnabled(""))
    }

    @Test
    fun `FATAL with a non-empty tag is disabled`() {
        assertFalse(logger("test").isFatalEnabled("tag-disable"))
    }

    @Test
    fun `ERROR with a non-empty tag is disabled`() {
        assertFalse(logger("test").isErrorEnabled("tag-disable"))
    }

    @Test
    fun `WARN with a non-empty tag is disabled`() {
        assertFalse(logger("test").isWarnEnabled("tag-disable"))
    }

    @Test
    fun `INFO with a non-empty tag is disabled`() {
        assertFalse(logger("test").isInfoEnabled("tag-disable"))
    }

    @Test
    fun `DEBUG with a non-empty tag is disabled`() {
        assertFalse(logger("test").isDebugEnabled("tag-disable"))
    }

    @Test
    fun `TRACE with a non-empty tag is disabled`() {
        assertFalse(logger("test").isTraceEnabled("tag-disable"))
    }

    @BeforeTest
    fun prepare() {
        entries.clear()
        TestLoggerAdapter.reset()
    }

    companion object {

        val exception = Exception("test-exception")
    }
}

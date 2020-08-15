/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

package org.la4k.log4j2.test

import org.la4k.logger

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

import org.apache.logging.log4j.Level

class Log4j2Tests {

    @Test
    fun `logger separation`() {

        logger("fatal").fatal("fatal-test-message")
        logger("error").error("error-test-message", exception)
        logger("warn").warn("warn-test-message", null, "tag")
        logger("info").info("info-test-message", exception, "tag")

        assertTrue(entries.size == 4)

        entries[0].let {
            assertTrue(it.name == "fatal")
            assertTrue(it.level == Level.FATAL)
            assertTrue(it.message == "fatal-test-message")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == null)
        }
        entries[1].let {
            assertTrue(it.name == "error")
            assertTrue(it.level == Level.ERROR)
            assertTrue(it.message == "error-test-message")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == null)
        }
        entries[2].let {
            assertTrue(it.name == "warn")
            assertTrue(it.level == Level.WARN)
            assertTrue(it.message == "warn-test-message")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == "tag")
        }
        entries[3].let {
            assertTrue(it.name == "info")
            assertTrue(it.level == Level.INFO)
            assertTrue(it.message == "info-test-message")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == "tag")
        }
    }

    @Test
    fun `FATAL maps correctly`() {

        logger("all").fatal("test-message")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.FATAL)
        }
    }

    @Test
    fun `ERROR maps correctly`() {

        logger("all").error("test-message")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.ERROR)
        }
    }

    @Test
    fun `WARN maps correctly`() {

        logger("all").warn("test-message")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.WARN)
        }
    }

    @Test
    fun `INFO maps correctly`() {

        logger("all").info("test-message")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.INFO)
        }
    }

    @Test
    fun `DEBUG maps correctly`() {

        logger("all").debug("test-message")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.DEBUG)
        }
    }

    @Test
    fun `TRACE maps correctly`() {

        logger("all").trace("test-message")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.TRACE)
        }
    }

    @Test
    fun `correct OFF levels enabled`() {
        logger("off").let {
            assertFalse(it.isFatalEnabled())
            assertFalse(it.isErrorEnabled())
            assertFalse(it.isWarnEnabled())
            assertFalse(it.isInfoEnabled())
            assertFalse(it.isDebugEnabled())
            assertFalse(it.isTraceEnabled())
        }
    }

    @Test
    fun `correct FATAL levels enabled`() {
        logger("fatal").let {
            assertTrue(it.isFatalEnabled())
            assertFalse(it.isErrorEnabled())
            assertFalse(it.isWarnEnabled())
            assertFalse(it.isInfoEnabled())
            assertFalse(it.isDebugEnabled())
            assertFalse(it.isTraceEnabled())
        }
    }

    @Test
    fun `correct ERROR levels enabled`() {
        logger("error").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertFalse(it.isWarnEnabled())
            assertFalse(it.isInfoEnabled())
            assertFalse(it.isDebugEnabled())
            assertFalse(it.isTraceEnabled())
        }
    }

    @Test
    fun `correct WARN levels enabled`() {
        logger("warn").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertFalse(it.isInfoEnabled())
            assertFalse(it.isDebugEnabled())
            assertFalse(it.isTraceEnabled())
        }
    }

    @Test
    fun `correct INFO levels enabled`() {
        logger("info").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertFalse(it.isDebugEnabled())
            assertFalse(it.isTraceEnabled())
        }
    }

    @Test
    fun `correct DEBUG levels enabled`() {
        logger("debug").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertTrue(it.isDebugEnabled())
            assertFalse(it.isTraceEnabled())
        }
    }

    @Test
    fun `correct TRACE levels enabled`() {
        logger("trace").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertTrue(it.isDebugEnabled())
            assertTrue(it.isTraceEnabled())
        }
    }

    @Test
    fun `correct ALL levels enabled`() {
        logger("finer").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertTrue(it.isDebugEnabled())
            assertTrue(it.isTraceEnabled())
        }
    }

    @Test
    fun `FATAL with an empty tag is enabled`() {
        assertTrue(logger("all").isFatalEnabled(""))
    }

    @Test
    fun `ERROR with an empty tag is enabled`() {
        assertTrue(logger("all").isFatalEnabled(""))
    }

    @Test
    fun `WARN with an empty tag is enabled`() {
        assertTrue(logger("all").isFatalEnabled(""))
    }

    @Test
    fun `INFO with an empty tag is enabled`() {
        assertTrue(logger("all").isFatalEnabled(""))
    }

    @Test
    fun `DEBUG with an empty tag is enabled`() {
        assertTrue(logger("all").isFatalEnabled(""))
    }

    @Test
    fun `TRACE with an empty tag is enabled`() {
        assertTrue(logger("all").isFatalEnabled(""))
    }

    @Test
    fun `FATAL with the 'MARKER_DENY' tag is disabled`() {
        assertFalse(logger("all").isFatalEnabled("MARKER_DENY"))
    }

    @Test
    fun `ERROR with the 'MARKER_DENY' tag is disabled`() {
        assertFalse(logger("all").isErrorEnabled("MARKER_DENY"))
    }

    @Test
    fun `WARN with the 'MARKER_DENY' tag is disabled`() {
        assertFalse(logger("all").isWarnEnabled("MARKER_DENY"))
    }

    @Test
    fun `INFO with the 'MARKER_DENY' tag is disabled`() {
        assertFalse(logger("all").isInfoEnabled("MARKER_DENY"))
    }

    @Test
    fun `DEBUG with the 'MARKER_DENY' tag is disabled`() {
        assertFalse(logger("all").isDebugEnabled("MARKER_DENY"))
    }

    @Test
    fun `TRACE with the 'MARKER_DENY' tag is disabled`() {
        assertFalse(logger("all").isTraceEnabled("MARKER_DENY"))
    }

    @BeforeTest
    fun prepare() = entries.clear()

    companion object {

        val exception = Exception("test-exception")
    }
}

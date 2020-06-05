/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.proxy.test

import org.la4k.logger
import org.la4k.impl.Level
import org.la4k.proxy.isLevelEnabled
import org.la4k.proxy.logEntry

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class ProxyTests {

    init {

        logEntry = { name, level, message, throwable, tag ->
            entries.add(Entry(name, level, message, throwable, tag))
        }

        isLevelEnabled = { level, tag ->
            if (tag == "tag-disable") {
                false
            } else {
                when (level) {
                    Level.FATAL -> isFatalEnabled
                    Level.ERROR -> isErrorEnabled
                    Level.WARN -> isWarnEnabled
                    Level.INFO -> isInfoEnabled
                    Level.DEBUG -> isDebugEnabled
                    Level.TRACE -> isTraceEnabled
                }
            }
        }
    }

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
    fun `only FATAL is disabled`() {

        isFatalEnabled = false

        assertFalse(logger("test").isFatalEnabled())
        assertTrue(logger("test").isErrorEnabled())
        assertTrue(logger("test").isWarnEnabled())
        assertTrue(logger("test").isInfoEnabled())
        assertTrue(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only ERROR is disabled`() {

        isErrorEnabled = false

        assertTrue(logger("test").isFatalEnabled())
        assertFalse(logger("test").isErrorEnabled())
        assertTrue(logger("test").isWarnEnabled())
        assertTrue(logger("test").isInfoEnabled())
        assertTrue(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only WARN is disabled`() {

        isWarnEnabled = false

        assertTrue(logger("test").isFatalEnabled())
        assertTrue(logger("test").isErrorEnabled())
        assertFalse(logger("test").isWarnEnabled())
        assertTrue(logger("test").isInfoEnabled())
        assertTrue(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only INFO is disabled`() {

        isInfoEnabled = false

        assertTrue(logger("test").isFatalEnabled())
        assertTrue(logger("test").isErrorEnabled())
        assertTrue(logger("test").isWarnEnabled())
        assertFalse(logger("test").isInfoEnabled())
        assertTrue(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only DEBUG is disabled`() {

        isDebugEnabled = false

        assertTrue(logger("test").isFatalEnabled())
        assertTrue(logger("test").isErrorEnabled())
        assertTrue(logger("test").isWarnEnabled())
        assertTrue(logger("test").isInfoEnabled())
        assertFalse(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only TRACE is disabled`() {

        isTraceEnabled = false

        assertTrue(logger("test").isFatalEnabled())
        assertTrue(logger("test").isErrorEnabled())
        assertTrue(logger("test").isWarnEnabled())
        assertTrue(logger("test").isInfoEnabled())
        assertTrue(logger("test").isDebugEnabled())
        assertFalse(logger("test").isTraceEnabled())
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

        isFatalEnabled = true
        isErrorEnabled = true
        isWarnEnabled = true
        isInfoEnabled = true
        isDebugEnabled = true
        isTraceEnabled = true
    }

    companion object {

        var isFatalEnabled = true
        var isErrorEnabled = true
        var isWarnEnabled = true
        var isInfoEnabled = true
        var isDebugEnabled = true
        var isTraceEnabled = true

        val exception = Exception("test-exception")
    }
}

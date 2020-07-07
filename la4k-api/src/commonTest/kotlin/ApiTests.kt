/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.test

import org.la4k.logger
import org.la4k.test.*

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class ApiTests {

    init {

        logFatal = { name, message, throwable, tag ->
            fatalEntries.add(Entry(name, message, throwable, tag))
        }
        logError = { name, message, throwable, tag ->
            errorEntries.add(Entry(name, message, throwable, tag))
        }
        logWarn = { name, message, throwable, tag ->
            warnEntries.add(Entry(name, message, throwable, tag))
        }
        logInfo = { name, message, throwable, tag ->
            infoEntries.add(Entry(name, message, throwable, tag))
        }
        logDebug = { name, message, throwable, tag ->
            debugEntries.add(Entry(name, message, throwable, tag))
        }
        logTrace = { name, message, throwable, tag ->
            traceEntries.add(Entry(name, message, throwable, tag))
        }

        isFatalEnabled = { _, tag ->
            if (tag == "tag-disable")
                false
            else
                isFatalLevelEnabled
        }

        isErrorEnabled = { _, tag ->
            if (tag == "tag-disable")
                false
            else
                isErrorLevelEnabled
        }

        isWarnEnabled = { _, tag ->
            if (tag == "tag-disable")
                false
            else
                isWarnLevelEnabled
        }

        isInfoEnabled = { _, tag ->
            if (tag == "tag-disable")
                false
            else
                isInfoLevelEnabled
        }

        isDebugEnabled = { _, tag ->
            if (tag == "tag-disable")
                false
            else
                isDebugLevelEnabled
        }

        isTraceEnabled = { _, tag ->
            if (tag == "tag-disable")
                false
            else
                isTraceLevelEnabled
        }
    }

    @Test
    fun `logger separation`() {

        logger("fatal").fatal("fatal-test-message")
        logger("error").error("error-test-message", exception)
        logger("warn").warn("warn-test-message", null, "tag")
        logger("info").info("info-test-message", exception, "tag")

        assertTrue(fatalEntries.size == 1)
        assertTrue(errorEntries.size == 1)
        assertTrue(warnEntries.size == 1)
        assertTrue(infoEntries.size == 1)

        fatalEntries[0].let {
            assertTrue(it.name == "fatal")
            assertTrue(it.message == "fatal-test-message")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == null)
        }
        errorEntries[0].let {
            assertTrue(it.name == "error")
            assertTrue(it.message == "error-test-message")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == null)
        }
        warnEntries[0].let {
            assertTrue(it.name == "warn")
            assertTrue(it.message == "warn-test-message")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == "tag")
        }
        infoEntries[0].let {
            assertTrue(it.name == "info")
            assertTrue(it.message == "info-test-message")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == "tag")
        }
    }

    @Test
    fun `FATAL maps correctly`() {

        logger("all").fatal("test-message")

        assertTrue(fatalEntries.size == 1)
    }

    @Test
    fun `ERROR maps correctly`() {

        logger("all").error("test-message")

        assertTrue(errorEntries.size == 1)
    }

    @Test
    fun `WARN maps correctly`() {

        logger("all").warn("test-message")

        assertTrue(warnEntries.size == 1)
    }

    @Test
    fun `INFO maps correctly`() {

        logger("all").info("test-message")

        assertTrue(infoEntries.size == 1)
    }

    @Test
    fun `DEBUG maps correctly`() {

        logger("all").debug("test-message")

        assertTrue(debugEntries.size == 1)
    }

    @Test
    fun `TRACE maps correctly`() {

        logger("all").trace("test-message")

        assertTrue(traceEntries.size == 1)
    }

    @Test
    fun `only FATAL is disabled`() {

        isFatalLevelEnabled = false

        assertFalse(logger("test").isFatalEnabled())
        assertTrue(logger("test").isErrorEnabled())
        assertTrue(logger("test").isWarnEnabled())
        assertTrue(logger("test").isInfoEnabled())
        assertTrue(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only ERROR is disabled`() {

        isErrorLevelEnabled = false

        assertTrue(logger("test").isFatalEnabled())
        assertFalse(logger("test").isErrorEnabled())
        assertTrue(logger("test").isWarnEnabled())
        assertTrue(logger("test").isInfoEnabled())
        assertTrue(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only WARN is disabled`() {

        isWarnLevelEnabled = false

        assertTrue(logger("test").isFatalEnabled())
        assertTrue(logger("test").isErrorEnabled())
        assertFalse(logger("test").isWarnEnabled())
        assertTrue(logger("test").isInfoEnabled())
        assertTrue(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only INFO is disabled`() {

        isInfoLevelEnabled = false

        assertTrue(logger("test").isFatalEnabled())
        assertTrue(logger("test").isErrorEnabled())
        assertTrue(logger("test").isWarnEnabled())
        assertFalse(logger("test").isInfoEnabled())
        assertTrue(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only DEBUG is disabled`() {

        isDebugLevelEnabled = false

        assertTrue(logger("test").isFatalEnabled())
        assertTrue(logger("test").isErrorEnabled())
        assertTrue(logger("test").isWarnEnabled())
        assertTrue(logger("test").isInfoEnabled())
        assertFalse(logger("test").isDebugEnabled())
        assertTrue(logger("test").isTraceEnabled())
    }

    @Test
    fun `only TRACE is disabled`() {

        isTraceLevelEnabled = false

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

        fatalEntries.clear()
        errorEntries.clear()
        warnEntries.clear()
        infoEntries.clear()
        debugEntries.clear()
        traceEntries.clear()

        isFatalLevelEnabled = true
        isErrorLevelEnabled = true
        isWarnLevelEnabled = true
        isInfoLevelEnabled = true
        isDebugLevelEnabled = true
        isTraceLevelEnabled = true
    }

    companion object {

        var isFatalLevelEnabled = true
        var isErrorLevelEnabled = true
        var isWarnLevelEnabled = true
        var isInfoLevelEnabled = true
        var isDebugLevelEnabled = true
        var isTraceLevelEnabled = true

        val exception = Exception("test-exception")
    }
}

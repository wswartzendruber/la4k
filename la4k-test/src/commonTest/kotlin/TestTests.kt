/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.test.test

import org.la4k.logger
import org.la4k.test.clear
import org.la4k.test.count
import org.la4k.test.Event
import org.la4k.test.Level

import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class TestTests {

    @Test
    fun `logger separation`() {

        assertTrue(count(fatalEvent) == 0)
        assertTrue(count(errorEvent) == 0)
        assertTrue(count(warnEvent) == 0)
        assertTrue(count(infoEvent) == 0)
        assertTrue(count(debugEvent) == 0)
        assertTrue(count(traceEvent) == 0)

        logger("fatal").fatal("fatal message", exception, tag)
        logger("error").error("error message", exception, tag)
        logger("warn").warn("warn message", exception, tag)
        logger("info").info("info message", exception, tag)
        logger("debug").debug("debug message", exception, tag)
        logger("trace").trace("trace message", exception, tag)

        assertTrue(count(fatalEvent) == 1)
        assertTrue(count(errorEvent) == 1)
        assertTrue(count(warnEvent) == 1)
        assertTrue(count(infoEvent) == 1)
        assertTrue(count(debugEvent) == 1)
        assertTrue(count(traceEvent) == 1)
    }

    @Test
    fun `FATAL is always enabled`() {
        assertTrue(logger("test").isFatalEnabled())
    }

    @Test
    fun `ERROR is always enabled`() {
        assertTrue(logger("test").isErrorEnabled())
    }

    @Test
    fun `WARN is always enabled`() {
        assertTrue(logger("test").isWarnEnabled())
    }

    @Test
    fun `INFO is always enabled`() {
        assertTrue(logger("test").isInfoEnabled())
    }

    @Test
    fun `DEBUG is always enabled`() {
        assertTrue(logger("test").isDebugEnabled())
    }

    @Test
    fun `TRACE is always enabled`() {
        assertTrue(logger("test").isTraceEnabled())
    }

    @BeforeTest
    fun prepare() {
        clear()
    }

    companion object {

        val exception = Exception("test-exception")
        val tag = "TAG"
        val fatalEvent = Event("fatal", Level.FATAL, "fatal message", exception, tag)
        val errorEvent = Event("error", Level.ERROR, "error message", exception, tag)
        val warnEvent = Event("warn", Level.WARN, "warn message", exception, tag)
        val infoEvent = Event("info", Level.INFO, "info message", exception, tag)
        val debugEvent = Event("debug", Level.DEBUG, "debug message", exception, tag)
        val traceEvent = Event("trace", Level.TRACE, "trace message", exception, tag)
    }
}

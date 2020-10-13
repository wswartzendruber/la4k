/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
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

        assertTrue(count({ it == fatalEvent }) == 0)
        assertTrue(count({ it == errorEvent }) == 0)
        assertTrue(count({ it == warnEvent }) == 0)
        assertTrue(count({ it == infoEvent }) == 0)
        assertTrue(count({ it == debugEvent }) == 0)
        assertTrue(count({ it == traceEvent }) == 0)

        logger("fatal").fatal("fatal message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 1)
        assertTrue(count({ it == errorEvent }) == 0)
        assertTrue(count({ it == warnEvent }) == 0)
        assertTrue(count({ it == infoEvent }) == 0)
        assertTrue(count({ it == debugEvent }) == 0)
        assertTrue(count({ it == traceEvent }) == 0)

        logger("error").error("error message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 1)
        assertTrue(count({ it == errorEvent }) == 1)
        assertTrue(count({ it == warnEvent }) == 0)
        assertTrue(count({ it == infoEvent }) == 0)
        assertTrue(count({ it == debugEvent }) == 0)
        assertTrue(count({ it == traceEvent }) == 0)

        logger("warn").warn("warn message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 1)
        assertTrue(count({ it == errorEvent }) == 1)
        assertTrue(count({ it == warnEvent }) == 1)
        assertTrue(count({ it == infoEvent }) == 0)
        assertTrue(count({ it == debugEvent }) == 0)
        assertTrue(count({ it == traceEvent }) == 0)

        logger("info").info("info message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 1)
        assertTrue(count({ it == errorEvent }) == 1)
        assertTrue(count({ it == warnEvent }) == 1)
        assertTrue(count({ it == infoEvent }) == 1)
        assertTrue(count({ it == debugEvent }) == 0)
        assertTrue(count({ it == traceEvent }) == 0)

        logger("debug").debug("debug message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 1)
        assertTrue(count({ it == errorEvent }) == 1)
        assertTrue(count({ it == warnEvent }) == 1)
        assertTrue(count({ it == infoEvent }) == 1)
        assertTrue(count({ it == debugEvent }) == 1)
        assertTrue(count({ it == traceEvent }) == 0)

        logger("trace").trace("trace message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 1)
        assertTrue(count({ it == errorEvent }) == 1)
        assertTrue(count({ it == warnEvent }) == 1)
        assertTrue(count({ it == infoEvent }) == 1)
        assertTrue(count({ it == debugEvent }) == 1)
        assertTrue(count({ it == traceEvent }) == 1)

        logger("fatal").fatal("fatal message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 2)
        assertTrue(count({ it == errorEvent }) == 1)
        assertTrue(count({ it == warnEvent }) == 1)
        assertTrue(count({ it == infoEvent }) == 1)
        assertTrue(count({ it == debugEvent }) == 1)
        assertTrue(count({ it == traceEvent }) == 1)

        logger("error").error("error message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 2)
        assertTrue(count({ it == errorEvent }) == 2)
        assertTrue(count({ it == warnEvent }) == 1)
        assertTrue(count({ it == infoEvent }) == 1)
        assertTrue(count({ it == debugEvent }) == 1)
        assertTrue(count({ it == traceEvent }) == 1)

        logger("warn").warn("warn message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 2)
        assertTrue(count({ it == errorEvent }) == 2)
        assertTrue(count({ it == warnEvent }) == 2)
        assertTrue(count({ it == infoEvent }) == 1)
        assertTrue(count({ it == debugEvent }) == 1)
        assertTrue(count({ it == traceEvent }) == 1)

        logger("info").info("info message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 2)
        assertTrue(count({ it == errorEvent }) == 2)
        assertTrue(count({ it == warnEvent }) == 2)
        assertTrue(count({ it == infoEvent }) == 2)
        assertTrue(count({ it == debugEvent }) == 1)
        assertTrue(count({ it == traceEvent }) == 1)

        logger("debug").debug("debug message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 2)
        assertTrue(count({ it == errorEvent }) == 2)
        assertTrue(count({ it == warnEvent }) == 2)
        assertTrue(count({ it == infoEvent }) == 2)
        assertTrue(count({ it == debugEvent }) == 2)
        assertTrue(count({ it == traceEvent }) == 1)

        logger("trace").trace("trace message", exception, tag)

        assertTrue(count({ it == fatalEvent }) == 2)
        assertTrue(count({ it == errorEvent }) == 2)
        assertTrue(count({ it == warnEvent }) == 2)
        assertTrue(count({ it == infoEvent }) == 2)
        assertTrue(count({ it == debugEvent }) == 2)
        assertTrue(count({ it == traceEvent }) == 2)
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
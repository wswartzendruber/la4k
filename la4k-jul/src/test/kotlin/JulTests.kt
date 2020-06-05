/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.jul.test

import org.la4k.logger

import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.Logger
import java.util.logging.LogRecord
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class JulTests {

    @Test
    fun `logger separation`() {

        logger("severe").error("severe-test-message")
        logger("warning").warn("warning-test-message", exception)

        assertTrue(entries.size == 2)

        entries[0].let {
            assertTrue(it.name == "severe")
            assertTrue(it.level == Level.SEVERE)
            assertTrue(it.message == "severe-test-message")
            assertTrue(it.throwable == null)
        }
        entries[1].let {
            assertTrue(it.name == "warning")
            assertTrue(it.level == Level.WARNING)
            assertTrue(it.message == "warning-test-message")
            assertTrue(it.throwable == exception)
        }
    }

    @Test
    fun `FATAL maps to SEVERE`() {

        logger("all").fatal("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.SEVERE)
        }
    }

    @Test
    fun `ERROR maps to SEVERE`() {

        logger("all").error("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.SEVERE)
        }
    }

    @Test
    fun `WARN maps to WARNING`() {

        logger("all").warn("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.WARNING)
        }
    }

    @Test
    fun `INFO maps to INFO`() {

        logger("all").info("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.INFO)
        }
    }

    @Test
    fun `DEBUG maps to FINE`() {

        logger("all").debug("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.FINE)
        }
    }

    @Test
    fun `TRACE maps to FINER`() {

        logger("all").trace("test-message", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.FINER)
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
    fun `correct SEVERE levels enabled`() {
        logger("severe").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertFalse(it.isWarnEnabled())
            assertFalse(it.isInfoEnabled())
            assertFalse(it.isDebugEnabled())
            assertFalse(it.isTraceEnabled())
        }
    }

    @Test
    fun `correct WARNING levels enabled`() {
        logger("warning").let {
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
    fun `correct FINE levels enabled`() {
        logger("fine").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertTrue(it.isDebugEnabled())
            assertFalse(it.isTraceEnabled())
        }
    }

    @Test
    fun `correct FINER levels enabled`() {
        logger("finer").let {
            assertTrue(it.isFatalEnabled())
            assertTrue(it.isErrorEnabled())
            assertTrue(it.isWarnEnabled())
            assertTrue(it.isInfoEnabled())
            assertTrue(it.isDebugEnabled())
            assertTrue(it.isTraceEnabled())
        }
    }

    @BeforeTest
    fun prepare() = entries.clear()

    companion object {

        data class Entry(
            val name: String,
            val level: Level,
            val message: String,
            val throwable: Throwable? = null
        )

        object TestHandler : Handler() {

            override fun close() { }

            override fun flush() { }

            override fun publish(record: LogRecord) {
                entries.add(Entry(
                    record.loggerName,
                    record.level,
                    record.message,
                    record.thrown
                ))
            }
        }

        val entries = mutableListOf<Entry>()
        val exception = Exception("test-exception")
        val offLogger = Logger.getLogger("off")
        val severeLogger = Logger.getLogger("severe")
        val warningLogger = Logger.getLogger("warning")
        val infoLogger = Logger.getLogger("info")
        val fineLogger = Logger.getLogger("fine")
        val finerLogger = Logger.getLogger("finer")
        val allLogger = Logger.getLogger("all")

        init {

            offLogger.addHandler(TestHandler)
            severeLogger.addHandler(TestHandler)
            warningLogger.addHandler(TestHandler)
            infoLogger.addHandler(TestHandler)
            fineLogger.addHandler(TestHandler)
            finerLogger.addHandler(TestHandler)
            allLogger.addHandler(TestHandler)

            offLogger.level = Level.OFF
            severeLogger.level = Level.SEVERE
            warningLogger.level = Level.WARNING
            infoLogger.level = Level.INFO
            fineLogger.level = Level.FINE
            finerLogger.level = Level.FINER
            allLogger.level = Level.ALL
        }
    }
}

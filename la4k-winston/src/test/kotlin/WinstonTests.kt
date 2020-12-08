/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

package org.la4k.winston.test

import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.Test

import org.la4k.activateBridge
import org.la4k.logger
import org.la4k.winston.registerLogger
import org.la4k.winston.WinstonBridge
import org.la4k.winston.WinstonLoggerException

external fun require(module: String): dynamic

class WinstonTests {

    @Test
    fun fatal_is_enabled_initialized() {
        assertTrue(logger("initialized-test-logger").isFatalEnabled())
    }

    @Test
    fun error_is_enabled_initialized() {
        assertTrue(logger("initialized-test-logger").isErrorEnabled())
    }

    @Test
    fun warn_is_enabled_initialized() {
        assertTrue(logger("initialized-test-logger").isWarnEnabled())
    }

    @Test
    fun info_is_enabled_initialized() {
        assertTrue(logger("initialized-test-logger").isInfoEnabled())
    }

    @Test
    fun debug_is_disabled_initialized() {
        assertFalse(logger("initialized-test-logger").isDebugEnabled())
    }

    @Test
    fun trace_is_disabled_initialized() {
        assertFalse(logger("initialized-test-logger").isTraceEnabled())
    }

    @Test
    fun fatal_is_disabled_uninitialized() {
        assertFalse(logger("uninitialized-test-logger").isFatalEnabled())
    }

    @Test
    fun error_is_disabled_uninitialized() {
        assertFalse(logger("uninitialized-test-logger").isErrorEnabled())
    }

    @Test
    fun warn_is_disabled_uninitialized() {
        assertFalse(logger("uninitialized-test-logger").isWarnEnabled())
    }

    @Test
    fun info_is_disabled_uninitialized() {
        assertFalse(logger("uninitialized-test-logger").isInfoEnabled())
    }

    @Test
    fun debug_is_disabled_uninitialized() {
        assertFalse(logger("uninitialized-test-logger").isDebugEnabled())
    }

    @Test
    fun trace_is_disabled_uninitialized() {
        assertFalse(logger("uninitialized-test-logger").isTraceEnabled())
    }

    @Test
    fun forbid_post_logger_registration() {
        logger("unregistered-logger")
        assertFailsWith<WinstonLoggerException>("An initialized logger could be registered.") {
            registerLogger("unregistered-logger", null)
        }
    }

    companion object {

        init {

            val winston = require("winston")

            activateBridge(WinstonBridge())
            registerLogger("initialized-test-logger", js("""
                winston.createLogger({
                    level: 'info',
                    transports: [
                        new winston.transports.Console()
                    ],
                });
            """))
            logger("uninitialized-test-logger")
        }
    }
}

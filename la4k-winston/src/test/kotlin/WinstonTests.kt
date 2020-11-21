/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

package org.la4k.winston.test

import kotlin.test.assertFailsWith
import kotlin.test.Test

import org.la4k.activateBridge
import org.la4k.logger
import org.la4k.winston.registerLogger
import org.la4k.winston.WinstonBridge
import org.la4k.winston.WinstonLoggerException

external fun require(module: String): dynamic

class WinstonTests {

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
            val winstonLogger = js("""
                winston.createLogger({
                    transports: [
                        new winston.transports.Console()
                    ]
                });
            """)

            activateBridge(WinstonBridge())
            registerLogger("test-logger", winstonLogger)
        }
    }
}

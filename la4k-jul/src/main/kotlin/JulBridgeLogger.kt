/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.jul

import java.util.logging.Level as TargetLevel
import java.util.logging.Logger as TargetLogger

import org.la4k.impl.BridgeLogger
import org.la4k.impl.Level

public class JulBridgeLogger(name: String) : BridgeLogger(name) {

    private val logger = TargetLogger.getLogger(name)

    /**
     * Routes an event from an `org.la4k.Logger` instance to an internal
     * `java.util.logging.Logger` instance.
     *
     * The [level] is mapped to a `java.util.logging.Level` in the following manner:
     *
     * * FATAL -> SEVERE
     * * ERROR -> SEVERE
     * * WARN -> WARNING
     * * INFO -> INFO
     * * DEBUG -> FINE
     * * TRACE -> FINER
     *
     * The [message] is always converted to a string via its `toString()` method.
     *
     * The [throwable] is passed if it is not `null`.
     *
     * The [tag] is always discarded.
     */
    public override fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        if (throwable == null)
            logger.log(level.toTargetLevel(), message.toString())
        else
            logger.log(level.toTargetLevel(), message.toString(), throwable)
    }

    /**
     * Queries an internal `java.util.logging.Level` instance to determine if a logging level is
     * enabled for it and returns it.
     *
     * The [level] is mapped to a `java.util.logging.Level` in the following manner:
     *
     * * FATAL -> SEVERE
     * * ERROR -> SEVERE
     * * WARN -> WARNING
     * * INFO -> INFO
     * * DEBUG -> FINE
     * * TRACE -> FINER
     *
     * The [tag] is always discarded.
     */
    public override fun isEnabled(level: Level, tag: String?): Boolean =
        logger.isLoggable(level.toTargetLevel())

    private companion object {

        private fun Level.toTargetLevel() =
            when (this) {
                Level.FATAL -> TargetLevel.SEVERE
                Level.ERROR -> TargetLevel.SEVERE
                Level.WARN -> TargetLevel.WARNING
                Level.INFO -> TargetLevel.INFO
                Level.DEBUG -> TargetLevel.FINE
                Level.TRACE -> TargetLevel.FINER
            }
    }
}

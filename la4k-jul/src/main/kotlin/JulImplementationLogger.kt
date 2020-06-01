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

import org.la4k.impl.ImplementationLogger
import org.la4k.impl.Level

public class JulImplementationLogger(name: String) : ImplementationLogger(name) {

    private val logger = TargetLogger.getLogger(name)

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

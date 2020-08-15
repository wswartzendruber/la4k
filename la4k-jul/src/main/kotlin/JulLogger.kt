/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.jul

import org.la4k.Logger

import java.util.logging.Level as Level
import java.util.logging.Logger as JulLogger

public class JulLogger(name: String) : Logger(name) {

    private val logger = JulLogger.getLogger(name)

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            logger.log(Level.SEVERE, message.toString())
        else
            logger.log(Level.SEVERE, message.toString(), throwable)
    }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            logger.log(Level.SEVERE, message.toString())
        else
            logger.log(Level.SEVERE, message.toString(), throwable)
    }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            logger.log(Level.WARNING, message.toString())
        else
            logger.log(Level.WARNING, message.toString(), throwable)
    }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            logger.log(Level.INFO, message.toString())
        else
            logger.log(Level.INFO, message.toString(), throwable)
    }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            logger.log(Level.FINE, message.toString())
        else
            logger.log(Level.FINE, message.toString(), throwable)
    }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            logger.log(Level.FINER, message.toString())
        else
            logger.log(Level.FINER, message.toString(), throwable)
    }

    public override fun isFatalEnabled(tag: String?): Boolean = logger.isLoggable(Level.SEVERE)

    public override fun isErrorEnabled(tag: String?): Boolean = logger.isLoggable(Level.SEVERE)

    public override fun isWarnEnabled(tag: String?): Boolean = logger.isLoggable(Level.WARNING)

    public override fun isInfoEnabled(tag: String?): Boolean = logger.isLoggable(Level.INFO)

    public override fun isDebugEnabled(tag: String?): Boolean = logger.isLoggable(Level.FINE)

    public override fun isTraceEnabled(tag: String?): Boolean = logger.isLoggable(Level.FINER)
}

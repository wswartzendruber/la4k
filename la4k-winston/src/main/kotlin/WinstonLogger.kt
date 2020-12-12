/*
 * Copyright 2020 William Swartzendruber
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package org.la4k.winston

import org.la4k.Logger

private val loggers = mutableMapOf<String, dynamic>()

/**
 * Causes the logging output from the LA4K logger with the specified [name] to be forwarded to
 * the provided Winston [logger]. This can only be called once per [name] and must be called
 * before any logger with that [name] is initialized.
 */
public fun registerLogger(name: String, logger: dynamic): Unit {

    if (name in loggers.keys)
        throw WinstonLoggerException("LA4K logger is already bound to a Winston logger.")

    loggers[name] = logger
}

public class WinstonLogger internal constructor(name: String) : Logger(name) {

    private val logger = loggers.getOrPut(name, { null })

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (logger != null) {
            logger.error(message)
            if (throwable != null)
                logger.error(throwable)
        }
    }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (logger != null) {
            logger.error(message)
            if (throwable != null)
                logger.error(throwable)
        }
    }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (logger != null) {
            logger.warn(message)
            if (throwable != null)
                logger.warn(throwable)
        }
    }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (logger != null) {
            logger.info(message)
            if (throwable != null)
                logger.info(throwable)
        }
    }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (logger != null) {
            logger.debug(message)
            if (throwable != null)
                logger.debug(throwable)
        }
    }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (logger != null) {
            logger.silly(message)
            if (throwable != null)
                logger.silly(throwable)
        }
    }

    public override fun isFatalEnabled(tag: String?): Boolean =
        if (logger != null)
            logger.levels[logger.level] >= logger.levels["error"]
        else
            false

    public override fun isErrorEnabled(tag: String?): Boolean =
        if (logger != null)
            logger.levels[logger.level] >= logger.levels["error"]
        else
            false

    public override fun isWarnEnabled(tag: String?): Boolean =
        if (logger != null)
            logger.levels[logger.level] >= logger.levels["warn"]
        else
            false

    public override fun isInfoEnabled(tag: String?): Boolean =
        if (logger != null)
            logger.levels[logger.level] >= logger.levels["info"]
        else
            false

    public override fun isDebugEnabled(tag: String?): Boolean =
        if (logger != null)
            logger.levels[logger.level] >= logger.levels["debug"]
        else
            false

    public override fun isTraceEnabled(tag: String?): Boolean =
        if (logger != null)
            logger.levels[logger.level] >= logger.levels["silly"]
        else
            false
}

/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.test

import org.la4k.Logger

class TestLogger(name: String) : Logger(name) {

    override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit = logFatal(name, message, throwable, tag)

    override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit = logError(name, message, throwable, tag)

    override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit = logWarn(name, message, throwable, tag)

    override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit = logInfo(name, message, throwable, tag)

    override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit = logDebug(name, message, throwable, tag)

    override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit = logTrace(name, message, throwable, tag)

    override fun isFatalEnabled(tag: String?): Boolean = isFatalEnabled(name, tag)

    override fun isErrorEnabled(tag: String?): Boolean = isErrorEnabled(name, tag)

    override fun isWarnEnabled(tag: String?): Boolean = isWarnEnabled(name, tag)

    override fun isInfoEnabled(tag: String?): Boolean = isInfoEnabled(name, tag)

    override fun isDebugEnabled(tag: String?): Boolean = isDebugEnabled(name, tag)

    override fun isTraceEnabled(tag: String?): Boolean = isTraceEnabled(name, tag)
}

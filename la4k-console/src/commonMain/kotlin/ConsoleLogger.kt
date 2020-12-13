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

package org.la4k.console

import org.la4k.Level
import org.la4k.Logger

private var _level = Level.INFO

/**
 * Gets or sets the active level of the bridge. Only logging events at this level or higher will
 * be sent to the console.
 */
public var level: Level
    get() = platformSynchronized(_level) { _level }
    set(value) = platformSynchronized(_level) { _level = value }

public class ConsoleLogger internal constructor(name: String) : Logger(name) {

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (isFatalEnabled(tag))
            printEvent(Level.FATAL, message, throwable, tag)
    }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (isErrorEnabled(tag))
            printEvent(Level.ERROR, message, throwable, tag)
    }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (isWarnEnabled(tag))
            printEvent(Level.WARN, message, throwable, tag)
    }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (isInfoEnabled(tag))
            printEvent(Level.INFO, message, throwable, tag)
    }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (isDebugEnabled(tag))
            printEvent(Level.DEBUG, message, throwable, tag)
    }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (isTraceEnabled(tag))
            printEvent(Level.TRACE, message, throwable, tag)
    }

    public override fun isFatalEnabled(tag: String?): Boolean = Level.FATAL.rank <= level.rank

    public override fun isErrorEnabled(tag: String?): Boolean = Level.ERROR.rank <= level.rank

    public override fun isWarnEnabled(tag: String?): Boolean = Level.WARN.rank <= level.rank

    public override fun isInfoEnabled(tag: String?): Boolean = Level.INFO.rank <= level.rank

    public override fun isDebugEnabled(tag: String?): Boolean = Level.DEBUG.rank <= level.rank

    public override fun isTraceEnabled(tag: String?): Boolean = Level.TRACE.rank <= level.rank

    private fun printEvent(
        level: Level,
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ) {

        val source =
            if (tag != null)
                "$name[$tag]"
            else
                name

        println("$source - $level - $message")

        if (throwable != null)
            println("$source - $level - EXCEPTION: ${throwable.message}")
    }
}

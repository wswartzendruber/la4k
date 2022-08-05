/*
 * Copyright 2021 William Swartzendruber
 *
 * To the extent possible under law, the person who associated CC0 with this file has waived all
 * copyright and related or neighboring rights to this file.
 *
 * You should have received a copy of the CC0 legalcode along with this work. If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 *
 * SPDX-License-Identifier: CC0-1.0
 */

package org.la4k.slf4j.test

import org.slf4j.Logger
import org.slf4j.Marker
import org.slf4j.helpers.MessageFormatter

class TestLoggerAdapter(private val name: String) : Logger {

    override fun getName() = name

    override fun error(message: String) {
        if (isErrorEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "ERROR", message))
            }
        }
    }

    override fun error(message: String, throwable: Throwable) {
        if (isErrorEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "ERROR", message, throwable))
            }
        }
    }

    override fun error(format: String, argument: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "ERROR", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun error(format: String, argument1: Any, argument2: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "ERROR", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun error(format: String, vararg arguments: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "ERROR", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun error(marker: Marker, message: String) {
        if (isErrorEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "ERROR", message, null, marker.name))
            }
        }
    }

    override fun error(marker: Marker, message: String, throwable: Throwable) {
        if (isErrorEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "ERROR", message, throwable, marker.name))
            }
        }
    }

    override fun error(marker: Marker, format: String, argument: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "ERROR", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun error(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "ERROR", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun error(marker: Marker, format: String, vararg arguments: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "ERROR", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun isErrorEnabled() = errorEnabled

    override fun isErrorEnabled(marker: Marker) = marker.toString().length < 1 && errorEnabled

    override fun warn(message: String) {
        if (isWarnEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "WARN", message))
            }
        }
    }

    override fun warn(message: String, throwable: Throwable) {
        if (isWarnEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "WARN", message, throwable))
            }
        }
    }

    override fun warn(format: String, argument: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "WARN", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun warn(format: String, argument1: Any, argument2: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "WARN", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun warn(format: String, vararg arguments: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "WARN", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun warn(marker: Marker, message: String) {
        if (isWarnEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "WARN", message, null, marker.name))
            }
        }
    }

    override fun warn(marker: Marker, message: String, throwable: Throwable) {
        if (isWarnEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "WARN", message, throwable, marker.name))
            }
        }
    }

    override fun warn(marker: Marker, format: String, argument: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "WARN", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun warn(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "WARN", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun warn(marker: Marker, format: String, vararg arguments: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "WARN", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun isWarnEnabled() = warnEnabled

    override fun isWarnEnabled(marker: Marker) = marker.toString().length < 1 && warnEnabled

    override fun info(message: String) {
        if (isInfoEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "INFO", message))
            }
        }
    }

    override fun info(message: String, throwable: Throwable) {
        if (isInfoEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "INFO", message, throwable))
            }
        }
    }

    override fun info(format: String, argument: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "INFO", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun info(format: String, argument1: Any, argument2: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "INFO", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun info(format: String, vararg arguments: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "INFO", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun info(marker: Marker, message: String) {
        if (isInfoEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "INFO", message, null, marker.name))
            }
        }
    }

    override fun info(marker: Marker, message: String, throwable: Throwable) {
        if (isInfoEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "INFO", message, throwable, marker.name))
            }
        }
    }

    override fun info(marker: Marker, format: String, argument: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "INFO", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun info(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "INFO", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun info(marker: Marker, format: String, vararg arguments: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "INFO", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun isInfoEnabled() = infoEnabled

    override fun isInfoEnabled(marker: Marker) = marker.toString().length < 1 && infoEnabled

    override fun debug(message: String) {
        if (isDebugEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "DEBUG", message))
            }
        }
    }

    override fun debug(message: String, throwable: Throwable) {
        if (isDebugEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "DEBUG", message, throwable))
            }
        }
    }

    override fun debug(format: String, argument: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "DEBUG", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun debug(format: String, argument1: Any, argument2: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "DEBUG", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun debug(format: String, vararg arguments: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "DEBUG", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun debug(marker: Marker, message: String) {
        if (isDebugEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "DEBUG", message, null, marker.name))
            }
        }
    }

    override fun debug(marker: Marker, message: String, throwable: Throwable) {
        if (isDebugEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "DEBUG", message, throwable, marker.name))
            }
        }
    }

    override fun debug(marker: Marker, format: String, argument: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "DEBUG", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun debug(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "DEBUG", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun debug(marker: Marker, format: String, vararg arguments: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "DEBUG", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun isDebugEnabled() = debugEnabled

    override fun isDebugEnabled(marker: Marker) = marker.toString().length < 1 && debugEnabled

    override fun trace(message: String) {
        if (isTraceEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "TRACE", message))
            }
        }
    }

    override fun trace(message: String, throwable: Throwable) {
        if (isTraceEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "TRACE", message, throwable))
            }
        }
    }

    override fun trace(format: String, argument: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "TRACE", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun trace(format: String, argument1: Any, argument2: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "TRACE", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun trace(format: String, vararg arguments: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "TRACE", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun trace(marker: Marker, message: String) {
        if (isTraceEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "TRACE", message, null, marker.name))
            }
        }
    }

    override fun trace(marker: Marker, message: String, throwable: Throwable) {
        if (isTraceEnabled) {
            synchronized(entries) {
                entries.add(Entry(name, "TRACE", message, throwable, marker.name))
            }
        }
    }

    override fun trace(marker: Marker, format: String, argument: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "TRACE", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun trace(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "TRACE", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun trace(marker: Marker, format: String, vararg arguments: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(entries) {
                    entries.add(Entry(name, "TRACE", mf.message, mf.throwable, marker.name))
                }
            }
        }
    }

    override fun isTraceEnabled() = traceEnabled

    override fun isTraceEnabled(marker: Marker) = marker.toString().length < 1 && traceEnabled

    companion object {

        var errorEnabled = true
        var warnEnabled = true
        var infoEnabled = true
        var debugEnabled = true
        var traceEnabled = true

        fun reset() {
            errorEnabled = true
            warnEnabled = true
            infoEnabled = true
            debugEnabled = true
            traceEnabled = true
        }
    }
}

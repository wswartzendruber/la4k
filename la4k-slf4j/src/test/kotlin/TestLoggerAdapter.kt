/*
 * Copyright 2020 William Swartzendruber
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.la4k.slf4j.test

import org.slf4j.Logger
import org.slf4j.Marker
import org.slf4j.helpers.MessageFormatter

class TestLoggerAdapter(private val name: String) : Logger {

    private val loggerMessages = messages.getOrPut(name, { mutableListOf<Message>() })

    override fun getName() = name

    override fun error(message: String) {
        if (isErrorEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("ERROR", message))
            }
        }
    }

    override fun error(message: String, throwable: Throwable) {
        if (isErrorEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("ERROR", message, throwable))
            }
        }
    }

    override fun error(format: String, argument: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("ERROR", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun error(format: String, argument1: Any, argument2: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("ERROR", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun error(format: String, vararg arguments: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("ERROR", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun error(marker: Marker, message: String) {
        if (isErrorEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("ERROR", message, null, marker))
            }
        }
    }

    override fun error(marker: Marker, message: String, throwable: Throwable) {
        if (isErrorEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("ERROR", message, throwable, marker))
            }
        }
    }

    override fun error(marker: Marker, format: String, argument: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("ERROR", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun error(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("ERROR", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun error(marker: Marker, format: String, vararg arguments: Any) {
        if (isErrorEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("ERROR", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun isErrorEnabled() = true

    override fun isErrorEnabled(marker: Marker) = marker.toString().length > 1

    override fun warn(message: String) {
        if (isWarnEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("WARN", message))
            }
        }
    }

    override fun warn(message: String, throwable: Throwable) {
        if (isWarnEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("WARN", message, throwable))
            }
        }
    }

    override fun warn(format: String, argument: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("WARN", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun warn(format: String, argument1: Any, argument2: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("WARN", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun warn(format: String, vararg arguments: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("WARN", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun warn(marker: Marker, message: String) {
        if (isWarnEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("WARN", message, null, marker))
            }
        }
    }

    override fun warn(marker: Marker, message: String, throwable: Throwable) {
        if (isWarnEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("WARN", message, throwable, marker))
            }
        }
    }

    override fun warn(marker: Marker, format: String, argument: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("WARN", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun warn(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("WARN", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun warn(marker: Marker, format: String, vararg arguments: Any) {
        if (isWarnEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("WARN", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun isWarnEnabled() = true

    override fun isWarnEnabled(marker: Marker) = marker.toString().length > 1

    override fun info(message: String) {
        if (isInfoEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("INFO", message))
            }
        }
    }

    override fun info(message: String, throwable: Throwable) {
        if (isInfoEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("INFO", message, throwable))
            }
        }
    }

    override fun info(format: String, argument: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("INFO", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun info(format: String, argument1: Any, argument2: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("INFO", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun info(format: String, vararg arguments: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("INFO", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun info(marker: Marker, message: String) {
        if (isInfoEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("INFO", message, null, marker))
            }
        }
    }

    override fun info(marker: Marker, message: String, throwable: Throwable) {
        if (isInfoEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("INFO", message, throwable, marker))
            }
        }
    }

    override fun info(marker: Marker, format: String, argument: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("INFO", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun info(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("INFO", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun info(marker: Marker, format: String, vararg arguments: Any) {
        if (isInfoEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("INFO", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun isInfoEnabled() = true

    override fun isInfoEnabled(marker: Marker) = marker.toString().length > 1

    override fun debug(message: String) {
        if (isDebugEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("DEBUG", message))
            }
        }
    }

    override fun debug(message: String, throwable: Throwable) {
        if (isDebugEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("DEBUG", message, throwable))
            }
        }
    }

    override fun debug(format: String, argument: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("DEBUG", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun debug(format: String, argument1: Any, argument2: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("DEBUG", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun debug(format: String, vararg arguments: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("DEBUG", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun debug(marker: Marker, message: String) {
        if (isDebugEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("DEBUG", message, null, marker))
            }
        }
    }

    override fun debug(marker: Marker, message: String, throwable: Throwable) {
        if (isDebugEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("DEBUG", message, throwable, marker))
            }
        }
    }

    override fun debug(marker: Marker, format: String, argument: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("DEBUG", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun debug(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("DEBUG", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun debug(marker: Marker, format: String, vararg arguments: Any) {
        if (isDebugEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("DEBUG", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun isDebugEnabled() = true

    override fun isDebugEnabled(marker: Marker) = marker.toString().length > 1

    override fun trace(message: String) {
        if (isTraceEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("TRACE", message))
            }
        }
    }

    override fun trace(message: String, throwable: Throwable) {
        if (isTraceEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("TRACE", message, throwable))
            }
        }
    }

    override fun trace(format: String, argument: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("TRACE", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun trace(format: String, argument1: Any, argument2: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("TRACE", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun trace(format: String, vararg arguments: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("TRACE", mf.message, mf.throwable))
                }
            }
        }
    }

    override fun trace(marker: Marker, message: String) {
        if (isTraceEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("TRACE", message, null, marker))
            }
        }
    }

    override fun trace(marker: Marker, message: String, throwable: Throwable) {
        if (isTraceEnabled) {
            synchronized(messages) {
                loggerMessages.add(Message("TRACE", message, throwable, marker))
            }
        }
    }

    override fun trace(marker: Marker, format: String, argument: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, argument).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("TRACE", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun trace(marker: Marker, format: String, argument1: Any, argument2: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, argument1, argument2).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("TRACE", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun trace(marker: Marker, format: String, vararg arguments: Any) {
        if (isTraceEnabled) {
            MessageFormatter.format(format, arguments).let { mf ->
                synchronized(messages) {
                    loggerMessages.add(Message("TRACE", mf.message, mf.throwable, marker))
                }
            }
        }
    }

    override fun isTraceEnabled() = true

    override fun isTraceEnabled(marker: Marker) = marker.toString().length > 1
}

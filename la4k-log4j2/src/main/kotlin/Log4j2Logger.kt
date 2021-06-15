/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.log4j2

import org.la4k.Logger

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Marker
import org.apache.logging.log4j.MarkerManager

public class Log4j2Logger internal constructor(name: String) : Logger(name) {

    private val logger = LogManager.getLogger(name)
    private val markers = mutableMapOf<String, Marker>()

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null) {
                when (message) {
                    is String -> logger.fatal(message)
                    is CharSequence -> logger.fatal(message)
                    else -> logger.fatal(message)
                }
            } else {
                when (message) {
                    is String -> logger.fatal(tag.toMarker(), message)
                    is CharSequence -> logger.fatal(tag.toMarker(), message)
                    else -> logger.fatal(tag.toMarker(), message)
                }
            }
        } else {
            if (tag == null) {
                when (message) {
                    is String -> logger.fatal(message, throwable)
                    is CharSequence -> logger.fatal(message, throwable)
                    else -> logger.fatal(message, throwable)
                }
            } else {
                when (message) {
                    is String -> logger.fatal(tag.toMarker(), message, throwable)
                    is CharSequence -> logger.fatal(tag.toMarker(), message, throwable)
                    else -> logger.fatal(tag.toMarker(), message, throwable)
                }
            }
        }
    }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null) {
                when (message) {
                    is String -> logger.error(message)
                    is CharSequence -> logger.error(message)
                    else -> logger.error(message)
                }
            } else {
                when (message) {
                    is String -> logger.error(tag.toMarker(), message)
                    is CharSequence -> logger.error(tag.toMarker(), message)
                    else -> logger.error(tag.toMarker(), message)
                }
            }
        } else {
            if (tag == null) {
                when (message) {
                    is String -> logger.error(message, throwable)
                    is CharSequence -> logger.error(message, throwable)
                    else -> logger.error(message, throwable)
                }
            } else {
                when (message) {
                    is String -> logger.error(tag.toMarker(), message, throwable)
                    is CharSequence -> logger.error(tag.toMarker(), message, throwable)
                    else -> logger.error(tag.toMarker(), message, throwable)
                }
            }
        }
    }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null) {
                when (message) {
                    is String -> logger.warn(message)
                    is CharSequence -> logger.warn(message)
                    else -> logger.warn(message)
                }
            } else {
                when (message) {
                    is String -> logger.warn(tag.toMarker(), message)
                    is CharSequence -> logger.warn(tag.toMarker(), message)
                    else -> logger.warn(tag.toMarker(), message)
                }
            }
        } else {
            if (tag == null) {
                when (message) {
                    is String -> logger.warn(message, throwable)
                    is CharSequence -> logger.warn(message, throwable)
                    else -> logger.warn(message, throwable)
                }
            } else {
                when (message) {
                    is String -> logger.warn(tag.toMarker(), message, throwable)
                    is CharSequence -> logger.warn(tag.toMarker(), message, throwable)
                    else -> logger.warn(tag.toMarker(), message, throwable)
                }
            }
        }
    }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null) {
                when (message) {
                    is String -> logger.info(message)
                    is CharSequence -> logger.info(message)
                    else -> logger.info(message)
                }
            } else {
                when (message) {
                    is String -> logger.info(tag.toMarker(), message)
                    is CharSequence -> logger.info(tag.toMarker(), message)
                    else -> logger.info(tag.toMarker(), message)
                }
            }
        } else {
            if (tag == null) {
                when (message) {
                    is String -> logger.info(message, throwable)
                    is CharSequence -> logger.info(message, throwable)
                    else -> logger.info(message, throwable)
                }
            } else {
                when (message) {
                    is String -> logger.info(tag.toMarker(), message, throwable)
                    is CharSequence -> logger.info(tag.toMarker(), message, throwable)
                    else -> logger.info(tag.toMarker(), message, throwable)
                }
            }
        }
    }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null) {
                when (message) {
                    is String -> logger.debug(message)
                    is CharSequence -> logger.debug(message)
                    else -> logger.debug(message)
                }
            } else {
                when (message) {
                    is String -> logger.debug(tag.toMarker(), message)
                    is CharSequence -> logger.debug(tag.toMarker(), message)
                    else -> logger.debug(tag.toMarker(), message)
                }
            }
        } else {
            if (tag == null) {
                when (message) {
                    is String -> logger.debug(message, throwable)
                    is CharSequence -> logger.debug(message, throwable)
                    else -> logger.debug(message, throwable)
                }
            } else {
                when (message) {
                    is String -> logger.debug(tag.toMarker(), message, throwable)
                    is CharSequence -> logger.debug(tag.toMarker(), message, throwable)
                    else -> logger.debug(tag.toMarker(), message, throwable)
                }
            }
        }
    }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null) {
                when (message) {
                    is String -> logger.trace(message)
                    is CharSequence -> logger.trace(message)
                    else -> logger.trace(message)
                }
            } else {
                when (message) {
                    is String -> logger.trace(tag.toMarker(), message)
                    is CharSequence -> logger.trace(tag.toMarker(), message)
                    else -> logger.trace(tag.toMarker(), message)
                }
            }
        } else {
            if (tag == null) {
                when (message) {
                    is String -> logger.trace(message, throwable)
                    is CharSequence -> logger.trace(message, throwable)
                    else -> logger.trace(message, throwable)
                }
            } else {
                when (message) {
                    is String -> logger.trace(tag.toMarker(), message, throwable)
                    is CharSequence -> logger.trace(tag.toMarker(), message, throwable)
                    else -> logger.trace(tag.toMarker(), message, throwable)
                }
            }
        }
    }

    public override fun isFatalEnabled(tag: String?): Boolean =
        if (tag == null)
            logger.isFatalEnabled()
        else
            logger.isFatalEnabled(tag.toMarker())

    public override fun isErrorEnabled(tag: String?): Boolean =
        if (tag == null)
            logger.isErrorEnabled()
        else
            logger.isErrorEnabled(tag.toMarker())

    public override fun isWarnEnabled(tag: String?): Boolean =
        if (tag == null)
            logger.isWarnEnabled()
        else
            logger.isWarnEnabled(tag.toMarker())

    public override fun isInfoEnabled(tag: String?): Boolean =
        if (tag == null)
            logger.isInfoEnabled()
        else
            logger.isInfoEnabled(tag.toMarker())

    public override fun isDebugEnabled(tag: String?): Boolean =
        if (tag == null)
            logger.isDebugEnabled()
        else
            logger.isDebugEnabled(tag.toMarker())

    public override fun isTraceEnabled(tag: String?): Boolean =
        if (tag == null)
            logger.isTraceEnabled()
        else
            logger.isTraceEnabled(tag.toMarker())

    private fun String.toMarker() =
        synchronized(markers) {
            markers.getOrPut(this, { MarkerManager.getMarker(this) })
        }
}

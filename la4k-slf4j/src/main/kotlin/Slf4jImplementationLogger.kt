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

package org.la4k.slf4j

import org.la4k.impl.Level
import org.la4k.impl.ImplementationLogger

import org.slf4j.LoggerFactory as TargetLoggerFactory
import org.slf4j.Marker as TargetMarker
import org.slf4j.MarkerFactory as TargetMarkerFactory

public class Slf4jImplementationLogger(name: String) : ImplementationLogger(name) {

    private val logger = TargetLoggerFactory.getLogger(name)
    private val markers = mutableMapOf<String, TargetMarker>()

    public override fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        when {
            throwable == null && tag == null -> {

                val targetMessage = message.toString()

                when (level) {
                    Level.FATAL -> logger.error(targetMessage)
                    Level.ERROR -> logger.error(targetMessage)
                    Level.WARN -> logger.warn(targetMessage)
                    Level.INFO -> logger.info(targetMessage)
                    Level.DEBUG -> logger.debug(targetMessage)
                    Level.TRACE -> logger.trace(targetMessage)
                }
            }
            throwable == null && tag != null -> {

                val targetMarker = tag.toTargetMarker()
                val targetMessage = message.toString()

                when (level) {
                    Level.FATAL -> logger.error(targetMarker, targetMessage)
                    Level.ERROR -> logger.error(targetMarker, targetMessage)
                    Level.WARN -> logger.warn(targetMarker, targetMessage)
                    Level.INFO -> logger.info(targetMarker, targetMessage)
                    Level.DEBUG -> logger.debug(targetMarker, targetMessage)
                    Level.TRACE -> logger.trace(targetMarker, targetMessage)
                }
            }
            throwable != null && tag == null -> {

                val targetMessage = message.toString()

                when (level) {
                    Level.FATAL -> logger.error(targetMessage, throwable)
                    Level.ERROR -> logger.error(targetMessage, throwable)
                    Level.WARN -> logger.warn(targetMessage, throwable)
                    Level.INFO -> logger.info(targetMessage, throwable)
                    Level.DEBUG -> logger.debug(targetMessage, throwable)
                    Level.TRACE -> logger.trace(targetMessage, throwable)
                }
            }
            throwable != null && tag != null -> {

                val targetMarker = tag.toTargetMarker()
                val targetMessage = message.toString()

                when (level) {
                    Level.FATAL -> logger.error(targetMarker, targetMessage, throwable)
                    Level.ERROR -> logger.error(targetMarker, targetMessage, throwable)
                    Level.WARN -> logger.warn(targetMarker, targetMessage, throwable)
                    Level.INFO -> logger.info(targetMarker, targetMessage, throwable)
                    Level.DEBUG -> logger.debug(targetMarker, targetMessage, throwable)
                    Level.TRACE -> logger.trace(targetMarker, targetMessage, throwable)
                }
            }
        }
    }

    public override fun isEnabled(level: Level, tag: String?) =
        if (tag == null) {
            when (level) {
                Level.FATAL -> logger.isErrorEnabled()
                Level.ERROR -> logger.isErrorEnabled()
                Level.WARN -> logger.isWarnEnabled()
                Level.INFO -> logger.isInfoEnabled()
                Level.DEBUG -> logger.isDebugEnabled()
                Level.TRACE -> logger.isTraceEnabled()
            }
        } else {
            tag.toTargetMarker().let { targetMarker ->
                when (level) {
                    Level.FATAL -> logger.isErrorEnabled(targetMarker)
                    Level.ERROR -> logger.isErrorEnabled(targetMarker)
                    Level.WARN -> logger.isWarnEnabled(targetMarker)
                    Level.INFO -> logger.isInfoEnabled(targetMarker)
                    Level.DEBUG -> logger.isDebugEnabled(targetMarker)
                    Level.TRACE -> logger.isTraceEnabled(targetMarker)
                }
            }
        }

    private fun String.toTargetMarker() =
        synchronized(markers) {
            markers.getOrPut(this, { TargetMarkerFactory.getDetachedMarker(this) })
        }
}

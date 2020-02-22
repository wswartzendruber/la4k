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

package org.la4k.log4j2

import org.la4k.impl.Level
import org.la4k.impl.ImplementationLogger

import org.apache.logging.log4j.Level as TargetLevel
import org.apache.logging.log4j.LogManager as TargetLogManager
import org.apache.logging.log4j.Marker as TargetMarker
import org.apache.logging.log4j.MarkerManager as TargetMarkerManager

public class Log4j2ImplementationLogger(name: String) : ImplementationLogger(name) {

    private val logger = TargetLogManager.getLogger(name)
    private val markers = mutableMapOf<String, TargetMarker>()

    public override fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    ) {
        when {
            throwable == null && tag == null -> {
                logger.log(level.toTargetLevel(), message)
            }
            throwable == null && tag != null -> {
                logger.log(level.toTargetLevel(), tag.toTargetMarker(), message)
            }
            throwable != null && tag == null -> {
                logger.log(level.toTargetLevel(), message, throwable)
            }
            throwable != null && tag != null -> {
                logger.log(level.toTargetLevel(), tag.toTargetMarker(), message, throwable)
            }
        }
    }

    public override fun isEnabled(level: Level, tag: String?) =
        if (tag == null)
            logger.isEnabled(level.toTargetLevel())
        else
            logger.isEnabled(level.toTargetLevel(), tag.toTargetMarker())

    private fun String.toTargetMarker() =
        synchronized(markers) {
            markers.getOrPut(this, { TargetMarkerManager.getMarker(this) })
        }

    private companion object {

        private fun Level.toTargetLevel() =
            when (this) {
                Level.FATAL -> TargetLevel.FATAL
                Level.ERROR -> TargetLevel.ERROR
                Level.WARN -> TargetLevel.WARN
                Level.INFO -> TargetLevel.INFO
                Level.DEBUG -> TargetLevel.DEBUG
                Level.TRACE -> TargetLevel.TRACE
            }
    }
}

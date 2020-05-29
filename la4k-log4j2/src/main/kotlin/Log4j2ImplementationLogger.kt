/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
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
    ): Unit {
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

    public override fun isEnabled(level: Level, tag: String?): Boolean =
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
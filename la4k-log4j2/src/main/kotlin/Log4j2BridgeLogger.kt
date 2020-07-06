/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.log4j2

import org.la4k.impl.Level
import org.la4k.impl.BridgeLogger

import org.apache.logging.log4j.Level as TargetLevel
import org.apache.logging.log4j.LogManager as TargetLogManager
import org.apache.logging.log4j.Marker as TargetMarker
import org.apache.logging.log4j.MarkerManager as TargetMarkerManager

public class Log4j2BridgeLogger(name: String) : BridgeLogger(name) {

    private val logger = TargetLogManager.getLogger(name)
    private val markers = mutableMapOf<String, TargetMarker>()

    /**
     * Routes an event from an `org.la4k.Logger` instance to an internal
     * `org.apache.logging.log4j.Logger` instance.
     *
     * As `org.la4k.impl.Level` is modeled after `org.apache.logging.log4j.Level`, no [level]
     * mapping needs to take place.
     *
     * The [message] is passed into the internal `org.apache.logging.log4j.Logger` instance as
     * either a `String`, a `CharSequence`, or `Any?` depending on its underlying type, and by
     * checking in that order.
     *
     * The [throwable] is passed if it is not `null`.
     *
     * The [tag] is converted into an `org.apache.logging.log4j.Marker` and cached for later use
     * by this instance.
     */
    public override fun log(
        level: Level,
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        when {
            throwable == null && tag == null -> {
                when (message) {
                    is String ->
                        logger.log(
                            level.toTargetLevel(),
                            message
                        )
                    is CharSequence ->
                        logger.log(
                            level.toTargetLevel(),
                            message
                        )
                    else ->
                        logger.log(
                            level.toTargetLevel(),
                            message
                        )
                }
            }
            throwable == null && tag != null -> {
                when (message) {
                    is String ->
                        logger.log(
                            level.toTargetLevel(),
                            tag.toTargetMarker(),
                            message
                        )
                    is CharSequence ->
                        logger.log(
                            level.toTargetLevel(),
                            tag.toTargetMarker(),
                            message
                        )
                    else ->
                        logger.log(
                            level.toTargetLevel(),
                            tag.toTargetMarker(),
                            message
                        )
                }
            }
            throwable != null && tag == null -> {
                when (message) {
                    is String ->
                        logger.log(
                            level.toTargetLevel(),
                            message,
                            throwable
                        )
                    is CharSequence ->
                        logger.log(
                            level.toTargetLevel(),
                            message,
                            throwable
                        )
                    else ->
                        logger.log(
                            level.toTargetLevel(),
                            message,
                            throwable
                        )
                }
            }
            throwable != null && tag != null -> {
                when (message) {
                    is String ->
                        logger.log(
                            level.toTargetLevel(),
                            tag.toTargetMarker(),
                            message,
                            throwable
                        )
                    is CharSequence ->
                        logger.log(
                            level.toTargetLevel(),
                            tag.toTargetMarker(),
                            message,
                            throwable
                        )
                    else ->
                        logger.log(
                            level.toTargetLevel(),
                            tag.toTargetMarker(),
                            message,
                            throwable
                        )
                }
            }
        }
    }

    /**
     * Queries an internal `org.apache.logging.log4j.Logger` instance to determine if a logging
     * level is enabled for it and returns it.
     *
     * As `org.la4k.impl.Level` is modeled after `org.apache.logging.log4j.Level`, no [level]
     * mapping needs to take place.
     *
     * The [tag] is converted into an `org.apache.logging.log4j.Marker` and cached for later use
     * by this instance.
     */
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

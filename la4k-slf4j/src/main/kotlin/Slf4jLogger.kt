/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.slf4j

import org.la4k.Logger

import org.slf4j.LoggerFactory
import org.slf4j.Marker
import org.slf4j.MarkerFactory

public class Slf4jLogger(name: String) : Logger(name) {

    private val logger = LoggerFactory.getLogger(name)
    private val markers = mutableMapOf<String, Marker>()

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null)
                logger.error(message.toString())
            else
                logger.error(tag.toMarker(), message.toString())
        } else {
            if (tag == null)
                logger.error(message.toString(), throwable)
            else
                logger.error(tag.toMarker(), message.toString(), throwable)
        }
    }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null)
                logger.error(message.toString())
            else
                logger.error(tag.toMarker(), message.toString())
        } else {
            if (tag == null)
                logger.error(message.toString(), throwable)
            else
                logger.error(tag.toMarker(), message.toString(), throwable)
        }
    }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null)
                logger.warn(message.toString())
            else
                logger.warn(tag.toMarker(), message.toString())
        } else {
            if (tag == null)
                logger.warn(message.toString(), throwable)
            else
                logger.warn(tag.toMarker(), message.toString(), throwable)
        }
    }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null)
                logger.info(message.toString())
            else
                logger.info(tag.toMarker(), message.toString())
        } else {
            if (tag == null)
                logger.info(message.toString(), throwable)
            else
                logger.info(tag.toMarker(), message.toString(), throwable)
        }
    }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null)
                logger.debug(message.toString())
            else
                logger.debug(tag.toMarker(), message.toString())
        } else {
            if (tag == null)
                logger.debug(message.toString(), throwable)
            else
                logger.debug(tag.toMarker(), message.toString(), throwable)
        }
    }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null) {
            if (tag == null)
                logger.trace(message.toString())
            else
                logger.trace(tag.toMarker(), message.toString())
        } else {
            if (tag == null)
                logger.trace(message.toString(), throwable)
            else
                logger.trace(tag.toMarker(), message.toString(), throwable)
        }
    }

    public override fun isFatalEnabled(tag: String?): Boolean =
        if (tag == null)
            logger.isErrorEnabled()
        else
            logger.isErrorEnabled(tag.toMarker())

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
            markers.getOrPut(this, { MarkerFactory.getDetachedMarker(this) })
        }
}

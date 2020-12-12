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

package org.la4k.slf4j

import org.la4k.Logger

import org.slf4j.LoggerFactory
import org.slf4j.Marker
import org.slf4j.MarkerFactory

public class Slf4jLogger internal constructor(name: String) : Logger(name) {

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

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

package org.slf4j.impl

import org.la4k.slf4j.test.TestLoggerFactory

import org.slf4j.ILoggerFactory
import org.slf4j.spi.LoggerFactoryBinder

class StaticLoggerBinder() : LoggerFactoryBinder {

    private val loggerFactory = TestLoggerFactory()

    override fun getLoggerFactory(): ILoggerFactory = loggerFactory

    override fun getLoggerFactoryClassStr() = this::class.qualifiedName

    companion object {

        @JvmField
        val REQUESTED_API_VERSION = "1.6.99"

        @JvmStatic
        val singleton = StaticLoggerBinder()
    }
}

/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
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

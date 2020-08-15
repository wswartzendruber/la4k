/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

package org.la4k.slf4j.test

import org.slf4j.Logger
import org.slf4j.ILoggerFactory

class TestLoggerFactory : ILoggerFactory {

    override fun getLogger(name: String) = TestLoggerAdapter(name)
}

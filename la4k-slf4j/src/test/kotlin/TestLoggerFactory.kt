/*
 * SPDX-FileCopyrightText: 2021 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: CC0-1.0
 */

package org.la4k.slf4j.test

import org.slf4j.Logger
import org.slf4j.ILoggerFactory

class TestLoggerFactory : ILoggerFactory {

    override fun getLogger(name: String) = TestLoggerAdapter(name)
}

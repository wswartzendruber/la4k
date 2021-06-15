/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.log4j2

import org.la4k.Bridge
import org.la4k.Logger

public class Log4j2Bridge : Bridge() {

    public override fun createLogger(name: String): Logger = Log4j2Logger(name)
}

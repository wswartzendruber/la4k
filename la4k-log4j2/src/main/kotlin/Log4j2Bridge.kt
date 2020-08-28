/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.log4j2

import org.la4k.Bridge
import org.la4k.Logger

public class Log4j2Bridge : Bridge() {

    public override fun createLogger(name: String): Logger = Log4j2Logger(name)
}

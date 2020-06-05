/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.jul.test

import java.util.logging.Handler
import java.util.logging.LogRecord

object TestHandler : Handler() {

    override fun close() { }

    override fun flush() { }

    override fun publish(record: LogRecord) {
        entries.add(Entry(record.loggerName, record.level, record.message, record.thrown))
    }
}

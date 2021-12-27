/*
 * Any copyright is dedicated to the Public Domain.
 *
 * Copyright 2021 William Swartzendruber
 *
 * SPDX-License-Identifier: CC0-1.0
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

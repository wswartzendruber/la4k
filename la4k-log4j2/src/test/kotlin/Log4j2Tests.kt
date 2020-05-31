/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.log4j2.test

import org.la4k.Logger

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail
import kotlin.test.BeforeTest
import kotlin.test.Test

import org.apache.logging.log4j.Level

class Log4j2Tests {

    // TODO: MARKER TESTING

    @Test
    fun `fatal, null throwable, null tag`() {

        Logger("test").fatal("test-message-1")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.FATAL)
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == null)
        }
    }

    @Test
    fun `fatal, non-null throwable, null tag`() {

        Logger("test").fatal("test-message-1", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.FATAL)
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == null)
        }
    }

    @Test
    fun `fatal, null throwable, non-null tag`() {

        Logger("test").fatal("test-message-1", null, "tag-1")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.FATAL)
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == "tag-1")
        }
    }

    @Test
    fun `fatal, non-null throwable, non-null tag`() {

        Logger("test").fatal("test-message-1", exception, "tag-1")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == Level.FATAL)
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == "tag-1")
        }
    }

    @Test
    fun `logger isolation`() {

        Logger("test-1").fatal("test-message-1", exception, "tag-1")
        Logger("test-2").error("test-message-2", null, "tag-2")

        assertTrue(entries.size == 2)

        entries[0].let {
            assertTrue(it.level == Level.FATAL)
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == "tag-1")
        }
        entries[1].let {
            assertTrue(it.level == Level.ERROR)
            assertTrue(it.message == "test-message-2")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == "tag-2")
        }
    }

    @Test
    fun `fatal is enabled`() {
        assertTrue(Logger("test").isFatalEnabled())
    }

    @Test
    fun `error is enabled`() {
        assertTrue(Logger("test").isErrorEnabled())
    }

    @Test
    fun `warn is enabled`() {
        assertTrue(Logger("test").isWarnEnabled())
    }

    @Test
    fun `info is enabled`() {
        assertTrue(Logger("test").isInfoEnabled())
    }

    @Test
    fun `debug is disabled`() {
        assertFalse(Logger("test").isDebugEnabled())
    }

    @Test
    fun `trace is disabled`() {
        assertFalse(Logger("test").isTraceEnabled())
    }

    @BeforeTest
    fun prepare() = entries.clear()

    companion object {

        val exception = Exception("test-exception")
    }
}

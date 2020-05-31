/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.slf4j.test

import org.la4k.logger

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail
import kotlin.test.BeforeTest
import kotlin.test.Test

class Slf4jTests {

    // TODO: MARKER TESTING

    @Test
    fun `fatal, null throwable, null tag`() {

        logger("test").error("test-message-1")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "ERROR")
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == null)
        }
    }

    @Test
    fun `fatal, non-null throwable, null tag`() {

        logger("test").error("test-message-1", exception)

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "ERROR")
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == null)
        }
    }

    @Test
    fun `fatal, null throwable, non-null tag`() {

        logger("test").error("test-message-1", null, "tag-1")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "ERROR")
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == "tag-1")
        }
    }

    @Test
    fun `fatal, non-null throwable, non-null tag`() {

        logger("test").error("test-message-1", exception, "tag-1")

        assertTrue(entries.size == 1)

        entries[0].let {
            assertTrue(it.level == "ERROR")
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == "tag-1")
        }
    }

    @Test
    fun `logger isolation`() {

        logger("test-1").error("test-message-1", exception, "tag-1")
        logger("test-2").warn("test-message-2", null, "tag-2")

        assertTrue(entries.size == 2)

        entries[0].let {
            assertTrue(it.level == "ERROR")
            assertTrue(it.message == "test-message-1")
            assertTrue(it.throwable == exception)
            assertTrue(it.tag == "tag-1")
        }
        entries[1].let {
            assertTrue(it.level == "WARN")
            assertTrue(it.message == "test-message-2")
            assertTrue(it.throwable == null)
            assertTrue(it.tag == "tag-2")
        }
    }

    @Test
    fun `fatal is enabled`() {
        assertTrue(logger("test").isFatalEnabled())
    }

    @Test
    fun `error is enabled`() {
        assertTrue(logger("test").isErrorEnabled())
    }

    @Test
    fun `warn is enabled`() {
        assertTrue(logger("test").isWarnEnabled())
    }

    @Test
    fun `info is enabled`() {
        assertTrue(logger("test").isInfoEnabled())
    }

    @Test
    fun `debug is disabled`() {
        assertFalse(logger("test").isDebugEnabled())
    }

    @Test
    fun `trace is disabled`() {
        assertFalse(logger("test").isTraceEnabled())
    }

    @BeforeTest
    fun prepare() = entries.clear()

    companion object {

        val exception = Exception("test-exception")
    }
}

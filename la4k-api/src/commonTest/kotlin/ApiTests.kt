/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.test

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail
import kotlin.test.BeforeTest
import kotlin.test.Test

import org.la4k.logger
import org.la4k.impl.Level
import org.la4k.proxy.isLevelEnabled
import org.la4k.proxy.logMessage

class ApiTests {

    init {
        logMessage = { name, level, message, throwable, tag ->
            messages.add(Message(name, level, message, throwable, tag))
        }
    }

    @Test
    fun `enabled message is logged`() {
        logger("test-1").fatal("test-message-1")
        assertTrue(messages.any({
            it.name == "test-1" &&
            it.message == "test-message-1"
        }))
    }

    @Test
    fun `disabled message is not logged`() {
        isLevelEnabled = { _, _ -> false }
        logger("test-2").error("test-message-2")
        assertFalse(messages.any({
            it.name == "test-2" &&
            it.message == "test-message-2"
        }))
    }

    @Test
    fun `disabled lambda is not logged`() {
        isLevelEnabled = { _, _ -> false }
        logger("test-3").error({ fail("Lambda was evaluated.") })
    }

    @BeforeTest
    fun prepare() {
        messages.clear()
        isLevelEnabled = { _, _ -> true }
    }

    companion object {

        val messages = mutableListOf<Message>()

        data class Message(
            val name: String,
            val level: Level,
            val message: CharSequence,
            val throwable: Throwable?,
            val tag: String?
        )
    }
}

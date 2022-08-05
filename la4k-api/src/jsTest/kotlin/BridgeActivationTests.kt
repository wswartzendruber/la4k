/*
 * Copyright 2021 William Swartzendruber
 *
 * To the extent possible under law, the person who associated CC0 with this file has waived all
 * copyright and related or neighboring rights to this file.
 *
 * You should have received a copy of the CC0 legalcode along with this work. If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 *
 * SPDX-License-Identifier: CC0-1.0
 */

package org.la4k.test

import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlin.test.Test

import org.la4k.activateBridge
import org.la4k.logger
import org.la4k.BridgeActivationException

class BridgeActivationTests {

    @Test
    fun a_bridge_can_only_be_activated_once() {
        activateBridge(org.la4k.NullBridge())
        assertFailsWith<BridgeActivationException>(
            "A bridge could be activated after one has already been activated."
        ) {
            activateBridge(org.la4k.NullBridge())
        }
    }

    @Test
    fun a_bridge_cannot_be_activated_after_a_logger() {
        logger("initialized-logger")
        assertFailsWith<BridgeActivationException>(
            "A bridge could be activated after a logger has been initialized."
        ) {
            activateBridge(org.la4k.NullBridge())
        }
    }
}

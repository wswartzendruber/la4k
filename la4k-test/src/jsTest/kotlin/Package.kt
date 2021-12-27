/*
 * Any copyright is dedicated to the Public Domain.
 *
 * Copyright 2021 William Swartzendruber
 *
 * SPDX-License-Identifier: CC0-1.0
 */

package org.la4k.test.test

import org.la4k.activateBridge
import org.la4k.test.TestBridge

internal actual fun initialize() = activateBridge(TestBridge())

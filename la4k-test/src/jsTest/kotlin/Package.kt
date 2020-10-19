/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

package org.la4k.test.test

import org.la4k.activateBridge
import org.la4k.test.TestBridge

internal actual fun initialize() = activateBridge(TestBridge())

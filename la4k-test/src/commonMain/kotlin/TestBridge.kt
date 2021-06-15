/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.test

import org.la4k.Bridge
import org.la4k.Logger

public class TestBridge : Bridge() {

    public override fun createLogger(name: String): Logger = TestLogger(name)
}

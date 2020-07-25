/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.test

import org.la4k.Logger
import org.la4k.impl.Bridge

class TestBridge : Bridge() {

    override fun getLogger(name: String): Logger = TestLogger(name)
}

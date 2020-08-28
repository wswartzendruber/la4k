/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.android

import org.la4k.Bridge
import org.la4k.Logger

public class AndroidBridge : Bridge() {

    public override fun createLogger(name: String): Logger = AndroidLogger(name)
}

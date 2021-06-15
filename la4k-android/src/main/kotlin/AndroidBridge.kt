/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.android

import org.la4k.Bridge
import org.la4k.Logger

public class AndroidBridge : Bridge() {

    public override fun createLogger(name: String): Logger = AndroidLogger(name)
}

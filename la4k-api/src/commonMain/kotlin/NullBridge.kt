/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k

internal class NullBridge : Bridge() {

    public override fun createLogger(name: String): Logger = NullLogger(name)
}

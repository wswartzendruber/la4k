/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.slf4j

import org.la4k.Bridge
import org.la4k.Logger

public class Slf4jBridge : Bridge() {

    public override fun createLogger(name: String): Logger = Slf4jLogger(name)
}

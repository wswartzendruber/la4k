/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.jul

import org.la4k.impl.Bridge
import org.la4k.impl.BridgeLogger

public class JulBridge : Bridge() {

    public override fun getBridgeLogger(name: String): BridgeLogger =
        JulBridgeLogger(name)
}
/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.impl

/**
 * Extended by LA4K bridge bridges to connect the [org.la4k.Logger] class to actual logging
 * bridges.
 */
public abstract class Bridge {

    /**
     * Called by each instance of the [org.la4k.Logger] class to get an internal logger
     * specific to that instance and for the extending bridge.
     *
     * @param[name] The same name that was passed to the calling [org.la4k.Logger] class on
     *     creation.
     */
    public abstract fun getBridgeLogger(name: String): BridgeLogger
}

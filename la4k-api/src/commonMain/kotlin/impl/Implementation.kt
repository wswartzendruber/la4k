/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.impl

/**
 * Extended by LA4K implementation bridges to connect the [org.la4k.Logger] class to actual
 * logging implementations.
 */
public abstract class Implementation {

    /**
     * Called by each instance of the [org.la4k.Logger] class to get an internal logger
     * specific to that instance and for the extending implementation.
     *
     * @param[name] The same name that was passed to the calling [org.la4k.Logger] class on
     *     creation.
     */
    public abstract fun getImplementationLogger(name: String): ImplementationLogger
}

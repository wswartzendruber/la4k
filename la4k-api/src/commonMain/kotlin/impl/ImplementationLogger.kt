/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.impl

/**
 * Extended by LA4K implementation bridges to provide logging functionality to specific
 * instances of the [org.la4k.Logger] class.
 */
public abstract class ImplementationLogger protected constructor(val name: String) {

    /**
     * Called by instances of [org.la4k.Logger] to forward a [message] to the implementation on
     * on their behalf at the specified [level].
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public abstract fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    ): Unit

    /**
     * Called by instances of [org.la4k.Logger] to determine if the implementation will show
     * a logging message from that instance at the specified [level].
     *
     * @param[tag] An arbitrary tag to check for.
     */
    public abstract fun isEnabled(level: Level, tag: String?): Boolean
}

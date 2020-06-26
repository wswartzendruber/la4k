/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.proxy

import org.la4k.impl.Level
import org.la4k.impl.BridgeLogger

public class ProxyBridgeLogger(name: String) : BridgeLogger(name) {

    /**
     * Invokes the shared [logEvent] closure by simply forwarding the event to it. The [level],
     * [message], [throwable], and [tag] values are directly sent, in addition to the name of
     * `org.la4k.Logger` instance sending the event.
     */
    public override fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        if (isEnabled(level, tag)) {
            logEvent(name, level, message, throwable, tag)
        }
    }

    /**
     * Invokes the shared [isLevelEnabled] closure with the provided [level] and [tag] values
     * and simply passes the return value back to the `org.la4k.Logger` instance making the
     * query. The name of the instance is also sent.
     */
    public override fun isEnabled(level: Level, tag: String?): Boolean =
        isLevelEnabled(name, level, tag)
}

/*
 * Copyright 2019 William Swartzendruber
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
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
    )

    /**
     * Called by instances of [org.la4k.Logger] to determine if the implementation will show
     * a logging message from that instance at the specified [level].
     *
     * @param[tag] An arbitrary tag to check for.
     */
    public abstract fun isEnabled(level: Level, tag: String?): Boolean
}

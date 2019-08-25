/*
 * Copyright 2019 William Swartzendruber
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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

/*
 * Copyright 2020 William Swartzendruber
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

package org.la4k

import org.la4k.impl.Implementation

internal val implementations = mutableListOf<Implementation>()
internal var currentHashCode = 0

/**
 * Forces a reinventory of all available implementations.
 *
 * This should only be done by host applications if a new logging implementation has
 * been made available since application startup. It may cause all instances of this
 * class to have to separately reinstanciate internal handles to all available
 * implementations, which will happen on each instance's next logging call.
 */
public fun refresh(): Unit {
    platformSynchronized(implementations) {
        implementations.clear()
        implementations.addAll(getImplementations())
        currentHashCode = implementations.hashCode()
    }
}

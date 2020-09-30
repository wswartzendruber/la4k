/*
 * Copyright 2020 William Swartzendruber
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package org.la4k

internal class NullLogger(name: String) : Logger(name) {

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun isFatalEnabled(tag: String?): Boolean = false

    public override fun isErrorEnabled(tag: String?): Boolean = false

    public override fun isWarnEnabled(tag: String?): Boolean = false

    public override fun isInfoEnabled(tag: String?): Boolean = false

    public override fun isDebugEnabled(tag: String?): Boolean = false

    public override fun isTraceEnabled(tag: String?): Boolean = false
}

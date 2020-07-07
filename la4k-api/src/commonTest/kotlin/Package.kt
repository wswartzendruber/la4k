/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.test

var logFatal: (String, Any?, Throwable?, String?) -> Unit =
    { _, _, _, _ -> }

var logError: (String, Any?, Throwable?, String?) -> Unit =
    { _, _, _, _ -> }

var logWarn: (String, Any?, Throwable?, String?) -> Unit =
    { _, _, _, _ -> }

var logInfo: (String, Any?, Throwable?, String?) -> Unit =
    { _, _, _, _ -> }

var logDebug: (String, Any?, Throwable?, String?) -> Unit =
    { _, _, _, _ -> }

var logTrace: (String, Any?, Throwable?, String?) -> Unit =
    { _, _, _, _ -> }

var isFatalEnabled: (String, String?) -> Boolean = { _, _ -> true }

var isErrorEnabled: (String, String?) -> Boolean = { _, _ -> true }

var isWarnEnabled: (String, String?) -> Boolean = { _, _ -> true }

var isInfoEnabled: (String, String?) -> Boolean = { _, _ -> true }

var isDebugEnabled: (String, String?) -> Boolean = { _, _ -> true }

var isTraceEnabled: (String, String?) -> Boolean = { _, _ -> true }

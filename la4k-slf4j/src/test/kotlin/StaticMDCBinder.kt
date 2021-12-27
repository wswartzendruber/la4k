/*
 * Any copyright is dedicated to the Public Domain.
 *
 * Copyright 2021 William Swartzendruber
 *
 * SPDX-License-Identifier: CC0-1.0
 */

package org.slf4j.impl;

import org.slf4j.helpers.NOPMDCAdapter;

class StaticMDCBinder {

    val MDCAdapterClassStr = NOPMDCAdapter::class.qualifiedName

    fun getMDCA() = NOPMDCAdapter()

    companion object {

        @JvmStatic
        val singleton = StaticMDCBinder()
    }
}

/*
 * Copyright 2021 William Swartzendruber
 *
 * To the extent possible under law, the person who associated CC0 with this file has waived all
 * copyright and related or neighboring rights to this file.
 *
 * You should have received a copy of the CC0 legalcode along with this work. If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
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

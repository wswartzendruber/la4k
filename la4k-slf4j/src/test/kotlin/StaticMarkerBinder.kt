/*
 * Any copyright is dedicated to the Public Domain.
 *
 * Copyright 2021 William Swartzendruber
 *
 * SPDX-License-Identifier: CC0-1.0
 */

package org.slf4j.impl

import org.slf4j.IMarkerFactory
import org.slf4j.helpers.BasicMarkerFactory
import org.slf4j.spi.MarkerFactoryBinder

class StaticMarkerBinder() : MarkerFactoryBinder {

    private val markerFactory = BasicMarkerFactory()

    override fun getMarkerFactory(): IMarkerFactory = markerFactory

    override fun getMarkerFactoryClassStr() = this::class.qualifiedName

    companion object {

        @JvmStatic
        val singleton = StaticMarkerBinder()
    }
}

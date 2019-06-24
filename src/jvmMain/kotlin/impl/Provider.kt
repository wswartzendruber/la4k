package org.la4k.api.impl

import java.util.ServiceLoader

internal actual fun getAvailableProviders() =
    ServiceLoader
        .load(Provider::class.java)
        .asSequence()
        .toList()

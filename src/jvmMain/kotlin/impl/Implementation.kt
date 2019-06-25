package org.la4k.impl

import java.util.ServiceLoader

internal actual fun getAvailableImplementations() =
    ServiceLoader
        .load(Implementation::class.java)
        .asSequence()
        .toList()

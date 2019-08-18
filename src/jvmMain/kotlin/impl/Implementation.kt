package org.la4k.impl

import java.util.ServiceLoader

internal actual fun getImplementations() =
    ServiceLoader
        .load(Implementation::class.java)
        .apply { reload() }
        .asSequence()
        .toList()

package org.la4k.impl

import java.util.ServiceLoader

internal actual fun implementations() =
    ServiceLoader
        .load(Implementation::class.java)
        .asSequence()
        .toList()

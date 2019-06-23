package org.la4k.api

import java.util.ServiceLoader

private val loader = ServiceLoader.load(JvmProvider::class.java)

public actual fun refresh() {
    synchronized(loader) {
        loader.reload()
    }
}

internal actual fun log(name: String, level: Level, message: String) {
    synchronized(loader) {
        for (provider in loader)
            provider.log(name, level, message)
    }
}

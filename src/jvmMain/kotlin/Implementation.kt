package org.la4k.api

import java.util.ServiceLoader

private val providers = ServiceLoader.load(JvmProvider::class.java)

public actual fun refresh() {
    synchronized(providers) {
        providers.reload()
    }
}

internal actual fun log(
    name: String,
    level: Level,
    message: CharSequence,
    throwable: Throwable?,
    tags: List<String>?
) {
    synchronized(providers) {
        for (provider in providers)
            provider.log(name, level, message, throwable, tags)
    }
}

internal actual fun isEnabled(level: Level, tags: List<String>?) =
    synchronized(providers) {
        providers.any { it.isEnabled(level, tags) }
    }

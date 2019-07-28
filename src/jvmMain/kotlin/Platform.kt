package org.la4k

internal actual fun <R> platformSynchronized(lock: Any, block: () -> R) =
    synchronized(lock, block)

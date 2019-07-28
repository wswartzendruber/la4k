package org.la4k

internal expect fun <R> platformSynchronized(lock: Any, block: () -> R): R

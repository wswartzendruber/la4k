package org.la4k.api

import java.util.ServiceLoader

public actual class Logger actual constructor(name: String) {

    private val name = name

    public actual fun fatal(message: String) {
        for (provider in providers)
            provider.fatal(name, message)
    }

    companion object {

        private val providers = ServiceLoader
            .load(JvmProvider::class.java)
            .iterator()
            .asSequence()
            .toList()
    }
}

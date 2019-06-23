package org.la4k.api

import java.util.ServiceLoader

public actual class Logger actual constructor(name: String) {

    private val name = name

    public actual fun fatal(message: String) {
        logMessage(name, 0, message)
    }

    actual companion object {

        private val loader = ServiceLoader.load(JvmProvider::class.java)

        init {
            reload()
        }

        public actual fun reload() {
            synchronized (this) {
                loader.reload()
            }
        }

        private fun logMessage(name: String, level: Int, message: String) {
            synchronized(this) {
                for (provider in loader)
                    provider.logMessage(name, level, message)
            }
        }
    }
}

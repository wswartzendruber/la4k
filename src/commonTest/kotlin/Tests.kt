package org.la4k.api

import kotlin.test.assertTrue
import kotlin.test.Test

import org.la4k.test.messages

class Tests {

    @Test
    fun aSimpleMessageCanBeLogged() {

        Logger("test").info("Hello.")

        assertTrue(
            messages.any { it.message == "Hello." }
        )
    }
}

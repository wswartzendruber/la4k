/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

package org.la4k.log4j2.test

import org.apache.logging.log4j.core.Appender
import org.apache.logging.log4j.core.Core
import org.apache.logging.log4j.core.Filter
import org.apache.logging.log4j.core.LogEvent
import org.apache.logging.log4j.core.appender.AbstractAppender
import org.apache.logging.log4j.core.config.plugins.Plugin
import org.apache.logging.log4j.core.config.plugins.PluginAttribute
import org.apache.logging.log4j.core.config.plugins.PluginElement
import org.apache.logging.log4j.core.config.plugins.PluginFactory

@Plugin(
    name = "ListAppender",
    category = Core.CATEGORY_NAME,
    elementType = Appender.ELEMENT_TYPE
)
class ListAppender protected constructor(
    name: String,
    filter: Filter?
) : AbstractAppender(name, filter, null, false, null) {

    override fun append(event: LogEvent) {
        entries.add(Entry(
            event.loggerName,
            event.level,
            event.message.formattedMessage,
            event.thrown,
            event.marker?.name
        ))
    }

    companion object {

        @JvmStatic
        @PluginFactory
        fun createAppender(
            @PluginAttribute("name") name: String,
            @PluginElement("Filter") filter: Filter?
        ) = ListAppender(name, filter)
    }
}

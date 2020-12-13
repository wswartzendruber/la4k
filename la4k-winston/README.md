# Module la4k-winston

## Introduction

The `la4k-winston` bridge connects `la4k-api` to the Winston logger for NodeJS.

## Registering Loggers

Once the bridge has been activated via `org.la4k.activateBridge`, LA4K loggers can be mapped to
Winston loggers via the former's name.

For example, say that a dependency in a Kotlin/JS application uses a LA4K logger named
`org.wswartzendruber.GrandConspiracy`:

```kotlin
import org.la4k.activateBridge
import org.la4k.winston.registerLogger
```

```kotlin
val winston = require("winston")
val masonicLogger = js("""
    winston.createLogger({
        transports: [
            new winston.transports.Console()
        ]
    });
""")

activateBridge(org.la4k.winston.WinstonBridge())
registerLogger("org.wswartzendruber.GrandConspiracy", masonicLogger)
```

This would cause logging events from the LA4K logger named `org.wswartzendruber.GrandConspiracy`
to be forwarded to `masonicLogger`.

Note that a LA4K logger must be mapped via `org.la4k.winston.registerLogger` before it logs any
events. Otherwise, it will be permanently bound to `null` which will cause all events to be
discarded. Once a logger is registered, it becomes locked and cannot be re-registered.

## Levels

The following level mappings are used:

| LA4K  | SLF4J |
|-------|-------|
| FATAL | ERROR |
| ERROR | ERROR |
| WARN  | WARN  |
| INFO  | INFO  |
| DEBUG | DEBUG |
| TRACE | SILLY |

## Tags

LA4K tags are discarded by the bridge. Any query for a level being enabled for a specific tag
returns `true` as long as that level is enabled for the logger in question.

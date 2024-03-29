<!--
    Copyright 2021 William Swartzendruber

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
    either express or implied. See the License for the specific language governing permissions
    and limitations under the License.

    SPDX-License-Identifier: Apache-2.0
-->

# Module la4k-winston

## Introduction

The `la4k-winston` bridge connects `la4k-api` to the Winston logger for NodeJS.

## Activation

Activation is done via the `org.la4k.activateBridge` function:

```kotlin
import org.la4k.activateBridge
```

```kotlin
activateBridge(org.la4k.winston.WinstonBridge())
```

This must be done before any LA4K loggers are initialized by any component. Otherwise, all LA4K
logging will be discarded.

## Mapping

Once the bridge has been activated, LA4K loggers can be mapped to Winston loggers via the
former's name.

For example, say that a dependency in a Kotlin/JS application uses a LA4K logger named
`org.wswartzendruber.GrandConspiracy`:

```kotlin
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

registerLogger("org.wswartzendruber.GrandConspiracy", masonicLogger)
```

This would cause logging events from the LA4K logger named `org.wswartzendruber.GrandConspiracy`
to be forwarded to `masonicLogger`.

Note that a LA4K logger must be mapped via `org.la4k.winston.registerLogger` before it logs any
events. Otherwise, it will be permanently bound to `null` which will cause all events to be
discarded. Once a logger is registered, it becomes locked and cannot be re-registered.

## Levels

The following level mappings are used:

| LA4K  | Winston |
|-------|---------|
| FATAL | ERROR   |
| ERROR | ERROR   |
| WARN  | WARN    |
| INFO  | INFO    |
| DEBUG | DEBUG   |
| TRACE | SILLY   |

## Tags

LA4K tags are discarded by this bridge. Any query for a level being enabled for a specific tag
returns `true` as long as that level is enabled for the logger in question.

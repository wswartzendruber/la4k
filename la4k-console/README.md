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

# Module la4k-console

## Introduction

The `la4k-console` bridge causes all events from `la4k-api` to be sent directly to the console
via Kotlin's `println()` function.

## Activation

### JVM, Android

The JAR for this bridge needs to be in the application's classpath. From there, `la4k-api` will
use JSPI to detect it and forward events to it, so long as there is not another bridge that
takes precedence.

### JS

Activation is done via the `org.la4k.activateBridge` function:

```kotlin
import org.la4k.activateBridge
```

```kotlin
activateBridge(org.la4k.console.ConsoleBridge())
```

This must be done before any LA4K loggers are initialized by any component. Otherwise, all LA4K
logging will be discarded.

## Format

The fixed format of console output is:

`NAME[TAG] - LEVEL - MESSAGE`

## Verbosity

Verbosity for all loggers can be configured at any time by setting the active level:

```kotlin
import org.la4k.Level
import org.la4k.console.level
```

```kotlin
level = Level.WARN
```

The default level is `INFO`, but the above snippet will reduce verbosity to only include `WARN`
messages and higher.

## Tags

Any query for a level being enabled for a specific tag returns `true` as long as that level is
enabled via `org.la4k.console.level`.

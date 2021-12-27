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

# Module la4k-jul

## Introduction

The `la4k-jul` bridge connects `la4k-api` to `java.util.logging`.

## Activation

The JAR for this bridge needs to be in the application's classpath. From there, `la4k-api` will
use JSPI to detect it and forward events to it, so long as there is not another bridge that
takes precedence.

## Naming

The LA4K name maps directly to the `java.util.logging` name.

## Levels

The following level mappings are used:

| LA4K  | JUL     |
|-------|---------|
| FATAL | SEVERE  |
| ERROR | SEVERE  |
| WARN  | WARNING |
| INFO  | INFO    |
| DEBUG | FINE    |
| TRACE | FINER   |

## Tags

As standard Java logging has no concept of tags or markers, they are ignored. Any query for a
level being enabled for a specific tag returns `true` as long as that level is enabled for the
logger in question.

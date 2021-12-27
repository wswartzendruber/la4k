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

# Module la4k-slf4j

## Introduction

The `la4k-slf4j` bridge connects `la4k-api` to SLF4J, and therefore, to Logback (if desired).

## Activation

The JAR for this bridge needs to be in the application's classpath. From there, `la4k-api` will
use JSPI to detect it and forward events to it, so long as there is not another bridge that
takes precedence.

## Naming

The LA4K name maps directly to the SLF4J name.

## Levels

The following level mappings are used:

| LA4K  | SLF4J |
|-------|-------|
| FATAL | ERROR |
| ERROR | ERROR |
| WARN  | WARN  |
| INFO  | INFO  |
| DEBUG | DEBUG |
| TRACE | TRACE |

## Tags

LA4K tags become SLF4J markers, which are cached for each `org.la4k.Logger` instance by this
bridge.

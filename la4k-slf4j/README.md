<!--
    SPDX-FileCopyrightText: 2021 William Swartzendruber <wswartzendruber@gmail.com>

    SPDX-License-Identifier: CC-BY-4.0
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

<!--
    SPDX-FileCopyrightText: 2021 William Swartzendruber <wswartzendruber@gmail.com>

    SPDX-License-Identifier: Apache-2.0
-->

# Module la4k-log4j2

## Introduction

The `la4k-log4j2` bridge connects `la4k-api` to the excellent Apache Log4j engine. Only version
2 is supported as version 1 has been discontinued.

## Activation

The JAR for this bridge needs to be in the application's classpath. From there, `la4k-api` will
use JSPI to detect it and forward events to it, so long as there is not another bridge that
takes precedence

## Naming

The LA4K name maps directly to the Log4j name.

## Levels

As LA4K's levels were modeled after those from Log4j, no level conversion takes place.

## Tags

LA4K tags become standalone Log4j markers, which are cached for each `org.la4k.Logger` instance
by this bridge.

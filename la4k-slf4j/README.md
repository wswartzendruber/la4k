# Module la4k-slf4j

## Introduction

The `la4k-slf4j` bridge connects `la4k-api` to SLF4J, and therefore, to Logback (if desired).

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

# Module la4k-jul

## Introduction

The `la4k-jul` bridge connects `la4k-api` to `java.util.logging`.

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

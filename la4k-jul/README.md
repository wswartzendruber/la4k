# Module la4k-jul

The `la4k-jul` bridge connects `la4k-api` to `java.util.logging`.

The following level mappings are used:

| LA4K  | JUL     |
|-------|---------|
| FATAL | SEVERE  |
| ERROR | SEVERE  |
| WARN  | WARNING |
| INFO  | INFO    |
| DEBUG | FINE    |
| TRACE | FINER   |

As standard Java logging has no concept of tags or markers, they are ignored. Any query for a
level being enabled for a specific tag returns `true` as long as that level is enabled for the
logger in question.

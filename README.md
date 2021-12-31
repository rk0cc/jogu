# Java Object of Git (Repository) URL - JOGU

[![Test](https://github.com/rk0cc/jogu/actions/workflows/test.yml/badge.svg?branch=main)](https://github.com/rk0cc/jogu/actions/workflows/test.yml)
[![CodeQL](https://github.com/rk0cc/jogu/actions/workflows/codeql.yml/badge.svg?branch=main)](https://github.com/rk0cc/jogu/actions/workflows/codeql.yml)

This Java library allows parsing Git repository URL addresses to an actual
object with validation included.

## Supported protocol of Git repository URL

Most remote protocol are supported in this library.

| Protocol format name       |             Example             | Supported in this library |
|:---------------------------|:-------------------------------:|:-------------------------:|
| HTTPS protocol             |  `https://example.com/foo.git`  |    :white_check_mark:     |
| Git protocol               |   `git://example.com/foo.git`   |    :white_check_mark:     |
| SSH protocol (Legit)       | `ssh://git@example.com/foo.git` |    :white_check_mark:     |
| SSH protocol (Alternative) |    `git@example.com:foo.git`    |    :white_check_mark:     |
| File protocol              |    `file:///path/to/foo.git`    |            :x:            |

## Setup

* JDK 17 (or above)
* Maven

## License

WTFPL 2.0
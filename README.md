# Java Object of Git (Repository) URL - JOGU

[![Test](https://github.com/rk0cc/jogu/actions/workflows/test.yml/badge.svg?branch=main)](https://github.com/rk0cc/jogu/actions/workflows/test.yml)
[![CodeQL](https://github.com/rk0cc/jogu/actions/workflows/codeql.yml/badge.svg?branch=main)](https://github.com/rk0cc/jogu/actions/workflows/codeql.yml)
[![Maven Central](https://img.shields.io/maven-central/v/xyz.rk0cc.jogu/jogu.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22xyz.rk0cc.jogu%22%20AND%20a:%22jogu%22)

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

## Install

Using Maven 3:

```xml
<dependencies>
    <dependency>
        <groupId>xyz.rk0cc.jogu</groupId>
        <artifactId>jogu</artifactId>
        <version>1.0.1</version>
    </dependency>
</dependencies>
```

## Usage

Get `GitRepositoryURL` without specific implemented type uses:

```java
GitRepositoryURL gurl = GitRepositoryURL.parse("git://example.com/foo.git");
```

Get `GitRepositoryURL` with specific implemented type uses:

```java
GitGitRepositoryURL gurl = GitRepositoryURL.parse(GitGitRepositoryURL.class, "git://example.com/foo.git");
```

## Setup

* JDK 17 (or above)
* Maven

## Documentation

Online version of Javadoc will be availabled in javadoc.io automatically:

[![javadoc](https://javadoc.io/badge2/xyz.rk0cc.jogu/jogu/javadoc.svg)](https://javadoc.io/doc/xyz.rk0cc.jogu/jogu)

For the offline version, please go to [release](https://github.com/rk0cc/jogu/releases).

## License

WTFPL 2.0

# Argos Contracts

Shared contracts for Argos services, including gRPC protobufs and RabbitMQ
event payload schemas. This module is published as a Maven artifact and
consumed by the microservices.

## Contents

- `proto/argos/resource_query.proto`: gRPC resource query definitions
- `rabbitmq/argos/*.json`: RabbitMQ event payload schemas

## Requirements

- Java 21
- Gradle (or use `./gradlew`)

## Build

```bash
./gradlew build
```

Generated gRPC/Protobuf sources are produced during the build and packaged
into the published artifact.

## Publish

Publishing targets GitHub Packages at `com.erval.argos:argos-contracts`.
Set credentials via environment variables:

```bash
export GITHUB_ACTOR=...
export GITHUB_TOKEN=...

./gradlew publish
```

## Usage

Include the dependency in service build files and import the generated gRPC
types from `com.erval.argos.contracts.resource.v1`.

### Gradle (Kotlin DSL)

```kotlin
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/ErVaaL/argos-contracts")
        credentials {
            username = System.getenv("GITHUB_ACTOR")
            password = System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("com.erval.argos:argos-contracts:0.0.1-SNAPSHOT")
}
```

### Gradle (Groovy DSL)

```groovy
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/ErVaaL/argos-contracts")
        credentials {
            username = System.getenv("GITHUB_ACTOR")
            password = System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation "com.erval.argos:argos-contracts:0.0.1-SNAPSHOT"
}
```

### Maven

```xml
<repositories>
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/ErVaaL/argos-contracts</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.erval.argos</groupId>
    <artifactId>argos-contracts</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </dependency>
</dependencies>
```

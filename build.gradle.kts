plugins {
    `java-library`
    `maven-publish`
    id("com.google.protobuf") version "0.9.4"
}

group = "com.erval.argos"
version = System.getenv("VERSION") ?: "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("io.grpc:grpc-protobuf:1.78.0")
    api("io.grpc:grpc-stub:1.78.0")
    api("com.google.protobuf:protobuf-java:4.33.2")
    implementation("io.grpc:grpc-netty-shaded:1.78.0")
    api("jakarta.annotation:jakarta.annotation-api:3.0.0")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.33.2"
    }

    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.78.0"
        }
    }

    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("grpc")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("proto")
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        withSourcesJar()
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "argos-contracts"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ErVaaL/argos-contracts")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

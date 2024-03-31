import io.freefair.gradle.plugins.lombok.LombokPlugin

plugins {
    java
    `java-library`
    idea
    id("io.freefair.lombok") version "8.6"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "eu.minecountry"
version = "1.0.0"
description = "Core library of the MineCountry minecraft server"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

subprojects {
    apply {
        apply<JavaPlugin>()
        apply<JavaLibraryPlugin>()
        apply<LombokPlugin>()
    }

    dependencies {
        implementation("org.jetbrains:annotations:24.1.0")
        implementation("org.apache.commons:commons-lang3:3.14.0")
        implementation("com.google.guava:guava:33.0.0-jre")

        testImplementation(platform("org.junit:junit-bom:5.10.2"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks {
        java {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(21))
            }
            withSourcesJar()
            withJavadocJar()
        }

        compileJava {
            options.encoding = Charsets.UTF_8.name()
        }

        compileTestJava {
            options.encoding = Charsets.UTF_8.name()
        }

        javadoc {
            options.encoding = Charsets.UTF_8.name()
        }

        test {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }
    }
}
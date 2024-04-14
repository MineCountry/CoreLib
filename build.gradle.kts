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

dependencies {
    api(project(":api"))
    api(project(":common"))
    api(project(":paper-lib"))
    api(project(":velocity-lib"))
}

subprojects {
    apply {
        plugin<JavaPlugin>()
        plugin<JavaLibraryPlugin>()
        plugin<LombokPlugin>()
    }

    tasks {
        java {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(21))
            }
            withSourcesJar()
            withJavadocJar()
        }
    }
}

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        implementation("org.jetbrains:annotations:24.1.0")
        implementation("org.apache.commons:commons-lang3:3.14.0")
        implementation("com.google.guava:guava:33.0.0-jre")
        implementation("de.chojo.sadu:sadu-mariadb:2.1.0")
        implementation("de.chojo.sadu:sadu-queries:2.1.0")
        implementation("de.chojo.sadu:sadu-datasource:2.1.0")

        compileOnly("eu.cloudnetservice.cloudnet:driver:4.0.0-RC10")
        compileOnly("eu.cloudnetservice.cloudnet:bridge:4.0.0-RC10")

        testImplementation("org.mockito:mockito-core:5.11.0")
        testImplementation(platform("org.junit:junit-bom:5.10.2"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks {
        test {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        compileJava {
            options.encoding = "UTF-8"
        }

        compileTestJava {
            options.encoding = "UTF-8"
        }

        javadoc {
            options.encoding = "UTF-8"
        }
    }
}
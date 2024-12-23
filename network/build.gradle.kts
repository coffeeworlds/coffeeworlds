/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.java-library-conventions")
    id("com.adarshr.test-logger") version "4.0.0"
    id("com.diffplug.spotless") version "7.0.0.BETA4"
    `maven-publish`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://plugins.gradle.org/m2/") }
}

publishing {
    publications {
        register<MavenPublication>("Maven") {
            from(components["java"])
        }
    }
}

dependencies {
    api(project(":huffman"))
}

spotless {
    java {
        importOrder()
        googleJavaFormat().reflowLongStrings()
    }
}

plugins {
    id("buildlogic.java-library-conventions")
    id("com.adarshr.test-logger") version "4.0.0"
    `maven-publish`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

publishing {
    publications {
        register<MavenPublication>("Maven") {
            from(components["java"])
        }
    }
}


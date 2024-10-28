/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":network"))
}

application {
    // Define the main class for the application.
    mainClass = "com.github.coffeeworlds.client.Client"
}

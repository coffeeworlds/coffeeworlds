# coffeeworlds

# !!WARNING!! there is no code yet the library does not exist yet lol !!WARNING!!

A teeworlds 0.7 network library written in java.

---

There is a sample client implementation in **client/** and the network library in **network/**.

## Run the sample client

```
./gradlew shadowJar
java -jar client/build/libs/client-all.jar localhost 8303
```

## Use the network library in your project

The network library is also published to jitpack. You can import it to your project by adding the jitpack maven url.

```kotlin
// build.gradle.kts

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.coffeeworlds:coffeeworlds:-SNAPSHOT")
}
```

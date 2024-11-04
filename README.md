# coffeeworlds

A teeworlds 0.7 network library written in java.

---

There is a sample client implementation in **client/** and the network library in **network/** and the compression library in **huffman/**

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
    implementation("com.github.coffeeworlds.coffeeworlds:huffman:v0.0.2")
    implementation("com.github.coffeeworlds.coffeeworlds:network:v0.0.2")
}
```

```java
// app/src/main/java/org/example/App.java

package org.example;

import java.util.HexFormat;

import com.github.coffeeworlds.network.system.MsgInfo;
import com.github.coffeeworlds.network.system.MsgInfoBuilder;
import com.github.coffeeworlds.network.PacketHeaderBuilder;
import com.github.coffeeworlds.network.PacketHeader;
import com.github.coffeeworlds.network.ChunkHeader;
import com.github.coffeeworlds.network.Chunk;
import com.github.coffeeworlds.network.Packet;

public class App {
    public static void main(String[] args) {
        PacketHeader header =
            new PacketHeaderBuilder()
                .ack(10)
                .token(new byte[]{0x0c, 0x0c, 0x0c, 0x0c})
                .buildHeader();
        Packet packet = new Packet(header);
        MsgInfo msg = new MsgInfoBuilder().password("pass").buildMsg();
        Chunk chunk = new Chunk(new ChunkHeader(0), msg);
        packet.messages.add(chunk);
        System.out.println(HexFormat.of().formatHex(packet.pack()));
        // => 000a000c0c0c0c401d0003302e372038303266316265363061303536363566007061737300851c
    }
}
```

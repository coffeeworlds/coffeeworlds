package com.github.coffeeworlds.client;

import com.github.coffeeworlds.network.Unpacker;

public class Client {
  public static void main(String[] args) {
    {
      byte[] data = new byte[] {0x02};
      Unpacker unpacker = new Unpacker(data);
      int val = unpacker.getInt();
      System.out.println("hello from coffee client val=" + val);
    }

    if (args.length < 2) {
      System.out.println("usage: client <ip> <port>");
      return;
    }

    String serverIp = args[0];
    int serverPort = Integer.parseInt(args[1]);

    GameClient gameClient = new GameClient();
    gameClient.connect(serverIp, serverPort);

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread() {
              public void run() {
                System.out.println("shutting down ...");
                gameClient.disconnect();
              }
            });

    try {
      while (true) {
        gameClient.onTick();
        Thread.sleep(100);
      }
    } catch (InterruptedException ex) {
      System.out.println("Interrupted!");
    }
  }
}

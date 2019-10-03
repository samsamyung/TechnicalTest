package com.samyung;

public class Start {
  public static void main(String[] args) {
    Server server = new Server("localhost", 8080);
    server.startHttpServer();
  }

}

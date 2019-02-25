package com.BalatoniSzel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Szerver {

    static final int PORT = 59090;
    private static boolean isServerRunning;
    private Apartman apartman = new Apartman();

    public static void main(String[] args) throws IOException {
        isServerRunning = false;
        new Szerver().RunServer();


    }

    private void RunServer() throws IOException {
        try (var listener = new ServerSocket(PORT)) {
            isServerRunning = true;
            System.out.println("A szerver fut...");
            while (isServerRunning) {
                var socket = listener.accept();

                new SzerverSzal(socket, apartman).start();
            }

        }
    }


}

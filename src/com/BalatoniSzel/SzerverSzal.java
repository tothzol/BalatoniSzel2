package com.BalatoniSzel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.Thread;
import java.net.Socket;
import java.net.ServerSocket;

public class SzerverSzal extends Thread {

    private Socket socket;
    private Apartman apartman;

    public SzerverSzal(Socket socket, Apartman apartman) {
        this.socket=socket;
        this.apartman=apartman;
    }

    public void run()  {
try {
    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    Foglalas f = (Foglalas) in.readObject();
    System.out.println("Csomag jött:");
    System.out.println(f.getFoglalasAzonosito() + " " + f.getVendegEmail());

}
catch (Exception ex) {
    ex.printStackTrace();
}
    }


}

package com.BalatoniSzel;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {


public static void main (String[] args) throws IOException{
    var socket=new Socket(InetAddress.getLocalHost(),59090);
    //var in=new Scanner(socket.getInputStream());
    ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
   //Hardcode, socket teszthez
    out.writeObject(new Foglalas("zoltan@tothzol.hu","2019-03-01","2019-03-02","201",false,12000,3500));
   // ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
    socket.close();
    //System.out.println("Server mondja: "+in.());
}




}

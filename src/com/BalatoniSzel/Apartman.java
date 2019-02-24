package com.BalatoniSzel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.File;

public class Apartman implements Serializable {
    private static final long serialVersionUID = -5771420064864866043L;

    private Map<String,Szoba> Szobak;
    private Map<String, Vendeg> Vendegek;
    private ConcurrentHashMap<Integer,Foglalas> Foglalasok;

// tesztszobák letrehozása

    public Apartman(){
        tesztSzobakeszites();
    }

private synchronized void tesztSzobakeszites() {


    Szoba szoba1=new Szoba("201",2);
    Szoba szoba2=new Szoba("301",3);
    Szoba szoba3=new Szoba("302",3);
    Szoba szoba4=new Szoba("401",4);

    Map<String,Szoba> tesztSzobak=new HashMap<>();
    tesztSzobak.put(szoba1.getAzonosito(),szoba1);
    tesztSzobak.put(szoba2.getAzonosito(),szoba2);
    tesztSzobak.put(szoba3.getAzonosito(),szoba3);
    tesztSzobak.put(szoba4.getAzonosito(),szoba4);
    this.setSzobak(tesztSzobak);
    SzobaMentes();
System.out.println("Megvan");
}


    public synchronized void setSzobak (Map<String,Szoba> szobak) {
    this.Szobak=szobak;
    }
public  synchronized Map<String,Szoba> getSzobak(){
        return this.Szobak;
}

    private synchronized void SzobaMentes() {
        Adattarolas.Mentes(this.getSzobak(),"Szobak.ser");
    }
}

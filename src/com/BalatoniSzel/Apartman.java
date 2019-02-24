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
    private ConcurrentHashMap<String,Foglalas> Foglalasok;

// tesztszobák letrehozása

    public Apartman(){
        SzobaBetoltes();
        Foglalasok=new ConcurrentHashMap<>();
        FoglalasBetoltes();
    }



    public synchronized void setSzobak (Map<String,Szoba> szobak) {
    this.Szobak=szobak;
    }
    public  synchronized Map<String,Szoba> getSzobak(){
        return this.Szobak;
    }

    public synchronized void setFoglalasok(ConcurrentHashMap<String, Foglalas> foglalasok) {
        this.Foglalasok=foglalasok;
    }

    public synchronized ConcurrentHashMap<String, Foglalas> getFoglalasok() {
        return Foglalasok;
    }

    private synchronized void SzobaMentes() {
        Adattarolas.Mentes(this.getSzobak(),"Szobak.ser");
    }
    private synchronized void SzobaBetoltes(){
        if (new File("Szobak.ser").exists()){
            this.Szobak=(Map<String,Szoba>)Adattarolas.Betoltes("Szobak.ser");
        }
        else {
            tesztSzobakeszites();
        }
    }

    public String pluszFoglalas (Foglalas foglalas) {
        Foglalasok.put(foglalas.getFoglalasAzonosito(), foglalas);
        return foglalas.getFoglalasAzonosito();
    }

    protected synchronized  void FoglalasMentes(){
        Adattarolas.Mentes(this.getFoglalasok(),"Foglalasok.ser");
    }

    private synchronized void FoglalasBetoltes(){
        if (new File("Foglalasok.ser").exists()){
            this.Foglalasok=(ConcurrentHashMap<String,Foglalas>)Adattarolas.Betoltes("Foglalasok.ser");
        }
        else {
            //tesztSzobakeszites();
        }
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

}

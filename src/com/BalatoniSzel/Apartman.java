package com.BalatoniSzel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.File;

public class Apartman implements Serializable {
    private static final long serialVersionUID = -5771420064864866043L;

    private Map<String, Szoba> Szobak;
    private Map<String, Vendeg> Vendegek;
    private ConcurrentHashMap<String, Foglalas> Foglalasok;


    public Apartman() {
        SzobaBetoltes();
        Foglalasok = new ConcurrentHashMap<>();
        FoglalasBetoltes();
        Vendegek = new HashMap<>();
        VendegBetoltes();
    }


    public synchronized void setSzobak(Map<String, Szoba> szobak) {
        this.Szobak = szobak;
    }

    public synchronized Map<String, Szoba> getSzobak() {
        return this.Szobak;
    }

    public synchronized void setFoglalasok(ConcurrentHashMap<String, Foglalas> foglalasok) {
        this.Foglalasok = foglalasok;
    }

    public synchronized ConcurrentHashMap<String, Foglalas> getFoglalasok() {
        return Foglalasok;
    }

    public synchronized Map<String, Vendeg> getVendegek() {
        return this.Vendegek;
    }

    public synchronized void setVendegek(Map<String, Vendeg> vendegek) {
        this.Vendegek = vendegek;
    }


    private synchronized void SzobaMentes() {
        Adattarolas.Mentes(this.getSzobak(), "Szobak.ser");
    }

    private synchronized void SzobaBetoltes() {
        if (new File("Szobak.ser").exists()) {
            this.Szobak = (Map<String, Szoba>) Adattarolas.Betoltes("Szobak.ser");
        } else {
            tesztSzobakeszites();
        }
    }

    public String pluszFoglalas(Foglalas foglalas) {
        Foglalasok.put(foglalas.getFoglalasAzonosito(), foglalas);
        return foglalas.getFoglalasAzonosito();
    }

    protected synchronized void FoglalasMentes() {
        Adattarolas.Mentes(this.getFoglalasok(), "Foglalasok.ser");
    }

    private synchronized void FoglalasBetoltes() {
        if (new File("Foglalasok.ser").exists()) {
            this.Foglalasok = (ConcurrentHashMap<String, Foglalas>) Adattarolas.Betoltes("Foglalasok.ser");
        } else {
            //Addig kellett, amíg file-ba ki nem irtam a szobákat
            //tesztSzobakeszites();

        }
    }

    private synchronized void VendegBetoltes() {
        if (new File("Vendegek.ser").exists()) {
            this.Vendegek = (Map<String, Vendeg>) Adattarolas.Betoltes("Vendegek.ser");
        } else {
            //Addig kellett, amíg file-ba ki nem irtam a Vendegeket
            tesztVendegKeszites();

        }
    }

    protected synchronized void VendegMentes() {
        Adattarolas.Mentes(this.getVendegek(), "Vendegek.ser");
    }

    // tesztszobák letrehozása
    private synchronized void tesztSzobakeszites() {


        Szoba szoba1 = new Szoba("201", 2);
        Szoba szoba2 = new Szoba("301", 3);
        Szoba szoba3 = new Szoba("302", 3);
        Szoba szoba4 = new Szoba("401", 4);

        Map<String, Szoba> tesztSzobak = new HashMap<>();
        tesztSzobak.put(szoba1.getAzonosito(), szoba1);
        tesztSzobak.put(szoba2.getAzonosito(), szoba2);
        tesztSzobak.put(szoba3.getAzonosito(), szoba3);
        tesztSzobak.put(szoba4.getAzonosito(), szoba4);
        this.setSzobak(tesztSzobak);
        SzobaMentes();
        System.out.println("Megvan");
    }

    //tesztvendégek

    private synchronized void tesztVendegKeszites() {
        Vendeg vendeg1 = new Vendeg("Tóth Zoltán", "123456AA", "1234 Simagöröngyös, Chuck Norris sugárút 1", "zoltan@tothzol.hu");
        Vendeg vendeg2 = new Vendeg("Gipsz Jakab", "789012BB", "5831 Viharsarok, Sivatag u. 17", "jakab@gipsz.hu");

        Map<String, Vendeg> tesztVendegek = new HashMap<>();

        tesztVendegek.put(vendeg1.getEmail(), vendeg1);
        tesztVendegek.put(vendeg2.getEmail(), vendeg2);

        this.Vendegek = tesztVendegek;
        VendegMentes();
    }
}

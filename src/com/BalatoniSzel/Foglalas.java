package com.BalatoniSzel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public class Foglalas implements Serializable {
    private static final long serialVersionUID = 1041441426752225702L;
    private String FoglalasAzonosito;
    private String VendegEmail;
    private String Erkezes;
    private String Tavozas;
    private String SzobaAzonosito;
    private boolean Potagy;
    private int Ar;
    private int Eloleg;
    private String FoglalasTipus; //felteteles, vegleges


    public Foglalas(String vendegemail, String erkezes, String tavozas, String szobaAzonosito, boolean potagy, int ar, int eloleg) {
        this.VendegEmail = vendegemail;
        this.Erkezes = erkezes;
        this.Tavozas = tavozas;
        //TODO : Több szobás foglalást kezelni kell majd
        this.SzobaAzonosito = szobaAzonosito;
        //új foglalás mindig feltételes, tulaj majd változtat, ha a szállás rendezve van
        this.FoglalasTipus = "Felteteles";
        //TODO : A kétágyas szobánál a pótágy nem elérhető, kezelni kell.
        this.Potagy = potagy;
        this.Eloleg = eloleg;
        this.Ar = ar;
        this.FoglalasAzonosito = UUID.randomUUID().toString(); //ez lehet, hogy erős ide

    }

    public String getFoglalasAzonosito() {
        return this.FoglalasAzonosito;
    }

    public String getVendegEmail() {
        return this.VendegEmail;
    }

    public String getErkezes() {
        return this.Erkezes;
    }

    public String getTavozas() {
        return this.Tavozas;
    }

    public String getFoglalasTipus() {
        return this.FoglalasTipus;
    }

    public String getSzobaAzonosito() {
        return SzobaAzonosito;
    }

    public int getEloleg() {
        return this.Eloleg;
    }

    public int getAr() {
        return this.Ar;
    }

    public void setErkezes(String erkezes) {
        this.Erkezes = erkezes;
    }

    public void setTavozas(String tavozas) {
        this.Tavozas = tavozas;
    }

    public void setEloleg(int eloleg) {
        this.Eloleg = eloleg;
    }


}

package com.BalatoniSzel;

public class Szoba {
 private String Azonosito;
 private int Agyszam;
 private boolean potagy;
 private boolean Elerheto;

 public Szoba (String azonosito, int agyszam) {
     this.Azonosito=azonosito;
     this.Agyszam=agyszam;
     this.Elerheto=true;
     //ket agynal nagyobb szobanal van lehetoseg potagyra
     if (agyszam>2) {this.potagy=true;} else {this.potagy=false;}
 }

    public String getAzonosito() {
        return this.Azonosito;
    }

    public int getAgyszam() {return Agyszam;}

    public boolean isPotagy() {
        return potagy;
    }

    public void setAzonosito(String azonosito) {
        this.Azonosito = azonosito;
    }

    public boolean isElerheto() {
        return Elerheto;
    }

    public void setElerheto(boolean elerheto) {
        Elerheto = elerheto;
    }

    public void setAgyszam(int agyszam) {
        Agyszam = agyszam;
        //ket agynal nagyobb szobanal van lehetoseg potagyra
        if (agyszam>2) {this.potagy=true;} else {this.potagy=false;}
    }
}

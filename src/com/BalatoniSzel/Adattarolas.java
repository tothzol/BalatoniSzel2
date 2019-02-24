package com.BalatoniSzel;

import java.io.*;

public final class Adattarolas {

    public static Object Mentes (Object object, String fileName) {
        try {
            FileOutputStream fileOut=new FileOutputStream(new File(fileName));
            ObjectOutputStream objectOut=new ObjectOutputStream(fileOut);
            objectOut.writeObject(objectOut);
            objectOut.close();
            fileOut.close();
            return object;
        }
        catch (FileNotFoundException ex) {
            System.out.println("Nincs meg a kimeneti file: ["+ex.getMessage()+"]");
        }
        catch (IOException ex) {
            System.out.println("Nem sikerült a kimeneti adatfolyamot inicializálni: ["+ex.getMessage()+"]");
        }
        return null;
    }

    public static Object Betoltes (String fileName) {
        try{
        FileInputStream fileIn=new FileInputStream(new File(fileName));
        ObjectInputStream objectIn=new ObjectInputStream(fileIn);
        Object object=objectIn.readObject();
        objectIn.close();
        fileIn.close();
        return object;
        }
        catch (FileNotFoundException ex) {
            System.out.println("Nincs meg a bemeneti file: ["+ex.getMessage()+"]");
        }
        catch (IOException ex) {
            System.out.println("Nem sikerült a bemeneti adatfolyamot inicializálni: ["+ex.getMessage()+"]");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}

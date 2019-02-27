<<<<<<< HEAD:balatoniszel-core/src/main/java/hu/unipannon/mik/balatoniszel/core/Vendeg.java
package hu.unipannon.mik.balatoniszel.core;
=======
package hu.unipannon.mik.balatoniszel.ws;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33:balatoniszel-core/src/main/java/hu/unipannon/mik/balatoniszel/core/Vendeg.java


public class Vendeg {
    private String Nev;
    private String Okmanyszam;
    private String Lakcim;
    private String email;


    public Vendeg(String nev, String okmanyszam, String lakcim, String email) {
        this.Nev = nev;
        this.Okmanyszam = okmanyszam;
        this.Lakcim = lakcim;
        this.email = email;
    }

    public String getNev() {
        return this.Nev;
    }

    public String getOkmanyszam() {
        return this.Okmanyszam;
    }

    public String getLakcim() {
        return this.Lakcim;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLakcim(String lakcim) {
        Lakcim = lakcim;
    }

    public void setNev(String nev) {
        Nev = nev;
    }

    public void setOkmanyszam(String okmanyszam) {
        Okmanyszam = okmanyszam;
    }
}

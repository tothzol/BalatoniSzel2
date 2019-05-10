package hu.unipannon.mik.balatoniszel.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "hu.unipannon.mik.balatoniszel.server")
@XmlAccessorType(XmlAccessType.FIELD)
public class Guest {
    private String id;
    private String name;
    private String document;
    private String email;
    private String address;
    private Boolean regular;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isRegular() {return regular;}

    public void setRegular(boolean regular) {this.regular=regular;}

    public String getPassword() { return password; }

    public void setPassword( String password) {this.password=password;}

}

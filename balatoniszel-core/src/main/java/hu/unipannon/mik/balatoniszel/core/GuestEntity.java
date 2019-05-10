package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.client.Guest;
import hu.unipannon.mik.balatoniszel.client.LoginLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "guest")
public class GuestEntity {
    @Id
    private final String id;
    @Column
    private final String name;
    @Column
    private final String address;
    @Column
    private final String document;
    @Column
    private final String email;
    @Column
    private Boolean regular;
    @Column
    private final String password;
    @Column
    private LoginLevel loginLevel;

    private GuestEntity() {
        this.id = null;
        this.name = null;
        this.address = null;
        this.document = null;
        this.email = null;
        this.regular = false;
        this.password = null;
        this.loginLevel = LoginLevel.PUBLIC;
    }


    public GuestEntity(String id,
                       String name,
                       String address,
                       String document,
                       String email,
                       Boolean regular,
                       String password,
                       LoginLevel loginLevel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.document = document;
        this.email = email;
        this.regular=regular;
        this.password=password;
        this.loginLevel = loginLevel;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() { return email;}

    public boolean isRegular() { return regular; }

    public void setRegular (boolean regular) {this.regular=regular;}



    public boolean isValidPassword (String password) {
        return Objects.equals(this.password, password);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GuestEntity that = (GuestEntity) o;
        return getId().equals(that.getId()) &&
               getName().equals(that.getName()) &&
               getAddress().equals(that.getAddress()) &&
               getDocument().equals(that.getDocument()) &&
               getEmail().equals(that.getEmail()) &&
                //getPassword().equals(that.getPassword()) &&
                isRegular()==that.isRegular();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getDocument(), getEmail(), isRegular());
    }

    public Guest asGuest() {
        Guest result = new Guest();
        result.setId(id);
        result.setName(name);
        result.setAddress(address);
        result.setDocument(document);
        result.setEmail(email);
        result.setRegular(regular);
        return result;
    }

    public LoginLevel getLoginLevel() {
        return loginLevel;
    }

    public void setLoginLevel(LoginLevel level) {
        this.loginLevel = level;
    }
}

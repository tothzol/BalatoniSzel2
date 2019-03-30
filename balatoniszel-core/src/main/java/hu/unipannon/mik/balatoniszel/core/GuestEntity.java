package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Guest;

import java.util.Objects;

public class GuestEntity {
    private final String id;
    private final String name;
    private final String address;
    private final String document;
    private final String email;
    private final Boolean regular;
    private final String password;


    public GuestEntity(String id, String name, String address, String document, String email, Boolean regular, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.document = document;
        this.email = email;
        this.regular=regular;
        this.password=password;
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

    public String getEmail() {
        return email;
    }

    public boolean isRegular() { return regular; }

    public boolean isValidPassword (String password) {
//TODO: Implementation needed
        return false;
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

}

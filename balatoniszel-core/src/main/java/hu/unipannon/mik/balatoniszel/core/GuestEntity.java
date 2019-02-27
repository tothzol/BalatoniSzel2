package hu.unipannon.mik.balatoniszel.core;

<<<<<<< HEAD
import hu.unipannon.mik.balatoniszel.ws.Guest;

import java.util.Objects;

public class GuestEntity {
    private final String id;
    private final String name;
    private final String address;
    private final String document;
    private final String email;


    public GuestEntity(String id, String name, String address, String document, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.document = document;
        this.email = email;
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
               getEmail().equals(that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getDocument(), getEmail());
    }

    public Guest asGuest() {
        Guest result = new Guest();
        result.setId(id);
        result.setName(name);
        result.setAddress(address);
        result.setDocument(document);
        result.setEmail(email);
        return result;
    }
=======
public class GuestEntity {

>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
}

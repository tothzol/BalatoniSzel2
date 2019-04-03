package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Guest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class GuestRepository {

    List<GuestEntity> guests = new ArrayList<>();

    public List<GuestEntity> guests() {
        return Collections.unmodifiableList(guests);
    }


    public GuestEntity getGuest(String guestId) {
        return guests.stream()
                     .filter(guest -> guest.getId().equalsIgnoreCase(guestId))
                     .findFirst()
                     .orElse(null);
    }
    public GuestEntity getGuest(String email, String password) {
        return guests.stream()
                .filter(guest -> guest.getEmail().equalsIgnoreCase(email) &&
                        (guest.isValidPassword(password))==true)
                .findFirst()
                .orElse(null);
    }


    public GuestEntity findGuest(String name, String address, String document, String email) {
        return guests.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name) &&
                             g.getAddress().equalsIgnoreCase(address) &&
                             g.getDocument().equalsIgnoreCase(document) &&
                             g.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(create(name, address, document, email, false));
    }



    private GuestEntity create(String name, String address, String document, String email, Boolean regular) {
        //TODO: password generálás
        GuestEntity guest = new GuestEntity(UUID.randomUUID().toString(), name, address, document, email,regular,"pass");
        guests.add(guest);
        return guest;
    }

    public void addNewGuest(String name, String address, String document, String email, Boolean regular, String password) {
        //TODO: password generálás
        GuestEntity guest = new GuestEntity(UUID.randomUUID().toString(), name, address, document, email,regular,password);
        guests.add(guest);
    }

    public void saveGuest(GuestEntity guest) {
        GuestEntity g=getGuest(guest.getId());
        g.setRegular(guest.isRegular());
    }
}

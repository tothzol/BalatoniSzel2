package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Guest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                .filter(guest -> guest.getEmail().equalsIgnoreCase(email)) //&&
            //            guest.getPassword().equals(password))
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
                .orElse(null);
    }



     public void create(String name, String address, String document, String email, Boolean regular, String password) {
        //TODO: password generálás
        GuestEntity guest = new GuestEntity(UUID.randomUUID().toString(), name, address, document, email,regular, password);
        guests.add(guest);
    }


}

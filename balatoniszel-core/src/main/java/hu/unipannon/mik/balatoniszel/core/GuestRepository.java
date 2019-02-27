package hu.unipannon.mik.balatoniszel.ws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuestRepository {

    List<Guest> guests = new ArrayList<>();

    public List<Guest> guests() {
        return Collections.unmodifiableList(guests);
    }

}

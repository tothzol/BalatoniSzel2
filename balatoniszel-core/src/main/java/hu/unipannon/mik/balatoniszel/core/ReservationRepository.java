package hu.unipannon.mik.balatoniszel.ws;

import java.util.Collections;
import java.util.List;

public class ReservationRepository {

    private List<Reservation> reservations;

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> reservations() {
        return Collections.unmodifiableList(reservations);
    }

}

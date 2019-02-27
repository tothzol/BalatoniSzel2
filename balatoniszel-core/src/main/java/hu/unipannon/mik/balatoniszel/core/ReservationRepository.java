<<<<<<< HEAD
package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Reservation;

import java.util.ArrayList;
=======
package hu.unipannon.mik.balatoniszel.ws;

>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
import java.util.Collections;
import java.util.List;

public class ReservationRepository {

<<<<<<< HEAD
    private List<ReservationEntity> reservations = new ArrayList<>();

    public void addReservation(ReservationEntity reservation) {
        reservations.add(reservation);
    }

    public List<ReservationEntity> reservations() {
=======
    private List<Reservation> reservations;

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> reservations() {
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
        return Collections.unmodifiableList(reservations);
    }

}

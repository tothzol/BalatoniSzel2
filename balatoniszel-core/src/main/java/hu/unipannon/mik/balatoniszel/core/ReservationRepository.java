package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Reservation;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public class ReservationRepository {

    private List<ReservationEntity> reservations = new ArrayList<>();

    public void addReservation(ReservationEntity reservation) {
        reservations.add(reservation);
    }

    public List<ReservationEntity> reservations() {
        return Collections.unmodifiableList(reservations);
    }

}

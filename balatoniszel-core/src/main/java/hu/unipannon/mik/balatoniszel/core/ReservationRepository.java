package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Reservation;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReservationRepository {
    private Map<String, ReservationEntity> reservations = new ConcurrentHashMap<>();

    public void addReservation(ReservationEntity reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    public List<ReservationEntity> reservations() {
        return Collections.unmodifiableList(new ArrayList<>(reservations.values()));
    }

    public ReservationEntity getReservation(String reservationId) {
        return reservations.get(reservationId);
    }

    public void saveReservation(ReservationEntity reservation) {
        reservations.replace(reservation.getId(), reservation);

    }
}

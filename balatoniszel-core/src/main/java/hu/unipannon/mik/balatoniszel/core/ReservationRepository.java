package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ReservationRepository {
    private Map<String, ReservationEntity> reservations = new ConcurrentHashMap<>();

    public void addReservation(ReservationEntity reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    public List<ReservationEntity> reservations() {
        return Collections.unmodifiableList(new ArrayList<>(reservations.values()));
    }

    public List<RoomEntity> freeRooms(LocalDate date, RoomRepository roomRepository) {
                return reservations()
                .stream()
                .filter(r -> !date.isAfter(r.getDepartureDate()) && !date.isBefore(r.getArrivalDate()))
                .map(ReservationEntity::getRoomId)
                .map(roomRepository::getRoom)
                .collect(Collectors.toList());
    }

    public ReservationEntity getReservation(String reservationId) {
        return reservations.get(reservationId);
    }

    public void saveReservation(ReservationEntity reservation) {
        reservations.replace(reservation.getId(), reservation);

    }
}

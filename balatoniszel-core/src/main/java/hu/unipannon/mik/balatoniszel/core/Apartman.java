package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Apartman {
    Logger LOG = LoggerFactory.getLogger(Apartman.class);

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    public Apartman(ReservationRepository reservationRepository,
                    RoomRepository roomRepository,
                    GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
    }

    public synchronized boolean reserve(LocalDate startDate,
                                        LocalDate endDate,
                                        int numberOfBeds,
                                        String name,
                                        String address,
                                        String document,
                                        String email) {
        List<RoomEntity> freeRooms = getFreeRooms(startDate, endDate, numberOfBeds);
        if(freeRooms.size() == 0) {
            LOG.info("No free rooms");
            return false;
        }
        RoomEntity reservedRoom = freeRooms.get(0);
        GuestEntity guest = guestRepository.findGuest(name, address, document, email);
        ReservationEntity reservationEntity = new ReservationEntity(UUID.randomUUID().toString(),
                                                                    startDate,
                                                                    endDate,
                                                                    numberOfBeds,
                                                                    guest.getId(),
                                                                    reservedRoom.getId());
        reservationRepository.addReservation(reservationEntity);
        LOG.info("Reservation saved.");
        return true;
    }

    public synchronized List<RoomEntity> getFreeRooms(LocalDate startDate, LocalDate endDate, int numberOfBeds) {
        List<RoomEntity> freeRooms = new ArrayList<>(roomRepository.rooms());
        LocalDate currentDate = startDate;
        while(!currentDate.isAfter(endDate)) {
            List<RoomEntity> reservedRooms = reservedRooms(currentDate);
            freeRooms.removeAll(reservedRooms);
            currentDate = currentDate.plus(1, ChronoUnit.DAYS);
        }
        return freeRooms;
    }

    private List<RoomEntity> reservedRooms(LocalDate currentDate) {
        return reservationRepository.reservations()
                .stream()
                .filter(r -> !currentDate.isAfter(r.getEndDate()) && !currentDate.isBefore(r.getStartDate()))
                .map(ReservationEntity::getRoomId)
                .map(roomRepository::getRoom)
                .collect(Collectors.toList());
    }

    public List<Reservation> reservations() {
        return reservationRepository.reservations()
                .stream()
                .map(r -> r.asReservation(guestRepository, roomRepository))
                .collect(Collectors.toList());
    }


}

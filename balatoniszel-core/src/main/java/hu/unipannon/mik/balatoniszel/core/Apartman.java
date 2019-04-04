package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Guest;
import hu.unipannon.mik.balatoniszel.ws.Reservation;
import hu.unipannon.mik.balatoniszel.ws.SpecialDays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class Apartman {
    Logger LOG = LoggerFactory.getLogger(Apartman.class);

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final SpecialDaysRepository specialDaysRepository;

    @Autowired
    public Apartman(ReservationRepository reservationRepository,
                    RoomRepository roomRepository,
                    GuestRepository guestRepository,
                    SpecialDaysRepository specialDaysRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.specialDaysRepository = specialDaysRepository;
    }

    public synchronized boolean reserve(LocalDate startDate,
                                        LocalDate endDate,
                                        int numberOfBeds,
                                        String name,
                                        String address,
                                        String document,
                                        String email) {
        List<RoomEntity> freeRooms = getFreeRooms(startDate, endDate, numberOfBeds);

        if (freeRooms.size() == 0) {
            LOG.info("No free rooms");
            return false;
        }
        freeRooms.sort(Comparator.comparing(RoomEntity::getNumberOfBeds));  //rendezi ágyszám szerint
        RoomEntity reservedRoom = freeRooms.get(0);
        GuestEntity guest = guestRepository.findOrCreateGuest(name, address, document, email);
        ReservationEntity reservationEntity = new ReservationEntity(UUID.randomUUID().toString(),
                startDate,
                endDate,
                numberOfBeds,
                guest.getId(),
                reservedRoom.getId(),
                LocalDateTime.now());
        reservationRepository.addReservation(reservationEntity);
        LOG.info("Reservation saved.");
        return true;
    }

    synchronized List<RoomEntity> getFreeRooms(LocalDate startDate, LocalDate endDate, int numberOfBeds) {
        List<RoomEntity> freeRooms = new ArrayList<>(roomRepository.rooms());
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            List<RoomEntity> reservedRooms = reservedRooms(currentDate);
            freeRooms.removeAll(reservedRooms);
            freeRooms.removeAll(roomRepository.tooSmall(numberOfBeds)); //itt veszi le a kisebb ágyszámú szobákat

            currentDate = currentDate.plus(1, ChronoUnit.DAYS);
        }
        return freeRooms;
    }

    private List<RoomEntity> reservedRooms(LocalDate currentDate) {
        return reservationRepository.reservedRooms(currentDate, roomRepository);
    }

    public List<Reservation> reservations() {
        return reservationRepository.reservations()
                .stream()
                .map(r -> r.asReservation(guestRepository, roomRepository, specialDaysRepository))
                .collect(Collectors.toList());
    }


    public ReservationEntity getReservation(String reservationId) {
        return reservationRepository.getReservation(reservationId);
    }

    public void saveReservation(ReservationEntity reservation) {
        reservationRepository.saveReservation(reservation);
    }

    public void setSpecialDays(LocalDate startDate, LocalDate endDate) {
        specialDaysRepository.markDayAsSpecialDay(startDate, endDate);
    }

    public List<SpecialDays> getSpecialDays() {
        return specialDaysRepository.getSpecialDays()
                .stream()
                .map(SpecialDaysEntity::asSpecialDays)
                .collect(Collectors.toList());
    }

    public void deleteSpecialDays(String id) {
        specialDaysRepository.deleteSpecialDays(id);
    }

    public void setDeposit(String reservationId, int deposit) {
        ReservationEntity reservation = reservationRepository.getReservation(reservationId);
        if (reservation != null) {
            reservation.setDeposit(deposit);
            reservationRepository.saveReservation(reservation);
        }
    }

    public List<Guest> guests() {
        return guestRepository.guests()
                .stream()
                .map(g -> g.asGuest())
                .collect(Collectors.toList());
    }

    public void newGuest(String name, String email, String address, String document, String password, String passwordOneMore) {
        if (password.equals(passwordOneMore)) {
            guestRepository.addNewGuest(name, address, document, email, false, password);
        }
    }

    public GuestEntity getGuest(String guestID) {
        return guestRepository.getGuest(guestID);
    }

    public Guest getGuest(String email, String password) {
        return guestRepository.getGuest(email, password).asGuest();
    }

    public void setRegular(String guestID, boolean regular) {
        GuestEntity g = getGuest(guestID);
        g.setRegular(regular);
        guestRepository.saveGuest(g);
    }
}

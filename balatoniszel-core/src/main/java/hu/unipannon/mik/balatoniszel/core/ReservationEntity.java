package hu.unipannon.mik.balatoniszel.core;

<<<<<<< HEAD
import hu.unipannon.mik.balatoniszel.ws.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationEntity {
    private final String    id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int       numberOfBeds;
    private final String     guestId;
    private final String roomId;

    public ReservationEntity(String id,
                             LocalDate startDate,
                             LocalDate endDate,
                             int numberOfBeds,
                             String guestId,
                             String roomId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfBeds = numberOfBeds;
        this.guestId = guestId;
        this.roomId = roomId;
    }

    public String getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getRoomId() {
        return roomId;
    }

    public Reservation asReservation(GuestRepository guestRepository, RoomRepository roomRepository) {
        Reservation result = new Reservation();
        result.setId(id);
        result.setStartDate(startDate.format(DateTimeFormatter.ISO_DATE));
        result.setEndDate(endDate.format(DateTimeFormatter.ISO_DATE));
        result.setNumberOfBeds(numberOfBeds);
        result.setGuest(guestRepository.getGuest(guestId).asGuest());
        result.setRoom(roomRepository.getRoom(roomId).asRoom());
        return result;
    }

=======
public class ReservationEntity {
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33

}

package hu.unipannon.mik.balatoniszel.core;


import hu.unipannon.mik.balatoniszel.ws.Reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Entity(name = "reservation")
public class ReservationEntity {
    @Id
    private final String    id;
    @Column
    private final LocalDate arrivalDate;
    @Column
    private final LocalDate departureDate;
    @Column
    private final int       numberOfBeds;
    @Column
    private final String     guestId;
    @Column
    private final String roomId;
    @Column
    private int deposit;
    @Column
    private final LocalDateTime reservationDate;

    private ReservationEntity() {
        this.id = null;
        this.arrivalDate = null;
        this.departureDate = null;
        this.numberOfBeds = 0;
        this.guestId = null;
        this.roomId = null;
        this.reservationDate = null;
    }

    public ReservationEntity(String id,
                             LocalDate arrivalDate,
                             LocalDate departureDate,
                             int numberOfBeds,
                             String guestId,
                             String roomId,
                             LocalDateTime reservationDate) {
        this.id = id;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.numberOfBeds = numberOfBeds;
        this.guestId = guestId;
        this.roomId = roomId;
        this.deposit = 0;
        this.reservationDate = reservationDate;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public String getId() {
        return id;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
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

    public Reservation asReservation(GuestRepository guestRepository,
                                     RoomRepository roomRepository,
                                     SpecialDaysRepository specialDaysRepository) {
        Reservation result = new Reservation();
        result.setId(id);
        result.setArrivalDate(arrivalDate.format(DateTimeFormatter.ISO_DATE));
        result.setDepartureDate(departureDate.format(DateTimeFormatter.ISO_DATE));
        result.setNumberOfBeds(numberOfBeds);
        result.setGuest(guestRepository.getGuest(guestId).asGuest());
        result.setRoom(roomRepository.getRoom(roomId).asRoom());
        result.setDeposit(deposit);
        result.setHasEnoughDeposit(hasEnoughDeposit(specialDaysRepository));
        result.setPrice(calculatePrice(specialDaysRepository));
        String formattedReservationDate = reservationDate.format(DateTimeFormatter.ISO_DATE_TIME);
        result.setReservationDate(formattedReservationDate);
        return result;
    }

    public int getPriceForDay(LocalDate day, SpecialDaysRepository specialDaysRepository) {
        if(specialDaysRepository.isSpecialDay(day)) {
            return 10000 * numberOfBeds;
        } else {
            return 5000 * numberOfBeds;
        }
    }

    private int calculatePrice(SpecialDaysRepository specialDaysRepository) {
        int price = 0;
        LocalDate currentDate = arrivalDate;
        while(currentDate.isBefore(departureDate)) {
            price += getPriceForDay(currentDate, specialDaysRepository);
            currentDate = currentDate.plus(1, ChronoUnit.DAYS);
        }
        return price;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public boolean hasEnoughDeposit(SpecialDaysRepository specialDaysRepository) {
        return deposit > getPriceForDay(arrivalDate, specialDaysRepository);
    }
}

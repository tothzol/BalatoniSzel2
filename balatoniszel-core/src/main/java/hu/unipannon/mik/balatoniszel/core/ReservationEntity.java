package hu.unipannon.mik.balatoniszel.core;


import hu.unipannon.mik.balatoniszel.ws.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationEntity {
    
    private final String    id;
    private final LocalDate arrivalDate;
    private final LocalDate departureDate;
    private final int       numberOfBeds;
    private final String     guestId;
    private final String roomId;
    private int deposit;
    private final LocalDateTime reservationDate;

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

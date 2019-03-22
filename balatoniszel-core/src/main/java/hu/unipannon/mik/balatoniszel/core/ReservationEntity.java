package hu.unipannon.mik.balatoniszel.core;


import hu.unipannon.mik.balatoniszel.ws.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationEntity {
    private final String    id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int       numberOfBeds;
    private final String     guestId;
    private final String roomId;
    private int deposit;

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
        this.deposit = 0;
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

    public Reservation asReservation(GuestRepository guestRepository,
                                     RoomRepository roomRepository,
                                     SpecialDaysRepository specialDaysRepository) {
        Reservation result = new Reservation();
        result.setId(id);
        result.setStartDate(startDate.format(DateTimeFormatter.ISO_DATE));
        result.setEndDate(endDate.format(DateTimeFormatter.ISO_DATE));
        result.setNumberOfBeds(numberOfBeds);
        result.setGuest(guestRepository.getGuest(guestId).asGuest());
        result.setRoom(roomRepository.getRoom(roomId).asRoom());
        result.setDeposit(deposit);
        result.setHasEnoughDeposit(hasEnoughDeposit(specialDaysRepository));
        result.setPrice(calculatePrice(specialDaysRepository));
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
        LocalDate currentDate = startDate;
        while(!currentDate.isAfter(endDate)) {
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
        return deposit > getPriceForDay(startDate, specialDaysRepository);
    }
}

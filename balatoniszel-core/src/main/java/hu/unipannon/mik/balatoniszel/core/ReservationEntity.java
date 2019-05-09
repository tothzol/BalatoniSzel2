package hu.unipannon.mik.balatoniszel.core;


import hu.unipannon.mik.balatoniszel.server.Reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
    @Column
    private boolean expired;

    private ReservationEntity() {
        this.id = null;
        this.arrivalDate = null;
        this.departureDate = null;
        this.numberOfBeds = 0;
        this.guestId = null;
        this.roomId = null;
        this.reservationDate = null;
        this.expired = false;
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
        this.expired = false;
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
        result.setExpired(expired);
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

    /**
     * Checks, wether the reservation has enough deposit or not. The deposit has to cover at least the first day price.
     * @param specialDaysRepository
     * @return
     */
    private boolean hasEnoughDeposit(SpecialDaysRepository specialDaysRepository) {
        return deposit >= getPriceForDay(arrivalDate, specialDaysRepository);
    }

    /**
     * Updates the expiration, if the reservation does not have enough deposit,
     * when the current date is 4 days before the arrival date, the reservation is expired.
     * This will be called:
     * - upon each Administrator login (TODO)
     * - on each day, 5 minutes after midnight from a scheduled task
     * @param specialDaysRepository
     * @param currentDate
     */
    public boolean updateExpiration(SpecialDaysRepository specialDaysRepository, LocalDate currentDate) {
        boolean shallUpdateExpiration = Period.between(currentDate, arrivalDate).get(ChronoUnit.DAYS) == 4; // end is exclusive
        boolean doesNotHaveEnoughDeposit = !hasEnoughDeposit(specialDaysRepository);
        if(shallUpdateExpiration && doesNotHaveEnoughDeposit) {
            this.expired = true;
        }
        return this.expired;
    }
}

package hu.unipannon.mik.balatoniszel.ws;

import hu.unipannon.mik.balatoniszel.core.Apartman;
import hu.unipannon.mik.balatoniszel.core.ReservationEntity;
import hu.unipannon.mik.balatoniszel.core.ReservationRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;

public class BalatoniSzelImpl implements BalatoniSzel {

    private final Apartman apartman;

    public BalatoniSzelImpl(Apartman apartman) {
        this.apartman = apartman;
    }

    @Override
    public boolean reserve(String startDate,
                           String endDate,
                           int numberOfBeds,
                           String name,
                           String address,
                           String document,
                           String email) {
        return apartman.reserve(LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE),
                                LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE),
                                numberOfBeds,
                                name,
                                address,
                                document,
                                email);
    }

    @Override
    public List<Reservation> reservations() {
        return apartman.reservations();
    }

    @Override
    public void setDeposit(String reservationId, int deposit) {
        
        apartman.setDeposit(reservationId, deposit);
    }

    @Override
    public void setSpecialDays(String startDate, String endDate) {
        apartman.setSpecialDays(
                LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE),
                LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE)
        );
    }

    @Override
    public List<SpecialDays> getSpecialDays() {
        return apartman.getSpecialDays();
    }

    @Override
    public void deleteSpecialDays(String id) {
        apartman.deleteSpecialDays(id);
    }

    @Override
    public  void newGuest(String name, String email,String address, String document, String password) {
        apartman.newGuest(name,email,address,document,password);
    }
    @Override
    public List<Guest> guests(){
       return apartman.guests();
    }
}

package hu.unipannon.mik.balatoniszel.ws;

import hu.unipannon.mik.balatoniszel.core.Apartman;

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
                                LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE),
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
}

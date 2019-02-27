<<<<<<< HEAD
package hu.unipannon.mik.balatoniszel.ws;

import hu.unipannon.mik.balatoniszel.core.Apartman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
=======
package hu.unipannon.mik.balatoniszel;

import java.time.LocalDate;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
import java.util.Collections;
import java.util.List;

public class BalatoniSzelImpl implements BalatoniSzel {

<<<<<<< HEAD
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
=======
    @Override
    public boolean reserve(LocalDate startDate,
                           LocalDate endDate,
                           int numberOfBeds,
                           String name,
                           String document,
                           String address,
                           String email) {
        return false;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
    }

    @Override
    public List<Reservation> reservations() {
<<<<<<< HEAD
        return apartman.reservations();
=======
        return Collections.emptyList();
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
    }
}

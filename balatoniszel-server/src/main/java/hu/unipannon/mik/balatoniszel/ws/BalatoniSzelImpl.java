package hu.unipannon.mik.balatoniszel;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class BalatoniSzelImpl implements BalatoniSzel {

    @Override
    public boolean reserve(LocalDate startDate,
                           LocalDate endDate,
                           int numberOfBeds,
                           String name,
                           String document,
                           String address,
                           String email) {
        return false;
    }

    @Override
    public List<Reservation> reservations() {
        return Collections.emptyList();
    }
}

package hu.unipannon.mik.balatoniszel;

import java.time.LocalDate;
import java.util.UUID;

public interface Reservation {
    String id();
    LocalDate startDate();
    LocalDate endDate();
    int numberOfBeds();
    Guest guest();
}

package hu.unipannon.mik.balatoniszel;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;

@WebService
public interface BalatoniSzel {
    @WebMethod
    boolean reserve(LocalDate startDate, LocalDate endDate, int numberOfBeds, String name, String document, String address, String email);

    @WebMethod
    List<Reservation> reservations();
}

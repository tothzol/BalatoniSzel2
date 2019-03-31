package hu.unipannon.mik.balatoniszel.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;

@WebService
public interface BalatoniSzel {
    @WebMethod
    boolean reserve(String startDate, String endDate, int numberOfBeds, String name, String address, String document, String email);

    @WebMethod
    List<Reservation> reservations();

    @WebMethod
    void setDeposit(String reservationId, int deposit);

    @WebMethod
    void setSpecialDays(String startDate, String endDate);

    @WebMethod
    List<SpecialDays> getSpecialDays();

    @WebMethod
    void deleteSpecialDays(String id);

    @WebMethod
    void addGuest (String name, String address, String document, String email, String password);

    @WebMethod
    Boolean isEmailValid(String email);
}

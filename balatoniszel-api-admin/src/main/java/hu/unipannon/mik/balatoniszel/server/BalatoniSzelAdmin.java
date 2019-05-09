package hu.unipannon.mik.balatoniszel.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface BalatoniSzelAdmin extends BalatoniSzelGuest{

    @WebMethod
    List<Reservation> reservations(String token);

    @WebMethod
    void setDeposit(String token, String reservationId, int deposit);

    @WebMethod
    void setSpecialDays(String token, String startDate, String endDate);

    @WebMethod
    List<SpecialDays> getSpecialDays(String token);

    @WebMethod
    void deleteSpecialDays(String token, String id);

    @WebMethod
    List<Guest> guests(String token);

    @WebMethod
    void setRegular(String token, String guestID, boolean regular);
}

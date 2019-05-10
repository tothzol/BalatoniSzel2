package hu.unipannon.mik.balatoniszel.client;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface BalatoniSzelGuest extends BalatoniSzelPublic {
    @WebMethod
    boolean reserve(String token,
                    String arrivalDate,
                    String departureDate,
                    int numberOfBeds);

    @WebMethod
    List<Reservation> reservations(String token);
}

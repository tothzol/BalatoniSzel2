package hu.unipannon.mik.balatoniszel.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BalatoniSzelGuest extends BalatoniSzelPublic {
    @WebMethod
    boolean reserve(String token,
                    String arrivalDate,
                    String departureDate,
                    int numberOfBeds,
                    String name,
                    String address,
                    String document,
                    String email);
}

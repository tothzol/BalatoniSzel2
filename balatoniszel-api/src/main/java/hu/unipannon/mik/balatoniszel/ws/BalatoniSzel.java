<<<<<<< HEAD
package hu.unipannon.mik.balatoniszel.ws;
=======
package hu.unipannon.mik.balatoniszel;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;

@WebService
public interface BalatoniSzel {
    @WebMethod
<<<<<<< HEAD
    boolean reserve(String startDate, String endDate, int numberOfBeds, String name, String address, String document, String email);
=======
    boolean reserve(LocalDate startDate, LocalDate endDate, int numberOfBeds, String name, String document, String address, String email);
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33

    @WebMethod
    List<Reservation> reservations();
}

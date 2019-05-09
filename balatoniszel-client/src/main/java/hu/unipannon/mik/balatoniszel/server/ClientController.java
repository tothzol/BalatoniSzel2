package hu.unipannon.mik.balatoniszel.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ClientController {
    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    private final BalatoniSzel client;

    @Autowired
    public ClientController(BalatoniSzel client) {
        this.client = client;
    }

    @GetMapping(path = "/")
    public ModelAndView index() {
        ModelAndView      indexView = new ModelAndView("index");
        List<Reservation> reservationList = client.reservations();
        indexView.addObject("reservations", reservationList);
        List<Guest> guestList=client.guests();
        indexView.addObject("guests", guestList);
        return indexView;
    }

    @PostMapping(path="/reserve")
    public ModelAndView reserve(@RequestParam(name = "arrivalDate") String arrivalDate,
                                @RequestParam(name = "departureDate") String departureDate,
                                @RequestParam(name = "numberOfBeds") int numberOfBeds,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "address") String address,
                                @RequestParam(name = "document") String document,
                                @RequestParam(name = "email") String email) {
        client.reserve(arrivalDate, departureDate, numberOfBeds, name, document, address, email);
        return new ModelAndView("redirect:/");
    }

    @PostMapping(path="/setDeposit")
    public ModelAndView setDeposit(@RequestParam(name = "reservationId") String reservationId,
                                   @RequestParam(name = "deposit") int deposit) {
        client.setDeposit(reservationId, deposit);
        return new ModelAndView("redirect:/");
    }

}

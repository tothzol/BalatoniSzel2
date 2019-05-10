package hu.unipannon.mik.balatoniszel.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class GuestClientController extends BaseController {
    @Autowired
    public GuestClientController(ClientFactory clientFactory, @Value("${public.url}") String publicUrl) {
        super(clientFactory, publicUrl);
    }

    @GetMapping(path = "/guest")
    public ModelAndView guestIndex(HttpSession session) {
        BalatoniSzelGuest client = getGuestClient(session);
        if(client != null) {
            ModelAndView reservationsView = new ModelAndView("guest_index");
            reservationsView.addObject("reservations", client.reservations(getToken(session)));
            return reservationsView;
        } else {
            return UNAUTH_VIEW;
        }
    }

    @PostMapping(path = "/guest/reserve")
    public ModelAndView reserve(
            @RequestParam("arrivalDate") String arrivalDate,
            @RequestParam("departureDate")String departureDate,
            @RequestParam("numberOfBeds") int numberOfBeds,
            HttpSession session) {
        BalatoniSzelGuest client = getGuestClient(session);
        if(client != null) {
            client.reserve(getToken(session), arrivalDate, departureDate, numberOfBeds);
            return new ModelAndView("redirect:/");
        } else {
            return UNAUTH_VIEW;
        }
    }
}

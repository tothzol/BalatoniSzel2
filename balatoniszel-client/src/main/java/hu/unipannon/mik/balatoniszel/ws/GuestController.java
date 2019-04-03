package hu.unipannon.mik.balatoniszel.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RestController
public class GuestController {

    private final BalatoniSzel client;

    @Autowired
    public GuestController(BalatoniSzel client) {
        this.client = client;
    }

    @GetMapping(path = "/guest")
    public ModelAndView guest() {
        ModelAndView      guestView = new ModelAndView("guest");
        guestView.addObject("guests", client.guests());
        return guestView;
    }

    @PostMapping(path="/newGuest")
    public ModelAndView newGuest(   @RequestParam(name = "guestName") String name,
                                    @RequestParam(name = "guestEmail") String email,
                                    @RequestParam(name = "guestAddress") String address,
                                    @RequestParam(name = "guestDocument") String document,
                                    @RequestParam(name = "guestPassword") String password,
                                    @RequestParam(name = "guestPasswordOneMore") String passwordOneMore){
        client.newGuest(name,email,address,document, password,passwordOneMore);
        return new ModelAndView("redirect:/guest");
    }

    @PostMapping(path="/setRegular")
    public ModelAndView newGuest(   @RequestParam(name = "guestId") String guestId,
                                    @RequestParam(name = "regular") boolean regular){
        client.setRegular(guestId, regular);
        return new ModelAndView("redirect:/guest");
    }
}

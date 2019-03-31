package hu.unipannon.mik.balatoniszel.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RestController
public class guestController {

    private final BalatoniSzel client;

    @Autowired
    public guestController(BalatoniSzel client) {
        this.client = client;
    }

    @GetMapping(path = "/guest")
    public ModelAndView guest() {
        ModelAndView      guestView       = new ModelAndView("guest");
        return guestView;
    }
    @PostMapping(path="/newGuest")
    public ModelAndView newGuest(   @RequestParam(name = "guestName") String name,
                                    @RequestParam(name = "guestEmail") String email,
                                    @RequestParam(name = "guestAddress") String address,
                                    @RequestParam(name = "guestDocument") String document,
                                    @RequestParam(name = "guestPassword") String password) {
            client.newGuest(name,email,address,document, password);
        return new ModelAndView("redirect:/guest");
    }
}

package hu.unipannon.mik.balatoniszel.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewGuestController {
    private final BalatoniSzel client;

    @Autowired
    public NewGuestController(BalatoniSzel client) {
        this.client = client;
    }

    @GetMapping(path="/newGuest")
    public ModelAndView newGuest() {
        ModelAndView      newGuestView       = new ModelAndView("newGuest");
        return newGuestView;
    }

    @PostMapping(path="/addNewGuest")
    public ModelAndView  addGuest(@RequestParam(name = "guestName") String name,
                                  @RequestParam(name = "guestAddress") String address,
                                  @RequestParam(name = "guestDocument") String document,
                                  @RequestParam(name = "guestEmail") String email,
                                  @RequestParam(name = "guestPassword") String password,
                                  @RequestParam(name = "guestPasswordAgain") String passwordAgain) {
        client.newGuest(name, address, email, document, password,passwordAgain);
        return new ModelAndView("redirect:/newGuest");
    }

}

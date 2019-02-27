package hu.unipannon.mik.balatoniszel;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private final BalatoniSzel client;

    @Autowired
    public ClientController(BalatoniSzel client) {
        this.client = client;
    }

    @PostMapping
    public String reserve() {
        return null;
    }

    @GetMapping
    public String showReservations() {
        return null;
    }

}

package hu.unipannon.mik.balatoniszel.server;

import hu.unipannon.mik.balatoniszel.client.LoginLevel;
import hu.unipannon.mik.balatoniszel.core.GuestEntity;
import hu.unipannon.mik.balatoniszel.core.GuestRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AdminUserLoader {

    private final GuestRepository guestRepository;


    public AdminUserLoader(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @PostConstruct
    public void postConstruct() {
        if(guestRepository.getGuest("admin", "admin") == null) {
            GuestEntity admin = guestRepository.addNewGuest("Admin", "Admin", "Admin", "admin", true, "admin");
            admin.setLoginLevel(LoginLevel.ADMIN);
            guestRepository.saveGuest(admin);
        }
    }
}

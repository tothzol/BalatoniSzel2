package hu.unipannon.mik.balatoniszel.client;

import hu.unipannon.mik.balatoniszel.core.Apartman;
import hu.unipannon.mik.balatoniszel.core.GuestEntity;
import hu.unipannon.mik.balatoniszel.core.GuestRepository;
import hu.unipannon.mik.balatoniszel.core.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class BalatoniSzelGuestImpl extends BalatoniSzelPublicImpl implements BalatoniSzelGuest {
    private static final Logger LOG = LoggerFactory.getLogger(BalatoniSzelGuestImpl.class);
    protected final Apartman apartman;

    public BalatoniSzelGuestImpl(Apartman apartman,
                                 GuestRepository guestRepository,
                                 SessionRepository sessionRepository,
                                 ServerUrls serverUrls,
                                 Clock clock) {
        super(guestRepository, sessionRepository, serverUrls, clock);
        this.apartman = apartman;
    }

    @Override
    public boolean reserve(String token,
                           String arrivalDate,
                           String departureDate,
                           int numberOfBeds) {
        if(checkTokenFor(token, LoginLevel.GUEST)) {
            GuestEntity guest = getGuestFor(token);
            return apartman.reserve(
                    LocalDate.parse(arrivalDate, DateTimeFormatter.ISO_DATE),
                    LocalDate.parse(departureDate, DateTimeFormatter.ISO_DATE),
                    numberOfBeds,
                    guest.getName(),
                    guest.getAddress(),
                    guest.getDocument(),
                    guest.getEmail());
        } else {
            return false;
        }
    }

    @Override
    public List<Reservation> reservations(String token) {
        if(checkTokenFor(token, LoginLevel.GUEST)) {
            LOG.info("auth check OK");
            return apartman.reservationsFor(getGuestFor(token));
        } else {
            LOG.info("auth check fail");
            return Collections.emptyList();
        }
    }


}

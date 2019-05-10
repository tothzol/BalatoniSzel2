package hu.unipannon.mik.balatoniszel.client;

import hu.unipannon.mik.balatoniszel.core.Apartman;
import hu.unipannon.mik.balatoniszel.core.GuestRepository;
import hu.unipannon.mik.balatoniszel.core.SessionRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Collections;
import java.util.List;

public class BalatoniSzelAdminImpl extends BalatoniSzelGuestImpl implements BalatoniSzelAdmin {


    public BalatoniSzelAdminImpl(Apartman apartman,
                                 GuestRepository guestRepository,
                                 SessionRepository sessionRepository,
                                 ServerUrls serverUrls,
                                 Clock clock) {
        super(apartman, guestRepository, sessionRepository, serverUrls, clock);
    }


    @Override
    public List<Reservation> reservations(String token) {
        if(checkTokenFor(token, LoginLevel.ADMIN)) {
            return apartman.reservations();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void setDeposit(String token, String reservationId, int deposit) {
        if(checkTokenFor(token, LoginLevel.ADMIN)) {
            apartman.setDeposit(reservationId, deposit);
        }
    }

    @Override
    public void setSpecialDays(String token, String startDate, String endDate) {
        if(checkTokenFor(token, LoginLevel.ADMIN)) {
            apartman.setSpecialDays(
                    LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE),
                    LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE)
            );
        }
    }

    @Override
    public List<SpecialDays> getSpecialDays(String token) {
        if(checkTokenFor(token, LoginLevel.ADMIN)) {
            return apartman.getSpecialDays();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteSpecialDays(String token, String id) {
        if(checkTokenFor(token, LoginLevel.ADMIN)) {
            apartman.deleteSpecialDays(id);
        }
    }

    @Override
    public List<Guest> guests(String token) {
        if(checkTokenFor(token, LoginLevel.ADMIN)) {
            return apartman.guests();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void setRegular(String token, String guestID, boolean regular) {
        if(checkTokenFor(token, LoginLevel.ADMIN)) {
            apartman.setRegular(guestID, regular);
        }
    }


}

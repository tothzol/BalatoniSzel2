package hu.unipannon.mik.balatoniszel.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;

@Component
public class ExpirationChecker implements Runnable {

    private final Logger LOG = LoggerFactory.getLogger(ExpirationChecker.class);

    private final Clock clock;
    private final ReservationRepository reservationRepository;
    private final SpecialDaysRepository specialDaysRepository;

    @Autowired
    public ExpirationChecker(Clock clock,
                             ReservationRepository reservationRepository,
                             SpecialDaysRepository specialDaysRepository) {
        this.clock = clock;
        this.reservationRepository = reservationRepository;
        this.specialDaysRepository = specialDaysRepository;
    }


    @Override
    public void run() {
        try {
            LOG.info("Checking expiration of non-expired reservations");
            LocalDate currentDate = LocalDate.now(clock);
            reservationRepository
                    .notExpiredReservations()
                    .stream()
                    .filter(r -> r.updateExpiration(specialDaysRepository, currentDate))
                    .forEach(reservationRepository::saveReservation);
        } catch(Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

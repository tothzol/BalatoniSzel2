package hu.unipannon.mik.balatoniszel.ws;

import hu.unipannon.mik.balatoniszel.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApartmanConfiguration {

    @Bean
    public Apartman apartman() {
        return new Apartman(new ReservationRepository(),
                            new RoomRepository(),
                            new GuestRepository(),
                            new SpecialDaysRepository());
    }
}

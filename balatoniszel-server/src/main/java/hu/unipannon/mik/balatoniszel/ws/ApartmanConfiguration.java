package hu.unipannon.mik.balatoniszel.ws;

import hu.unipannon.mik.balatoniszel.core.Apartman;
import hu.unipannon.mik.balatoniszel.core.GuestRepository;
import hu.unipannon.mik.balatoniszel.core.ReservationRepository;
import hu.unipannon.mik.balatoniszel.core.RoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApartmanConfiguration {

    @Bean
    public Apartman apartman() {
        return new Apartman(new ReservationRepository(), new RoomRepository(), new GuestRepository());
    }
}

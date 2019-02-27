package hu.unipannon.mik.balatoniszel.ws;

<<<<<<< HEAD
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
=======
public class ApartmanConfiguration {

>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
}

package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Guest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class guestTest {

    @Test
    public void oneUserAddTest () {
        //Given
        RoomRepository roomRepository=new RoomRepository();
        ReservationRepository reservationRepository=new ReservationRepository();
        GuestRepository guestRepository=new GuestRepository();
        SpecialDaysRepository specialDaysRepository=new SpecialDaysRepository();
        Apartman a=new Apartman(reservationRepository,roomRepository,guestRepository,specialDaysRepository);

        //When
        a.newGuest("Zoli","zoli@zoli.hu)","1111 Sé Bő u. 1","123456ab","123456");
        //Then
        List<Guest> guests=a.guests();

        Assertions.assertEquals(guests.size(),1);

    }
}

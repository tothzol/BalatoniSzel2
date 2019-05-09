package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.server.Guest;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class GuestTest {

    public void oneUserAddTest () {
        //Given
        RoomRepository roomRepository=new RoomRepository();
        ReservationRepository reservationRepository=new ReservationRepository(null);
        GuestRepository guestRepository=new GuestRepository(null);
        SpecialDaysRepository specialDaysRepository=new SpecialDaysRepository(null);
        Apartman a=new Apartman(reservationRepository,roomRepository,guestRepository,specialDaysRepository);

        //When
        a.newGuest("Zoli","zoli@zoli.hu)","1111 Sé Bő u. 1","123456ab","123456", "123456");
        //Then
        List<Guest> guests=a.guests();

        Assertions.assertEquals(guests.size(),1);

    }

    public void userLoginTest () {
        //Given
        RoomRepository roomRepository=new RoomRepository();
        ReservationRepository reservationRepository=new ReservationRepository(null);
        GuestRepository guestRepository=new GuestRepository(null);
        SpecialDaysRepository specialDaysRepository=new SpecialDaysRepository(null);
        Apartman a=new Apartman(reservationRepository,roomRepository,guestRepository,specialDaysRepository);

        //When
        a.newGuest("Zoli","zoli@zoli.hu","1111 Sé Bő u. 1","123456ab","123456", "123456");
        //Then
        List<Guest> guests=a.guests();
        Guest g1=a.getGuest("zoli@zoli.hu","123456");
      Assertions.assertEquals(g1.getEmail(),"zoli@zoli.hu");



    }

}

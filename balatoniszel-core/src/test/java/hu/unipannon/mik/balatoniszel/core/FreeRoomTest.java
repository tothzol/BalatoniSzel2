package hu.unipannon.mik.balatoniszel.core;

import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class FreeRoomTest {
    public void testAllRoomsAvaliable(){
        //Given
        RoomRepository roomRepository=new RoomRepository();
        ReservationRepository reservationRepository=new ReservationRepository(null);
        GuestRepository guestRepository=new GuestRepository(null);
        SpecialDaysRepository specialDaysRepository=new SpecialDaysRepository(null);
        Apartman a=new Apartman(reservationRepository,roomRepository,guestRepository,specialDaysRepository);


        //when
        List<RoomEntity> freeRooms=a.getFreeRooms(LocalDate.of(2019,03,29),
                                                    LocalDate.of(2019,04,01),
                                                    2);

        //then
        Assertions.assertEquals(freeRooms.size(),roomRepository.rooms().size());

    }
    public void testRoom4Avaliable(){
        //Given
        RoomRepository roomRepository=new RoomRepository();
        ReservationRepository reservationRepository=new ReservationRepository(null);
        GuestRepository guestRepository=new GuestRepository(null);
        SpecialDaysRepository specialDaysRepository=new SpecialDaysRepository(null);
        Apartman a=new Apartman(reservationRepository,roomRepository,guestRepository,specialDaysRepository);


        //when
        List<RoomEntity> freeRooms=a.getFreeRooms(LocalDate.of(2019,03,29),
                LocalDate.of(2019,04,01),
                4);

        //then
        Assertions.assertEquals(freeRooms.size(),1);

    }

    public void testRoom3Avaliable(){
        //Given
        RoomRepository roomRepository=new RoomRepository();
        ReservationRepository reservationRepository=new ReservationRepository(null);
        GuestRepository guestRepository=new GuestRepository(null);
        SpecialDaysRepository specialDaysRepository=new SpecialDaysRepository(null);
        Apartman a=new Apartman(reservationRepository,roomRepository,guestRepository,specialDaysRepository);


        //when
        List<RoomEntity> freeRooms=a.getFreeRooms(LocalDate.of(2019,03,29),
                LocalDate.of(2019,04,01),
                3);

        //then
        Assertions.assertEquals(freeRooms.size(),3);

    }



}

package hu.unipannon.mik.balatoniszel.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class FreeRoomTest {
    @Test
    public void testAllRoomsAvaliable(){
        //Given
        RoomRepository roomRepository=new RoomRepository();
        ReservationRepository reservationRepository=new ReservationRepository();
        GuestRepository guestRepository=new GuestRepository();
        SpecialDaysRepository specialDaysRepository=new SpecialDaysRepository();
        Apartman a=new Apartman(reservationRepository,roomRepository,guestRepository,specialDaysRepository);


        //when
        List<RoomEntity> freeRooms=a.getFreeRooms(LocalDate.of(2019,03,29),
                                                    LocalDate.of(2019,04,01),
                                                    2);

        //then
        Assertions.assertEquals(freeRooms.size(),roomRepository.rooms().size());

    }
    @Test
    public void testRoom4Avaliable(){
        //Given
        RoomRepository roomRepository=new RoomRepository();
        ReservationRepository reservationRepository=new ReservationRepository();
        GuestRepository guestRepository=new GuestRepository();
        SpecialDaysRepository specialDaysRepository=new SpecialDaysRepository();
        Apartman a=new Apartman(reservationRepository,roomRepository,guestRepository,specialDaysRepository);


        //when
        List<RoomEntity> freeRooms=a.getFreeRooms(LocalDate.of(2019,03,29),
                LocalDate.of(2019,04,01),
                4);

        //then
        Assertions.assertEquals(freeRooms.size(),1);

    }

    @Test
    public void testRoom3Avaliable(){
        //Given
        RoomRepository roomRepository=new RoomRepository();
        ReservationRepository reservationRepository=new ReservationRepository();
        GuestRepository guestRepository=new GuestRepository();
        SpecialDaysRepository specialDaysRepository=new SpecialDaysRepository();
        Apartman a=new Apartman(reservationRepository,roomRepository,guestRepository,specialDaysRepository);


        //when
        List<RoomEntity> freeRooms=a.getFreeRooms(LocalDate.of(2019,03,29),
                LocalDate.of(2019,04,01),
                3);

        //then
        Assertions.assertEquals(freeRooms.size(),3);

    }



}

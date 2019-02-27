<<<<<<< HEAD
package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Room;

import java.time.LocalDate;
=======
package hu.unipannon.mik.balatoniszel.ws;

import java.util.ArrayList;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
import java.util.Collections;
import java.util.List;

public class RoomRepository {

<<<<<<< HEAD
    private List<RoomEntity> rooms = List.of(new RoomEntity("201", 2, false),
                                       new RoomEntity("301", 3, true),
                                       new RoomEntity("302", 3, true),
                                       new RoomEntity("401", 4, true));

    public List<RoomEntity> rooms() {
        return Collections.unmodifiableList(rooms);
    }

    public RoomEntity getRoom(String roomId) {
        return rooms.stream()
                .filter(r -> r.getId().equalsIgnoreCase(roomId))
                .findFirst()
                .orElse(null);
    }
=======
    private List<Room> rooms = List.of(new Room("201", 2, false),
                                       new Room("301", 3, true),
                                       new Room("302", 3, true),
                                       new Room("401", 4, true));

    public List<Room> rooms() {
        return Collections.unmodifiableList(rooms);
    }

>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
}

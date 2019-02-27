package hu.unipannon.mik.balatoniszel.ws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomRepository {

    private List<Room> rooms = List.of(new Room("201", 2, false),
                                       new Room("301", 3, true),
                                       new Room("302", 3, true),
                                       new Room("401", 4, true));

    public List<Room> rooms() {
        return Collections.unmodifiableList(rooms);
    }

}

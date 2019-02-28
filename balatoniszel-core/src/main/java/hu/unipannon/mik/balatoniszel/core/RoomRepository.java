package hu.unipannon.mik.balatoniszel.core;

import java.util.Collections;
import java.util.List;

public class RoomRepository {

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

}

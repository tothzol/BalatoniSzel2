package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.Room;

public class RoomEntity {
    private final String id;
    private final int numberOfBeds;
    private final boolean extraBedAvailable;

    public RoomEntity(String id, int numberOfBeds, boolean extraBedAvailable) {
        this.id = id;
        this.numberOfBeds = numberOfBeds;
        this.extraBedAvailable = extraBedAvailable;
    }

    public String getId() {
        return id;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public boolean getExtraBedAvailable() {
        return extraBedAvailable;
    }

    public Room asRoom() {
        Room result = new Room();
        result.setId(id);
        result.setNumberOfBeds(numberOfBeds);
        result.setExtraBedAvailable(extraBedAvailable);
        return result;
    }
}

package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.client.Room;

import javax.persistence.*;

@Entity(name="room")
public class RoomEntity {
    @Id
    private final String id;
    @Column
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

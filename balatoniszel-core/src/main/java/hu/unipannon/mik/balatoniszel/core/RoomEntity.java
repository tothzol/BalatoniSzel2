package hu.unipannon.mik.balatoniszel.core;

<<<<<<< HEAD
import hu.unipannon.mik.balatoniszel.ws.Room;

public class RoomEntity {
    private final String id;
    private final int numberOfBeds;
    private final boolean extraBedAvailable;

    public RoomEntity(String id, int numberOfBeds, boolean extraBedAvailable) {
        this.id = id;
=======
public class RoomEntity {
    private final String name;
    private final int numberOfBeds;
    private final boolean extraBedAvailable;

    public RoomEntity(String name, int numberOfBeds, boolean extraBedAvailable) {
        this.name = name;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
        this.numberOfBeds = numberOfBeds;
        this.extraBedAvailable = extraBedAvailable;
    }

<<<<<<< HEAD
    public String getId() {
        return id;
=======
    public String getName() {
        return name;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public boolean getExtraBedAvailable() {
        return extraBedAvailable;
    }
<<<<<<< HEAD

    public Room asRoom() {
        Room result = new Room();
        result.setId(id);
        result.setNumberOfBeds(numberOfBeds);
        result.setExtraBedAvailable(extraBedAvailable);
        return result;
    }
=======
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
}

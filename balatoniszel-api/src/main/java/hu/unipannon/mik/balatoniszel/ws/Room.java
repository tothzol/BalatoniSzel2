<<<<<<< HEAD
package hu.unipannon.mik.balatoniszel.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "hu.unipannon.mik.balatoniszel.ws")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {
    private String id;
    private int numberOfBeds;
    private boolean extraBedAvailable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
=======
package hu.unipannon.mik.balatoniszel.core;

public class Room {
    private final String name;
    private final int numberOfBeds;
    private final boolean extraBedAvailable;

    public Room(String name, int numberOfBeds, boolean extraBedAvailable) {
        this.name = name;
        this.numberOfBeds = numberOfBeds;
        this.extraBedAvailable = extraBedAvailable;
    }

    public String getName() {
        return name;
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

<<<<<<< HEAD
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public boolean isExtraBedAvailable() {
        return extraBedAvailable;
    }

    public void setExtraBedAvailable(boolean extraBedAvailable) {
        this.extraBedAvailable = extraBedAvailable;
    }
=======
    public boolean getExtraBedAvailable() {
        return extraBedAvailable;
    }
>>>>>>> 29a372930036ad7f49d018af4466c77cface1e33
}

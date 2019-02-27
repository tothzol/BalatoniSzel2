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
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public boolean isExtraBedAvailable() {
        return extraBedAvailable;
    }

    public void setExtraBedAvailable(boolean extraBedAvailable) {
        this.extraBedAvailable = extraBedAvailable;
    }
}

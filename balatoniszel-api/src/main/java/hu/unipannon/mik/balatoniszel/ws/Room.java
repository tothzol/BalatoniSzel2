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
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public boolean getExtraBedAvailable() {
        return extraBedAvailable;
    }
}

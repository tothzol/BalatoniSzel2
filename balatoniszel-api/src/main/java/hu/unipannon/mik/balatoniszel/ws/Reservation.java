package hu.unipannon.mik.balatoniszel.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;


@XmlType(namespace = "hu.unipannon.mik.balatoniszel.ws")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation {
    private String id;
    private String startDate;
    private String endDate;
    private int numberOfBeds;
    private Guest guest;
    private Room room;
    private int deposit;
    private boolean hasEnoughDeposit;
    private int price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public boolean isHasEnoughDeposit() {
        return hasEnoughDeposit;
    }

    public void setHasEnoughDeposit(boolean hasEnoughDeposit) {
        this.hasEnoughDeposit = hasEnoughDeposit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

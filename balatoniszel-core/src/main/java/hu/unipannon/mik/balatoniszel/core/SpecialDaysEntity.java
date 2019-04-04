package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.SpecialDays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name = "specialday")
public class SpecialDaysEntity {
    @Id
    private final String id;
    @Column
    private final LocalDate startDate;
    @Column
    private final LocalDate endDate;

    private SpecialDaysEntity() {
        this.id = null;
        this.startDate = null;
        this.endDate = null;
    }

    public SpecialDaysEntity(String id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean contains(LocalDate day) {
        if(day.equals(startDate) || day.equals(endDate)) {
            return true;
        } else {
            return day.isAfter(startDate) && day.isBefore(endDate);
        }
    }

    public String getId() {
        return id;
    }

    public SpecialDays asSpecialDays() {
        SpecialDays result = new SpecialDays();
        result.setId(id);
        result.setStartDate(startDate.format(DateTimeFormatter.ISO_DATE));
        result.setEndDate(endDate.format(DateTimeFormatter.ISO_DATE));
        return result;
    }
}

package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.ws.SpecialDays;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SpecialDaysRepository {

    private final Map<String, SpecialDaysEntity> specialDays;

    public SpecialDaysRepository() {
        this.specialDays = new ConcurrentHashMap<>();
    }


    public boolean isSpecialDay(LocalDate day) {
        return specialDays
                .values()
                .stream()
                .anyMatch(s -> s.contains(day));
    }

    public void markDayAsSpecialDay(LocalDate startDate, LocalDate endDate) {
        SpecialDaysEntity newSpecialDays = new SpecialDaysEntity(UUID.randomUUID().toString(), startDate, endDate);
        specialDays.put(newSpecialDays.getId(), newSpecialDays);
    }

    public Collection<SpecialDaysEntity> getSpecialDays() {
        return Collections.unmodifiableCollection(specialDays.values());
    }

    public void deleteSpecialDays(String id) {
        specialDays.remove(id);
    }
}

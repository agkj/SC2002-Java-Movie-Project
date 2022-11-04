package Entities;

import java.time.LocalDate;

public class Holiday {
    private String holidayName;
    private LocalDate holidayDate;

    public Holiday() {}

    public Holiday(String name, LocalDate date) {
        this.holidayName = name;
        this.holidayDate = date;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }
}

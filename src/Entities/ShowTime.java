package Entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ShowTime implements Serializable {
    private LocalDateTime showDateTime;

    private Cinema cinema;
    private ShowTimeStatus showTimeStatus;

    public ShowTime() { }

    public ShowTime(LocalDateTime date, Cinema cinema, ShowTimeStatus status) {
        this.showDateTime = date;
        this.cinema = cinema;
        this.showTimeStatus = ShowTimeStatus.Available;
    }

    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public ShowTimeStatus getShowTimeStatus() {
        return showTimeStatus;
    }

    public void setShowTimeStatus(ShowTimeStatus showTimeStatus) {
        this.showTimeStatus = showTimeStatus;
    }
}

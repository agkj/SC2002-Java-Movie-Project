package Entities;

import java.io.Serializable;
import java.time.LocalDate;

public class ShowTime implements Serializable {
    private LocalDate showDateTime;
    private String cinemaLocation;
    private CinemaClass cinemaType;
    private ShowTimeStatus showTimeStatus;

    public ShowTime() { }

    public ShowTime(LocalDate date, String location, CinemaClass type, ShowTimeStatus status) {
        this.showDateTime = date;
        this.cinemaLocation = location;
        this.cinemaType = type;
        this.showTimeStatus = status;
    }

    public LocalDate getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDate showDateTime) {
        this.showDateTime = showDateTime;
    }

    public String getCinemaLocation() {
        return cinemaLocation;
    }

    public void setCinemaLocation(String cinemaLocation) {
        this.cinemaLocation = cinemaLocation;
    }

    public CinemaClass getCinemaType() {
        return cinemaType;
    }

    public void setCinemaType(CinemaClass cinemaType) {
        this.cinemaType = cinemaType;
    }

    public ShowTimeStatus getShowTimeStatus() {
        return showTimeStatus;
    }

    public void setShowTimeStatus(ShowTimeStatus showTimeStatus) {
        this.showTimeStatus = showTimeStatus;
    }
}

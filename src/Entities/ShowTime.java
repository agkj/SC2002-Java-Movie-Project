package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ShowTime implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    private String showtimeID;
    private String cinemaID;
    private String cineplexID;
    private LocalDateTime showDateTime;
    private ShowTimeStatus showTimeStatus;
    private Seat[][] showTimeLayout;

    private int numOfAvailSeats;

    public ShowTime() {
        this.showTimeStatus = ShowTimeStatus.Available;
    }

    public String getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(String cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(String showtimeID) {
        this.showtimeID = showtimeID;
    }

    public String getCineplexID() {
        return cineplexID;
    }

    public void setCineplexID(String cineplexID) {
        this.cineplexID = cineplexID;
    }

    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    public ShowTimeStatus getShowTimeStatus() {
        return showTimeStatus;
    }

    public void setShowTimeStatus(ShowTimeStatus showTimeStatus) {
        this.showTimeStatus = showTimeStatus;
    }

    public Seat[][] getShowTimeLayout() {
        return showTimeLayout;
    }

    public void setShowTimeLayout(Seat[][] showTimeLayout) {
        this.showTimeLayout = showTimeLayout;
    }

    public int getNumOfAvailSeats() {
        return numOfAvailSeats;
    }

    public void setNumOfAvailSeats(int numOfAvailSeats) {
        this.numOfAvailSeats = numOfAvailSeats;
    }
}

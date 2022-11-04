package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ShowTime implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    private LocalDateTime showDateTime;
    private Cineplex cineplex;
    private ShowTimeStatus showTimeStatus;


    public ShowTime() {
        //is this needed here?
        this.showTimeStatus = ShowTimeStatus.Available;
    }

    public ShowTime(LocalDateTime date, Cineplex cineplex, ShowTimeStatus status) {
        this.showDateTime = date;
        this.cineplex = cineplex;
        this.showTimeStatus = ShowTimeStatus.Available;
    }

    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    public ShowTimeStatus getShowTimeStatus() {
        return showTimeStatus;
    }

    public void setShowTimeStatus(ShowTimeStatus showTimeStatus) {
        this.showTimeStatus = showTimeStatus;
    }
}

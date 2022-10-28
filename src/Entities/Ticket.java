package Entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Ticket implements Serializable{
    //Attributes:
    private double price;
    private Enum ticketType; // this is our age
    private MovieGenre movietype;
    private CinemaClass cinemaclass;
    private int holiday;
    //constructor

    public Ticket(double price, Enum ticketType, MovieGenre movietype, CinemaClass cinemaclass, int holiday) {
        this.price = price;
        this.ticketType = ticketType;
        this.movietype = movietype;
        this.cinemaclass = cinemaclass;
        this.holiday = holiday;
    }
    public double getPrice() {
        return price;
    }

    public Enum getTicketType() {
        return ticketType;
    }

    public MovieGenre getMovietype() {
        return movietype;
    }

    public CinemaClass getCinemaclass() {
        return cinemaclass;
    }

    public int getHoliday() {
        return holiday;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTicketType(Enum ticketType) {
        this.ticketType = ticketType;
    }

    public void setMovietype(MovieGenre movietype) {
        this.movietype = movietype;
    }

    public void setCinemaclass(CinemaClass cinemaclass) {
        this.cinemaclass = cinemaclass;
    }

    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }
}

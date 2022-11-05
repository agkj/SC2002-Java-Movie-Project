package Entities;

import java.io.Serial;
import java.io.Serializable;

public class Ticket implements Serializable{
    @Serial
    private static final long serialVersionUID = 2002;

    //Attributes:
    private double ticketPrice;
    private TicketType ticketType; // this is our age
    private MovieGenre moviegenre;
    private CinemaClass cinemaclass;
    private int holiday;
    //constructor
    public Ticket(){};

    public Ticket(double price, TicketType ticketType, MovieGenre moviegenre, CinemaClass cinemaclass, int holiday) {
        this.ticketPrice = price;
        this.ticketType = ticketType;
        this.moviegenre = moviegenre;
        this.cinemaclass = cinemaclass;
        this.holiday = holiday;
    }
    
    public double getPrice() {
        return ticketPrice;
    }

    //Getters
    public TicketType getTicketType() {
        return ticketType;
    }

    public MovieGenre getMoviegenre() {
        return moviegenre;
    }

    public CinemaClass getCinemaclass() {
        return cinemaclass;
    }

    public int getHoliday() {
        return holiday;
    }

    //Setters

    public void setPrice(double price) {
        this.ticketPrice = price;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void setMoviegenre(MovieGenre moviegenre) {
        this.moviegenre = moviegenre;
    }

    public void setCinemaclass(CinemaClass cinemaclass) {
        this.cinemaclass = cinemaclass;
    }

    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }
}

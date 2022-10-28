package Entities;

import java.io.Serializable;

public class Ticket implements Serializable{
    //Attributes:
    private double price;
    private Enum ticketType; // this is our age
    private MovieGenre moviegenre;
    private CinemaClass cinemaclass;
    private int holiday;
    //constructor

    public Ticket(double price, Enum ticketType, MovieGenre moviegenre, CinemaClass cinemaclass, int holiday) {
        this.price = price;
        this.ticketType = ticketType;
        this.moviegenre = moviegenre;
        this.cinemaclass = cinemaclass;
        this.holiday = holiday;
    }
    public double getPrice() {
        return price;
    }

    public Enum getTicketType() {
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTicketType(Enum ticketType) {
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

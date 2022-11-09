package Entities;

import java.io.Serial;
import java.io.Serializable;

public class Ticket implements Serializable{
    @Serial
    private static final long serialVersionUID = 2002;

    //Attributes:
    private double ticketPrice;
    private TicketType ticketType; // this is our age
    private MovieType movieType;
    private CinemaClass cinemaclass;
    private DayType dayType;
    
    //constructor
    public Ticket(){};
    
    

    public Ticket(double ticketPrice, TicketType ticketType, MovieType movieType, CinemaClass cinemaclass,
			DayType dayType) {
		super();
		this.ticketPrice = ticketPrice;
		this.ticketType = ticketType;
		this.movieType = movieType;
		this.cinemaclass = cinemaclass;
		this.dayType = dayType;
	}



	public double getTicketPrice() {
		return ticketPrice;
	}



	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}



	public TicketType getTicketType() {
		return ticketType;
	}



	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}



	public MovieType getMovieType() {
		return movieType;
	}



	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}



	public CinemaClass getCinemaclass() {
		return cinemaclass;
	}



	public void setCinemaclass(CinemaClass cinemaclass) {
		this.cinemaclass = cinemaclass;
	}



	public DayType getDayType() {
		return dayType;
	}



	public void setDayType(DayType dayType) {
		this.dayType = dayType;
	}
    
    
}

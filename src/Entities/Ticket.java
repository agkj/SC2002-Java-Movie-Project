package Entities;

import java.io.Serial;
import java.io.Serializable;

public class Ticket implements Serializable{
    @Serial
    private static final long serialVersionUID = 2002;

    //Attributes:
    private double ticketPrice;
    private String seatNum;
    private TicketType ticketType; // this is our age
    private MovieGenre moviegenre;
    private CinemaClass cinemaclass;
    private int holiday;
    //constructor
    public Ticket(){};

<<<<<<< Updated upstream
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
=======
    public Ticket(double ticketPrice, TicketType ticketType, MovieType movieType, CinemaClass cinemaclass,
			DayType dayType, String seatNum) {
		super();
		this.ticketPrice = ticketPrice;
		this.ticketType = ticketType;
		this.movieType = movieType;
		this.cinemaclass = cinemaclass;
		this.dayType = dayType;
		this.seatNum = seatNum;
	}

    // Getter and Setters
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

	public double extractPrice(String priceString) {
		String price = priceString.split(",")[1];
		return Double.parseDouble(price);
	}
>>>>>>> Stashed changes

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
    
    public String getSeat() {
    	return seatNum;
    }
    
    public void setSeat(String seatNum) {
    	this.seatNum = seatNum;
    }
}

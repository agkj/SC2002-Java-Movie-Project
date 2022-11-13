package Entities;

import Util.FileReader;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a movie ticket.
 * A ticket is made under one booking.
 * Many tickets can be made under one booking.
 */
public class Ticket implements Serializable {
	@Serial
	private static final long serialVersionUID = 2002;

	/**
	 * Price of the ticket.
	 */
    private double ticketPrice;
	/**
	 * Type of ticket bought (i.e., Standard, Senior).
	 */
    private TicketType ticketType;
	/**
	 * Seat number booked under the ticket.
	 */
    private String seatNum;
	/**
	 * Movie Type (i.e., 2D, 3D) that the ticket is purchased for.
	 */
    private MovieType movieType;
	/**
	 * Cinema Class (i.e., Regular, Platinum) that the ticket is purchased at.
	 */
    private CinemaClass cinemaclass;
	/**
	 * Day Type (i.e., WEEKEND, HOLIDAY) of the showtime that the ticket is purchased for.
	 */
    private DayType dayType;
    
    //constructor
    public Ticket(){};

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

	/**
	 * Calculate the price of the ticket based on movie type, cinema class, ticket type (age of movie-goer) and day of week.
	 * @return
	 */
	public double calculateTicketPrice() {
		double totalPrice = 0;

		double standardPrice, bookingFee;
		double ticketTypeMod, movieTypeMod, cinemaClassMod, dayTypeMod;

		File path = new File(System.getProperty("user.dir") + "\\data\\ticket_pricing\\");

		// Get Standard/Base Ticket Price
		String standardPriceString = FileReader.readLine(path + "\\standard_ticket.txt", "Standard");
		if (standardPriceString != null) {
			standardPrice = extractPrice(standardPriceString);
			totalPrice += standardPrice;
		}

		String bookingFeeString = FileReader.readLine(path + "\\standard_ticket.txt", "Booking");
		if (bookingFeeString != null) {
			bookingFee = extractPrice(bookingFeeString);
			totalPrice += bookingFee;
		}

		//// Get Ticket Type Modifier
		String ticketTypeString = FileReader.readLine(path + "\\ticket_type_price.txt", this.ticketType.toString());
		if (ticketTypeString != null) {
			ticketTypeMod = extractPrice(ticketTypeString);
			totalPrice += ticketTypeMod;
		}

		// Get Cinema Class Modifier
		String cinemaClassString = FileReader.readLine(path + "\\cinema_class_price.txt", this.cinemaclass.toString());
		if (cinemaClassString != null) {
			cinemaClassMod = extractPrice(cinemaClassString);
			totalPrice += cinemaClassMod;
		}

		// Get Day Type Modifier
		String dayTypeString = FileReader.readLine(path + "\\day_type_price.txt", this.dayType.toString());
		if (dayTypeString != null) {
			dayTypeMod = extractPrice(dayTypeString);
			totalPrice += dayTypeMod;
		}

		// Get Movie Type Modifier (do last because it's a multiplier)
		String movieTypeString = FileReader.readLine(path + "\\movie_type_price.txt", this.movieType.toString());
		if (movieTypeString != null) {
			movieTypeMod = extractPrice(movieTypeString);
			totalPrice *= movieTypeMod;
		}

        return totalPrice;
    }
    
    public String getSeat() {
    	return seatNum;
    }
    
    public void setSeat(String seatNum) {
    	this.seatNum = seatNum;
    }
    
    @Override
    public String toString() {
        return  "\nDay Type: " + dayType+ 
        		"\nType: " + ticketType +
        		"\nCinema: "+ cinemaclass +
        		"\nMovie Type: " + movieType +
        		"\nSeat: " + seatNum + 
        		"\nPrice: " + ticketPrice +"\n";
        		
    }
	
	public void getTicketInfo() {
		System.out.println();
		System.out.println("Day type: " + dayType + "\t\t\t\t ");
		System.out.println("Ticket type:" + ticketType + "\t\t\t\t ");
		System.out.println("Cinema class: " + cinemaclass + "\t\t\t\t ");
		System.out.println("Movie type: " + movieType + "\t\t\t\t ");
		System.out.println("Seat number: " + seatNum + "\t\t\t\t ");
		System.out.println("Ticket price: " + ticketPrice + "\t\t\t\t");

//		return "\nDay Type: " + dayType + "\nType: " + ticketType + "\nCinema: " + cinemaclass + "\nMovie Type: "
//				+ movieType + "\nSeat: " + seatNum + "\nPrice: " + ticketPrice + "\n";

	}

}

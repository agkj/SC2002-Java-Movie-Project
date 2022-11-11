package Entities;

import Util.FileReader;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;

public class Ticket implements Serializable{
    @Serial
    private static final long serialVersionUID = 2002;

    //Attributes:
    private double ticketPrice;
    private TicketType ticketType;
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

    public double calculateTicketPrice() {
		double totalPrice = 0;

		double standardPrice, bookingFee;
		double ticketTypeMod, movieTypeMod, cinemaClassMod, dayTypeMod;

		File path = new File(System.getProperty("user.dir") + "\\data\\ticket_pricing\\");

		// Get Standard/Base Ticket Price
        String standardPriceString = FileReader.readLine(path + "\\standard_ticket.txt", "Standard");
		if(standardPriceString != null) {
			standardPrice = extractPrice(standardPriceString);
			totalPrice += standardPrice;
		}

		String bookingFeeString = FileReader.readLine(path + "\\standard_ticket.txt", "Booking");
		if(bookingFeeString != null) {
			bookingFee = extractPrice(bookingFeeString);
			totalPrice += bookingFee;
		}

		//// Get Ticket Type Modifier
		String ticketTypeString = FileReader.readLine(path + "\\ticket_type_price.txt", this.ticketType.toString());
		if(ticketTypeString != null) {
			ticketTypeMod = extractPrice(ticketTypeString);
			totalPrice += ticketTypeMod;
		}

		// Get Cinema Class Modifier
		String cinemaClassString = FileReader.readLine(path + "\\cinema_class_price.txt", this.cinemaclass.toString());
		if(cinemaClassString != null) {
			cinemaClassMod = extractPrice(cinemaClassString);
			totalPrice += cinemaClassMod;
		}

		// Get Day Type Modifier
		String dayTypeString = FileReader.readLine(path + "\\day_type_price.txt", this.dayType.toString());
		if(dayTypeString != null) {
			dayTypeMod = extractPrice(dayTypeString);
			totalPrice += dayTypeMod;
		}

		// Get Movie Type Modifier (do last because it's a multiplier)
		String movieTypeString = FileReader.readLine(path + "\\movie_type_price.txt", this.movieType.toString());
		if(movieTypeString != null) {
			movieTypeMod = extractPrice(movieTypeString);
			totalPrice *= movieTypeMod;
		}

        return totalPrice;
    }
}

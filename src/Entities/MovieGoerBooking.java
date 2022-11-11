package Entities;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


//import MovieGoer.MovieGoerBooking;
import Util.Serializer;

public class MovieGoerBooking implements Serializable {

	@Serial
	private static final long serialVersionUID = 2002;

	protected String name;
	protected String mobileNumber;
	protected String email;
	protected String selectedMovie;
	protected String cinemaCode;
	protected LocalDateTime	showDateTime;
	protected String ticketID;
	protected ArrayList<Ticket> listOfTickets = new ArrayList<Ticket>();
	
	/*
	protected String seatNum;
	protected String ticketType;
	protected Double price;
	protected String ticketID;
	*/

	
	protected static int totalTickets = 0;

	String root = System.getProperty("user.dir");

	protected File path;
	protected File[] movieBookings;

	// find a way to store all this into a separate dat.file
	public void load() {
		// Try to read all movie .dat files in movie directory
		path = new File(System.getProperty("user.dir") + "\\data\\bookings");

		// Store all movie .dat files
		movieBookings = path.listFiles();
	}

	// cinema code: yy/mm/dd, hr hr min min
	
	public MovieGoerBooking() {}
	
	public MovieGoerBooking(String name, String mobileNumber, String email, String selectedMovie, String cinemaCode,
			LocalDateTime showDateTime) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.selectedMovie = selectedMovie;
		this.cinemaCode = cinemaCode;
		this.showDateTime = showDateTime;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSelectedMovie() {
		return selectedMovie;
	}

	public void setSelectedMovie(String selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

	public String getCinemaCode() {
		return cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	public LocalDateTime getShowDateTime() {
		return showDateTime;
	}

	public void setShowDateTime(LocalDateTime showDateTime) {
		this.showDateTime = showDateTime;
	}	
	
	public void addTicket(Ticket ticket) {
		this.listOfTickets.add(ticket);
	}

	public static int getTotalTickets() {
		return totalTickets;
	}

	public static void setTotalTickets(int totalTickets) {
		MovieGoerBooking.totalTickets = totalTickets;
	}
	
	public String getTicketID() {
		return ticketID;
	}
	
	
	public void setTicketID() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmm");
		Date date = new Date();

		this.ticketID= this.cinemaCode + formatter.format(date);


	} 

	    public void getInfo() {
	    	System.out.println("| Transaction ID:"+ ticketID+ "\t\t |");
	    	System.out.println("|----------------Booking Details-----------------|");
	        System.out.println("| Name: " + name + "\t\t\t\t\t |");
	        System.out.println("| Mobile number: " + mobileNumber+ "\t\t\t |");
	        System.out.println("| Email:" + email+ "\t\t\t\t |");
	        System.out.println("| Movie booked:"+selectedMovie+ "\t\t\t\t |");
	        System.out.println("| Movie time: "+ showDateTime+"\t\t\t |");
	        System.out.println("| Cinema code:"+ cinemaCode+ "\t\t\t\t |");
	        
	        System.out.println("| Movie time: " + showDateTime+ "\t\t\t |");
	        
	        for(int i = 0; i < listOfTickets.size(); i++) {
	        	System.out.print("|----------------Ticket " + (i+1) + " Details----------------|");
	        	listOfTickets.get(i).getTicketInfo();
	        	
	        }
	        

	        System.out.println("|------------------------------------------------|");

	 }


}
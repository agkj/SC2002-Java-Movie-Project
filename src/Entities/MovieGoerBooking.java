package Entities;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieGoerBooking implements Serializable {

	@Serial
	private static final long serialVersionUID = 2002;

	protected String name;
	protected String mobileNumber;
	protected String email;
	protected String cinemaCode;
	protected String ticketID;
	
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

	public MovieGoerBooking() {
		this.name = null;
		this.mobileNumber = null;
		this.email = null;
		this.cinemaCode = null;
	}

	public MovieGoerBooking(String name, String mobileNumber, String email, String cinemaCode) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.cinemaCode = cinemaCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public String getCinemaCode() {
		return this.cinemaCode;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	public String getTicketID() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmm");
		Date date = new Date();
		totalTickets++;
		return cinemaCode + formatter.format(date);
		// System.out.println("Your ticket id is "+ cinemaCode +
		// formatter.format(date));

	}

}
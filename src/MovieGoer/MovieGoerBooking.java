package MovieGoer;

import java.io.Serializable;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class MovieGoerBooking implements Serializable {
	
	protected String name;
	protected int mobileNumber;
	protected String email;
	protected String cinemaCode;
	protected String ticketID;
	
	
	//cinema code: yy/mm/dd, hr hr min min

	public MovieGoerBooking() {
		this.name = null;
		this.mobileNumber = 0;
		this.email = null;
		this.cinemaCode = null;
	}
	
	
	public void MovieGoerBooking(String name, int mobileNumber, String email, String cinemaCode) {
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
	
	public int getMobileNumber() {
		return this.mobileNumber;
	}
	public String getEmail() {
		return this.email;
	}
	public String getCinemaCode() {
		return this.cinemaCode;
	}
	
	
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	
	public String setTicketID() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmm");  
	    Date date = new Date();  
	     
	    return this.cinemaCode + formatter.format(date) ;
	}
}
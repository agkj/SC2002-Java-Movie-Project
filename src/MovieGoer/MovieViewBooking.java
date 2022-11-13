package MovieGoer;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

import Util.AppHelper;
import Admin.MovieListingApp;

/**
 * [Movie-Goer Module] Movie Booking App to create a new booking.
 * Allow movie-gowers to select seat(s) for a selected showtime and get ticket(s).
 */
public class MovieViewBooking extends MovieListingApp implements Serializable {

	Scanner sc = new Scanner(System.in);
	protected File pathCinema;
	protected File[] cinemaFiles;

	public MovieViewBooking(AppHelper prevApp) {
		super(prevApp);

		// Try to read all cinema .dat files in movie directory
		pathCinema = new File(System.getProperty("user.dir") + "\\data\\cinema");

		// Store all movie .dat files
		cinemaFiles = pathCinema.listFiles();
	}


	
	public void movieViewBookings() { // TODO should viewbook be saved under the mobile number and email?
		System.out.println("==========================================================");
		System.out.println("\t\tSEARCH BOOKING HISTORY");
		System.out.println("==========================================================");
		MovieGoerCheckBooking checkBooking = new MovieGoerCheckBooking();
		checkBooking.getBookingDetails();

		goBack().runInterface();
	}

}

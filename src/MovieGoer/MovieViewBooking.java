package MovieGoer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Util.AppHelper;
import Admin.MovieListingApp;
import Entities.Cinema;
import Entities.Cineplex;
import Entities.DayType;
import Entities.Movie;
import Entities.MovieGoerBooking;
import Entities.Review;
import Entities.ShowTime;
import Entities.ShowTimeStatus;
import Entities.ShowingStatus;
import Entities.Ticket;
import Entities.TicketType;
import Util.Serializer;

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
		System.out.println("---------- SEARCH BOOKING HISTORY ----------\n");
		MovieGoerCheckBooking checkBooking = new MovieGoerCheckBooking();
		checkBooking.getBookingDetails();

		goBack().runInterface();
	}

}

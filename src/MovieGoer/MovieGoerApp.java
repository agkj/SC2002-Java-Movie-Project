package MovieGoer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
import Entities.Ticket;
import Entities.TicketType;
import Util.Serializer;

public class MovieGoerApp extends MovieListingApp implements Serializable {

	Scanner sc = new Scanner(System.in);
	protected File pathCinema;
	protected File[] cinemaFiles;

	public MovieGoerApp(AppHelper prevApp) {
		super(prevApp);
		
		 // Try to read all movie .dat files in movie directory
        pathCinema = new File(System.getProperty("user.dir") + "\\data\\cinema");

        // Store all movie .dat files
        cinemaFiles = pathCinema.listFiles();
	}

	public void movieView() {
		System.out.println("-------------- VIEW MOVIE LISTING --------------\n");

		try {
			// Read all available Movies
			if (movieFiles != null) {
				for (int i = 0; i < movieFiles.length; i++) {
					Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
					System.out.println((i + 1) + ") " + curr.getTitle());
					System.out.println("  Showing Status: " + curr.getShowingStatus());
					System.out.println("  Synopsis: " + curr.getSynopsis());
					System.out.println("  Director: " + curr.getDirector());
					System.out.println("  Cast: " + curr.getCast());
					System.out.println("  Overall Ratings: " + curr.getOverallRating());
					System.out.println("  Past and Present Reviews: " + curr.getReviews());
					System.out.println("-------------------------------------------------------------------");
				}
			}

			

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		goBack().runInterface();
	}

	public void movieRate() {
		try {
			// Read all available Movies
			for (int i = 0; i < movieFiles.length; i++) {
				Movie curr = (Movie) Serializer.deSerialize(movieFiles[i].getAbsolutePath());
				System.out.println((i + 1) + ") " + curr.getTitle());
			}

			System.out.print("Enter Movie Index to Update: ");
			int index = sc.nextInt() - 1;
			
			File selected = movieFiles[index];
			Movie movieToUpdate = (Movie) Serializer.deSerialize(movieFiles[index].getAbsolutePath());
			ArrayList<Review> review = movieToUpdate.getReviews();
			double overallRating = movieToUpdate.getOverallRating();
			overallRating = overallRating * review.size();
			System.out.print("Enter Ratings (1 to 5): ");
			double userRating = sc.nextInt();
			String userReviewId = String.valueOf(review.size() + 1); // get latest ID

			while (userRating < 1 || userRating > 5) {
				userRating = sc.nextInt();
				System.out.println("Please Enter Ratings (1 to 5): ");
			}
			System.out.println("Enter Review: ");

			String buffer = sc.nextLine(); // required for previous sc's "\n"
			String userTextReview = sc.nextLine();

			review.add(new Review(userReviewId, userRating, userTextReview));
			movieToUpdate.setReviews(review);
			overallRating = (overallRating + userRating) / review.size();
			movieToUpdate.setOverallRating(overallRating);

			try {
				Serializer.serialize(path + "\\" + selected.getName(), movieToUpdate);

				// Reload Movies
				this.load();

				System.out.println("\n------- SUCCESS: UPDATED REVIEW -------\n");
				System.out.println(movieToUpdate);
			} catch (IOException e) {
				System.out.println("\n------- ERROR: PLEASE TRY AGAIN -------\n");
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("\n------- ERROR: MOVIE NOT IN RANGE -------\n");
			e.printStackTrace();
		}
		goBack().runInterface();

	}

	public void movieLoad() {
		// Try to read all movie .dat files in movie directory
		path = new File(System.getProperty("user.dir") + "\\data\\movies");

		// Store all movie .dat files
		movieFiles = path.listFiles();
	}

	public void movieTopRatings() {

		try {
			// Read all available Movies
			Map<String, Double> mapOfMovies = new HashMap<>();
			if (movieFiles != null) {

				for (int i = 0; i < movieFiles.length; i++) {
					Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
					mapOfMovies.put(curr.getTitle(), curr.getOverallRating());

				}
			}
			List<Map.Entry<String, Double>> list = new ArrayList<>(mapOfMovies.entrySet());

			Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {

				public int compare(Map.Entry<String, Double> e2, Map.Entry<String, Double> e1) {

					return Double.compare(e1.getValue(), e2.getValue());
				}

			});

			System.out.println("*********Here are the top 5 movies!*********");
			for (Map.Entry<String, Double> e : list) {
				System.out.println("Movie: " + e.getKey() + "\t\t Rating: " + e.getValue() + " Stars");
			}
			System.out.println("*********************************************");

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		goBack().runInterface();
	}

	public void movieTopSales() {
		try {
			// Read all available Movies
			Map<String, Integer> mapOfMovies = new HashMap<>();
			if (movieFiles != null) {

				for (int i = 0; i < movieFiles.length; i++) {
					Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
					mapOfMovies.put(curr.getTitle(), curr.getTicketsSold());

				}
			}
			List<Map.Entry<String, Integer>> list = new ArrayList<>(mapOfMovies.entrySet());

			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {

					return Integer.compare(e1.getValue(), e2.getValue());
				}

			});

			System.out.println("*********Here are the top 5 movies by ticket sales!*********");
			for (Map.Entry<String, Integer> e : list) {
				System.out.println(e.getKey() + " Tickets sold: " + e.getValue());
			}
			System.out.println("*********************************************");

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		goBack().runInterface();
	}

	public void movieBooking() {
		System.out.println("-------Make a Booking--------");
		
		MovieGoerBooking newBooking = new MovieGoerBooking();
		
		// Get User Details
		System.out.println("Enter your name: ");
		String customerName = sc.next();
		newBooking.setName(customerName);
		
		System.out.println("Enter your phone number: ");
		String customerPhone = sc.next();
		newBooking.setMobileNumber(customerPhone);
		
		System.out.println("Enter your email: ");
		String customerEmail = sc.next();
		newBooking.setEmail(customerEmail);

		System.out.println("How many tickets are you buying: ");
		int customerTickets = sc.nextInt();

		// list available movies, user select movie

		try {
			//Print all the available Movies
			System.out.println("These are the MOVIES");
			System.out.println(("------------------"));
			// Read all available Movies
			for (int i = 0; i < movieFiles.length; i++) {
				Movie curr = (Movie) Serializer.deSerialize(movieFiles[i].getAbsolutePath());
				System.out.println((i + 1) + ") " + curr.getTitle() + " " + curr.getShowingStatus());//TODO should we show the EndOfStatus here?
			}
			System.out.println(("------------------"));
			System.out.println("Select a movie to book");
			int movieChoice = sc.nextInt() - 1;
			

			File selected = movieFiles[movieChoice];
			//Movie selectedMovie = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[movieChoice].getName());
			Movie selectedMovie = (Movie) Serializer.deSerialize(movieFiles[movieChoice].getAbsolutePath());
			System.out.println("You are booking :" + selectedMovie.getTitle());

			String movieID = selectedMovie.getMovieId();
			String movieName = selectedMovie.getTitle();

			newBooking.setSelectedMovie(movieID);

			// *******FUNCTIONS TO IMPLEMENT**************\\
			
			System.out.println("------- VIEW CINEMAS -------\n");//TODO this should be view cineplex?
			Cineplex selectedCineplex;
            try {
            	
            	File path;
                File[] files;
                path = new File(root + "\\data\\cineplex");
                files = path.listFiles();
            	
                // Read all available Cineplex created
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                      //  Cineplex curr = (Cineplex) Serializer.deSerialize(path + "\\" + files[i].getName());
                        Cineplex curr = (Cineplex) Serializer.deSerialize(files[i].getAbsolutePath());
                        System.out.println((i + 1) + ") " + curr.getVenue());
                    }

                    System.out.print("\nSelect Cineplex: ");

                    // Get selected Cineplex file and object
                    int selectedcine = sc.nextInt();
                  //  selectedCineplex = (Cineplex) Serializer.deSerialize(path + "\\" + files[selectedcine - 1].getName());
                    selectedCineplex = (Cineplex) Serializer.deSerialize(files[selectedcine -1].getAbsolutePath());
                    // Show list of showtimes from movieBooked
                    ArrayList<ShowTime> listOfShowtimes = selectedMovie.getShowTimes();
                    ArrayList<ShowTime> filteredShowtimes = new ArrayList<ShowTime>();
					System.out.println("These are the ShowTimes available:");
                    for(int i=0; i < listOfShowtimes.size(); i++) {
                    	ShowTime curr = listOfShowtimes.get(i);
                    	
                    	if(curr.getCineplexID().equals(selectedCineplex.getCineplexID()) && curr.getShowTimeStatus() != ShowTimeStatus.Sold_Out) {
                    		// Show showtime
                    		System.out.println(i+1 + ") " + curr.getShowDateTime() + curr.getCinemaID()); //TODO show cinema location
                    		filteredShowtimes.add(curr);
                    	}
                    }
                    
                    System.out.print("\nSelect Showtime: "); //TODO rmb to create all the test case for showtime before testing

                    // Get selected Cineplex file and object
                    int selectedShowTimeIndex = sc.nextInt();
                    ShowTime selectedShowtime = filteredShowtimes.get(selectedShowTimeIndex-1);
                    Cinema selectedCinema = (Cinema) Serializer.deSerialize(pathCinema + "\\" + selectedShowtime.getCinemaID() + ".dat");
                    selectedShowtime.showLayout();
                    newBooking.setCinemaCode(selectedShowtime.getCinemaID());
                    // Select Seat(s) based on number of tickets purchasing
                    while(customerTickets > 0) {
                    	System.out.println("\nSelect a seat number: ");
                        String seatNum = sc.next();
                        
                        selectedShowtime.bookSeat(seatNum);		// TODO maybe book seat only after payment complete
                        
                        System.out.println("\nSelect ticket type: ");
                        for(int i=0; i < TicketType.values().length; i++) {
                        	System.out.println((i+1) + ") " + TicketType.values()[i]);
                        }
                        
                        // Get selected Cineplex file and object
                        TicketType ticketType = TicketType.values()[sc.nextInt()-1];
                        
                        // Create Ticket Object
                        Ticket newTicket = new Ticket();
                        newTicket.setTicketType(ticketType);
                        newTicket.setMovieType(selectedMovie.getMovieType());
                        newTicket.setCinemaclass(selectedCinema.getCinemaClass());
                        newTicket.setDayType(DayType.MON_WED);	// TODO need to check for day type
                        
                        double price = newTicket.calculateTicketPrice();
                        newTicket.setTicketPrice(price);
                        System.out.println("Total Ticket Price: $" + price);
                        
                        newBooking.addTicket(newTicket);
                        //MovieGoerBooking userBooking = new MovieGoerBooking(customerName,customerPhone,customerEmail,movieName, movieID, null,seatNum);

                        
                    	customerTickets--; //TODO increment counter total ticket sold and total sales
                    }
                    
                }
                
                System.out.print("\nPlease select 1 to continue with payment: ");
    			int cont = sc.nextInt();
    			while(cont != 1) {
    				System.out.print("\nPlease select 1 to continue with payment: ");
    				cont = sc.nextInt();
    			}
    			// TODO Try catch maybe?
    			
    			System.out.println("Thank you for booking with us.");
    			System.out.println("Here are your booking details: ");

    			
    			String root = System.getProperty("user.dir");
    			 // movie id
    			String ticID = newBooking.getTicketID();
    			Serializer.serialize(root + "\\data\\bookings\\" + ticID + ".dat", newBooking);
    			
    			// Reload Movies
    			newBooking.load();
    			//newBooking.getInfo(); // TODO check what the ticket saves
    			
    			
            } catch (Exception e) {
                e.printStackTrace();
            }

		}

		catch (Exception e) {
			System.out.println("Select an option");
		}
		goBack().runInterface();

}


	public void movieViewBooking() { // TODO should viewbook be saved and email?
		System.out.println("---------- SEARCH BOOKING HISTORY ----------\n");
		MovieGoerCheckBooking checkBooking = new MovieGoerCheckBooking();
		checkBooking.getBookingDetails();
		
		goBack().runInterface();
	}

}

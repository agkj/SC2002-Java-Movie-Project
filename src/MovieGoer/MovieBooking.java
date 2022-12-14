package MovieGoer;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Entities.*;
import Util.AppHelper;
import Admin.MovieListingApp;
import Util.Serializer;

/**
 * [Movie-Goer Module] Movie Booking App to create a new booking.
 * Allow movie-gowers to select seat(s) for a selected showtime and get ticket(s).
 */
public class MovieBooking extends MovieListingApp implements Serializable {

	Scanner sc = new Scanner(System.in);
	protected File pathCinema;
	protected File[] cinemaFiles;

	public MovieBooking(AppHelper prevApp) {
		super(prevApp);

		// Try to read all cinema .dat files in movie directory
		pathCinema = new File(System.getProperty("user.dir") + "\\data\\cinema");

		// Store all movie .dat files
		cinemaFiles = pathCinema.listFiles();
	}

/*
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
			System.out.println("|------------------------------------------------|");
			System.out.println("|\t Here are the top 5 movies by rating \t |");
			for (Map.Entry<String, Double> e : list) {
				System.out.println("Movie: " + e.getKey() + "\t Rating: " + e.getValue() + " Stars \t |");
			}
			System.out.println("|------------------------------------------------|");

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
				public int compare(Map.Entry<String, Integer> e2, Map.Entry<String, Integer> e1) {

					return Integer.compare(e1.getValue(), e2.getValue());
				}

			});
			System.out.println("|------------------------------------------------|");
			System.out.println("|\t Here are the top 5 movies by sale! \t |");

			for (Map.Entry<String, Integer> e : list) {
				System.out.println(e.getKey() + "\t Tickets sold: " + e.getValue() + "\t\t |");
			}
			System.out.println("|------------------------------------------------|");

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		goBack().runInterface();
	}
*/
	public void movieBookings() {
		System.out.println("==========================================================");
		System.out.println(" \t\t Make a Booking");
		System.out.println("==========================================================");

		Booking newBooking = new Booking();

		int selectedcine = 0;
		Cineplex selectedCineplex = null;

		// Get User Details
		System.out.println("Enter your name: ");
		String customerName = sc.next();
		newBooking.setName(customerName);

		System.out.println("Enter your phone number: ");
		String customerPhone = sc.next();
		
		while(customerPhone.length() != 8) {
			System.out.println("Invalid phone number");
			System.out.println("Enter your phone number: ");
			customerPhone = sc.next();
		}
		

		
		newBooking.setMobileNumber(customerPhone);

		System.out.println("Enter your email: ");
		String customerEmail = sc.next();
		
		String substring = "@gmail.com";
		
		while(!customerEmail.contains(substring)) {
			System.out.println("Invalid email format");
			System.out.println("Enter your email: ");
			customerEmail = sc.next();
		}
		
		
		
		
		newBooking.setEmail(customerEmail);

		// list available movies, user select movie

		try {
			// Print all the available Movies
			System.out.println("==========================================================");
			System.out.println("\t\tList of Movies: \t\t ");
			System.out.println("==========================================================");
			// Read all available Movies if not End_of_Showing

			ArrayList<Movie> filteredMovie = new ArrayList<Movie>();
			for (int i = 0; i < movieFiles.length; i++) {
				Movie curr = (Movie) Serializer.deSerialize(movieFiles[i].getAbsolutePath());

				if (!curr.isEndOfShowing() & curr.getShowingStatus() != ShowingStatus.Coming_Soon & curr.getShowTimes().size() != 0) // TODO Coming soon
					// to filter
					filteredMovie.add(curr);
			}
			for (int i = 0; i < filteredMovie.size(); i++) {
				System.out.println( (i + 1) + ") " + filteredMovie.get(i).getTitle() + "["
						+ filteredMovie.get(i).getContentRating().toString() + "] - "
						+ (filteredMovie).get(i).getShowingStatus());
			}

			System.out.println("0) Go back to main menu \t\t\t ");

			System.out.println("==========================================================");

			System.out.println("Choose a movie to book");
			int movieChoice = sc.nextInt() - 1;
			if (movieChoice == -1) {
				AppHelper prevApp = goBack();
				prevApp.runInterface();

			}

			System.out.println("How many tickets are you buying: ");
			int customerTickets = sc.nextInt();
			int totalTicket = customerTickets;

			// File selected = movieFiles[movieChoice];
			// Movie selectedMovie = (Movie) Serializer.deSerialize(path + "\\" +
			// movieFiles[movieChoice].getName());
			// Movie selectedMovie = (Movie)
			// Serializer.deSerialize(movieFiles[movieChoice].getAbsolutePath());
			Movie selectedMovie = (Movie) filteredMovie.get(movieChoice);
			System.out.println("You are booking " + selectedMovie.getTitle());

			// String movieID = selectedMovie.getMovieId();
			String movieName = selectedMovie.getTitle();

			newBooking.setSelectedMovie(movieName);

			// *******FUNCTIONS TO IMPLEMENT**************\\
			System.out.println("==========================================================");
			System.out.println(("\t\tChoose a cineplex\t\t "));

			try {

				File path;
				File[] files;
				path = new File(root + "\\data\\cineplex");
				files = path.listFiles();
				double price = 0;
				double newPrice = 0;
				
				//ArrayList<ShowTime> listOfShowtimes = selectedMovie.getShowTimes();
				//ArrayList<ShowTime> filteredShowtimes = new ArrayList<ShowTime>();
				
				// Read all available Cineplex created
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						Cineplex curr = (Cineplex) Serializer.deSerialize(files[i].getAbsolutePath());
						//ShowTime temp = listOfShowtimes.get(i);
						//if(temp.getCineplexID().equals(curr.getCineplexID()) & temp.getShowTimeStatus() != ShowTimeStatus.Sold_Out)
						System.out.println((i + 1) + ") " + curr.getVenue());
					}
					System.out.println("==========================================================");
					System.out.print("\nSelect Cineplex: ");

					// Get selected Cineplex file and object
					selectedcine = sc.nextInt();
					// selectedCineplex = (Cineplex) Serializer.deSerialize(path + "\\" +
					// files[selectedcine - 1].getName());
					selectedCineplex = (Cineplex) Serializer.deSerialize(files[selectedcine - 1].getAbsolutePath());
					// Show list of showtimes from movieBooked
					ArrayList<ShowTime> listOfShowtimes = selectedMovie.getShowTimes();
					ArrayList<ShowTime> filteredShowtimes = new ArrayList<ShowTime>();
					for (int i = 0; i < listOfShowtimes.size(); i++) {
						ShowTime curr = listOfShowtimes.get(i);
						Cinema currCinema = (Cinema) Serializer
								.deSerialize(pathCinema + "\\" + curr.getCinemaID() + ".dat");

						if (curr.getCineplexID().equals(selectedCineplex.getCineplexID())
								&& curr.getShowTimeStatus() != ShowTimeStatus.Sold_Out) {
							// Show showtime
							LocalDateTime showtimeDate = curr.getShowDateTime();
							String day = showtimeDate.getDayOfWeek().toString();
							
							//TODO Check error
							try {
								if(curr.checkDayType().equals(DayType.HOLIDAY))
									day += " (PH)";
							}catch(Exception e) {
								
							}
							
							
							System.out.println(i+1 + ") " +  day + " - " + curr.getShowDateTime() + " (" + currCinema.getCinemaClass().toString() + " Class)");
							filteredShowtimes.add(curr);
						}
					}
					
					System.out.print("\nSelect Showtime: ");

					// Get selected Cineplex file and object
					int selectedShowTimeIndex = sc.nextInt();
					ShowTime selectedShowtime = filteredShowtimes.get(selectedShowTimeIndex - 1);
					Cinema selectedCinema = (Cinema) Serializer
							.deSerialize(pathCinema + "\\" + selectedShowtime.getCinemaID() + ".dat");

					
					newBooking.setCinemaCode(selectedShowtime.getCinemaID());
					
					// Select Seat(s) based on number of tickets purchasing
					while (customerTickets > 0) {
						selectedShowtime.showLayout();
						System.out.println("\nSelect a seat number: ");
						String seatNum = sc.next();

						while (selectedShowtime.bookSeat(seatNum) != true) {
							System.out.println("\nSeat has been taken!\nPlase select another seat number: ");
							seatNum = sc.next();
						}
						selectedShowtime.showLayout();
						
						System.out.println("\nSelect ticket type: ");
						for (int i = 0; i < TicketType.values().length; i++) {
							System.out.println((i + 1) + ") " + TicketType.values()[i]);
						}

						// Get selected Cineplex file and object
						TicketType ticketType = TicketType.values()[sc.nextInt() - 1];
						newBooking.setShowDateTime(selectedShowtime.getShowDateTime());
						// Create Ticket Object
						Ticket newTicket = new Ticket();

						newTicket.setTicketType(ticketType);
						newTicket.setSeat(seatNum);
						newTicket.setMovieType(selectedMovie.getMovieType());
						newTicket.setCinemaclass(selectedCinema.getCinemaClass());

						newTicket.setDayType(selectedShowtime.checkDayType());

						newPrice = newTicket.calculateTicketPrice();
						newTicket.setTicketPrice(newPrice);
						price = price + newPrice;
						System.out.println("Total Ticket Price: $" + price);

						newBooking.addTicket(newTicket);

						customerTickets--;
					}

				}

				 System.out.println("Press Enter key to confirm purchase: ");
			        try
			        {
			            System.in.read();
			            sc.nextLine();
			        }  
			        catch(Exception e)
			        {}  
				
				

//				if (cont == 0) {
//					MovieGoerMenu menu = 
//				}
//				while (cont != 1) {
//					System.out.print("\nPlease select 1 to continue with payment: ");
//					System.out.println("Select 0 to cancel payment");
//					cont = sc.nextInt();
//					
//					if (cont == 0) {
//						AppHelper prevApp = goBack();
//						prevApp.runInterface();
//					}
//					
//				}

				// TODO Try catch maybe?

				System.out.println("Thank you for booking with us.");
				System.out.println("Here are your booking details: ");

				selectedMovie.updateSales(totalTicket, price);
				Serializer.serialize(root + "\\data\\movies\\" + selectedMovie.getMovieId() + ".dat", selectedMovie);
				Serializer.serialize(files[selectedcine - 1].getAbsolutePath(), selectedCineplex);

				String root = System.getProperty("user.dir");

				newBooking.setTicketID();
				String ticID = newBooking.getTicketID();

				// movie id

				Serializer.serialize(root + "\\data\\bookings\\" + ticID + ".dat", newBooking);
				// Reload Movies
				newBooking.load();
				newBooking.getInfo(); // TODO check what the ticket saves

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		catch (Exception e) {
			System.out.println("Select an option");
		}

	}


}

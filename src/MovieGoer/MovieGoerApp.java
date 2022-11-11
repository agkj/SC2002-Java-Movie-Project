package MovieGoer;

import java.io.IOException;
import java.util.Scanner;

import MovieGoer.AppInterface;
import Util.Serializer;

public class MovieGoerApp {


	public MovieGoerApp() {
		// TODO Auto-generated constructor stub
	}


	public void runInterface() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("------------------");
		System.out.println("Welcome to customer module");
		//call list movie functions
		
		System.out.println("1) View movies"); // ->select movies -> booking ->check seat availability
		System.out.println("2) Rate movies");
		System.out.println("3) List top 5 movies");
		System.out.println("4) Make a booking"); //based on booking id/movie code
		System.out.println("5) Return to main menu");
		
		
		int choice = sc.nextInt();	
		MovieGoerReview movieReview = new MovieGoerReview(null);
		MovieGoerTop5 movieTop5 = new MovieGoerTop5(null);
		
		switch(choice) {
		
		case 1:
			//View movies
			
			movieReview.viewMovie();
			
			break;
		case 2:
			
			movieReview.setReview();
			
			//rate movies
			
			break;
		case 3:
			//list top 5 movies
			movieTop5.movieSort();
			break;
		case 4:
			//Make booking
			
			System.out.println("-------Make a Booking--------");
			
			//list available movies, user select movie 
			System.out.println("Choose a movie to book");
			
			
			//Choose cineplex -> choose cinema
			
			//Choose timing 
			
			//show available seats
			System.out.println("Choose your seat");
			
			
			System.out.println("Enter your name: ");
			String customerName = sc.next();
			
			System.out.println("Enter your phone number: ");
			String customerPhone = sc.next();
			
			System.out.println("Enter your email: ");
			String customerEmail = sc.next();
			
			
			MovieGoerBooking movieBooking = new MovieGoerBooking(customerName, customerPhone, customerEmail, "cd");
			String root = System.getProperty("user.dir");
			
			try {
	            Serializer.serialize(root + "\\data\\bookings\\" + "cd" + ".dat", movieBooking);

	            // Reload Movies
	            movieBooking.load();

	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			
			
			
			
		
			break;
			
		case 5:
			
			
			
		default: break;
		
		}
		
		
		
		
		
<<<<<<< Updated upstream
=======
		System.out.println("Enter your email: ");
		String customerEmail = sc.next();
		newBooking.setEmail(customerEmail);

		System.out.println("How many tickets are you buying: ");
		int customerTickets = sc.nextInt();
		int totalTickets = customerTickets;

		// list available movies, user select movie

		try {
			//Print all the available Movies
			System.out.println(("\n------------------"));
			System.out.println("List of Movies: ");
			// Read all available Movies if not End_of_Showing
			for (int i = 0; i < movieFiles.length; i++) {
				Movie curr = (Movie) Serializer.deSerialize(movieFiles[i].getAbsolutePath());

				if(!curr.isEndOfShowing())
					System.out.println((i + 1) + ") " + curr.getTitle() + " [" + curr.getContentRating().toString() + "] - " + curr.getShowingStatus());
			}

			System.out.println(("------------------"));
			System.out.println("Select a movie to book: ");

			// Read all available Movies
			for (int i = 0; i < movieFiles.length; i++) {
				Movie curr = (Movie) Serializer.deSerialize(movieFiles[i].getAbsolutePath());
				if(curr.getShowingStatus() != ShowingStatus.End_Of_Showing)
					System.out.println((i + 1) + ") " + curr.getTitle());
				
			}
			System.out.println("Choose a movie to book");
			int movieChoice = sc.nextInt() - 1;

			File selected = movieFiles[movieChoice];
			//Movie selectedMovie = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[movieChoice].getName());
			Movie selectedMovie = (Movie) Serializer.deSerialize(movieFiles[movieChoice].getAbsolutePath());
			System.out.println("You are booking " + selectedMovie.getTitle());

			String movieID = selectedMovie.getMovieId();
			String movieName = selectedMovie.getTitle();

			newBooking.setSelectedMovie(movieID);

			// *******FUNCTIONS TO IMPLEMENT**************\\
			System.out.println("------- VIEW CINEPLEX -------\n");
            try {
            	
            	File path;
                File[] files;
                path = new File(root + "\\data\\cineplex");
                files = path.listFiles();
                double price =0;
            	
                // Read all available Cineplex created
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                      //  Cineplex curr = (Cineplex) Serializer.deSerialize(path + "\\" + files[i].getName());
                        Cineplex curr = (Cineplex) Serializer.deSerialize(files[i].getAbsolutePath());
                        System.out.println((i + 1) + ") " + curr.getVenue());
                    }

                    System.out.print("\nSelect Cineplex: ");

                    // Get selected Cineplex file and object
                    selectedcine = sc.nextInt();
                  //  selectedCineplex = (Cineplex) Serializer.deSerialize(path + "\\" + files[selectedcine - 1].getName());
                    selectedCineplex = (Cineplex) Serializer.deSerialize(files[selectedcine -1].getAbsolutePath());
                    // Show list of showtimes from movieBooked
                    ArrayList<ShowTime> listOfShowtimes = selectedMovie.getShowTimes();
                    ArrayList<ShowTime> filteredShowtimes = new ArrayList<ShowTime>();
                    for(int i=0; i < listOfShowtimes.size(); i++) {
                    	ShowTime curr = listOfShowtimes.get(i);
						Cinema currCinema = (Cinema) Serializer.deSerialize(pathCinema + "\\" + curr.getCinemaID() + ".dat");
                    	
                    	if(curr.getCineplexID().equals(selectedCineplex.getCineplexID()) && curr.getShowTimeStatus() != ShowTimeStatus.Sold_Out) {
                    		// Show showtime
							LocalDateTime showtimeDate = curr.getShowDateTime();
							String day = showtimeDate.getDayOfWeek().toString();
							int hour = showtimeDate.getHour();
							System.out.println("Hour: " + hour);
                    		System.out.println(i+1 + ") " +  day + " - " + curr.getShowDateTime() + " (" + currCinema.getCinemaClass().toString() + " Class)");
                    		filteredShowtimes.add(curr);
                    	}
                    }
                    
                    System.out.print("\nSelect Showtime: ");

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
                        
                        while(selectedShowtime.bookSeat(seatNum) != true)	{
                        	System.out.println("\nSeat has been taken!\nPlase select another seat number: ");
                            seatNum = sc.next();
                        }
                        // TODO maybe book seat only after payment complete
                        
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
                        newTicket.setSeat(seatNum);

                        newTicket.setDayType(DayType.MON_WED);	// TODO need to check for day type
                        
                       // price = newTicket.calculateTicketPrice();
                        newTicket.setTicketPrice(price);
                        price = price + newTicket.calculateTicketPrice();
                        System.out.println("Total Ticket Price: $" + price);
                        
                        newBooking.addTicket(newTicket);
                        
                        
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
    			selectedMovie.updateSales(totalTickets, price);
    			Serializer.serialize(root + "\\data\\movies\\" + selectedMovie.getMovieId() + ".dat", selectedMovie);
    			Serializer.serialize(files[selectedcine -1].getAbsolutePath(), selectedCineplex);
    			
    			String root = System.getProperty("user.dir");
    			 // movie id
    			String ticID = newBooking.getTicketID();
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


	public void movieViewBooking() { // TODO should viewbook be saved under the mobile number and email?
		System.out.println("---------- SEARCH BOOKING HISTORY ----------\n");
		MovieGoerCheckBooking checkBooking = new MovieGoerCheckBooking();
		checkBooking.getBookingDetails();
>>>>>>> Stashed changes
		
		
		
		
	}
	
	
	
	
	
	
}

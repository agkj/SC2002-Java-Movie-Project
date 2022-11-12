package MovieGoer;

import java.util.Scanner;

import Util.AppHelper;
import Util.FileReader;
import Util.MovieHelper;

/**
 * [Movie-Goer Module] Movie-Goer Starter App containing list of Movie-Goer Functions.
 * Allow movie-gowers to view and rate movies, list top 5 movies, and create and view bookings.
 */
public class MovieGoerMenu extends AppHelper {

	String root = System.getProperty("user.dir");

	public MovieGoerMenu(AppHelper prevApp) {
		// TODO Auto-generated constructor stub
		super(prevApp);
	}

	public void runInterface() {
		Scanner sc = new Scanner(System.in);
		Boolean stay = true;
		while(stay) {
			try {
				System.out.println("|------------------------------------------------|");
				System.out.println("| \t Welcome to Customer Module \t\t |");
				System.out.println("|------------------------------------------------|");

				//call list movie functions

				System.out.println("| 1) View movies \t\t\t\t |"); // ->select movies -> booking ->check seat availability
				System.out.println("| 2) Rate movies \t\t\t\t |");

				StringBuffer optionView = FileReader.copyFile(root + "\\data\\admin_control\\control.txt");
				String[] optionStrings = optionView.toString().split("\n");

				//look at the admin control
				int set_control_ticket = 0;
				int set_control_rating = 0;

				for(int i=0; i < optionStrings.length; i++) {
					String[] currLine = optionStrings[i].split(",");
					String option = currLine[0];
					String optionBoolean = currLine[1];

					if(option.equals("Ticket Sales") && optionBoolean.equals("true")){
						set_control_ticket = 1;

					}else if(option.equals("Reviewers' Rating") && optionBoolean.equals("true")){
						set_control_rating = 1;
					}
				}
				//look at the admin control
				if(set_control_ticket == 0){
					System.out.print("| 3) List top 5 movies by ticket sales (DISABLED)|");
				}else {
					System.out.print("| 3) List top 5 movies by ticket sales \t\t |");
				}
				System.out.println();
				if(set_control_rating == 0){
					System.out.print("| 4) List top 5 movies by ratings (DISABLED) \t |");
				}else {
					System.out.print("| 4) List top 5 movies by ratings  \t\t |");
				}
				System.out.println();
				System.out.println("| 5) Make a booking \t\t\t\t |"); //based on booking id/movie code
				System.out.println("| 6) View booking history \t\t\t |");

				System.out.println("| \t\t\t\t\t\t |");
				System.out.println("| 0) Return to main menu \t\t\t |");
				System.out.println("|------------------------------------------------|");

				System.out.println("Select an option :");

				int choice = sc.nextInt();

				
				MovieHelper movieHelper = new MovieHelper();
				switch(choice) {
					case 0:
						stay = false;
						break;
					case 1:
						//View movies
						MovieView movieViewApp = new MovieView(this);
						movieViewApp.movieViews();
						break;
					case 2:
						// Rate Movies
						MovieRate movieRateApp = new MovieRate(this);
						movieRateApp.movieRates();
						break;
					case 3:
						//list top 5 movies
						if(set_control_ticket == 1) {
							movieHelper.getTopListings(1);
						}
						else{
							System.out.println("THIS FEATURE IS DISABLED\n");
						}
						runInterface();
						break;
					case 4:
						if(set_control_rating == 1){
							movieHelper.getTopListings(2);

						}
						else{
							System.out.println("THIS FEATURE IS DISABLED\n");
						}
						runInterface();
						break;
					case 5:
						//Make booking
						MovieBooking movieBookingApp = new MovieBooking(this);
						movieBookingApp.movieBookings();
						break;
					case 6:
						//View booking
						MovieViewBooking movieViewBookingApp = new MovieViewBooking(this);
						movieViewBookingApp.movieViewBookings();
						break;
					default: 
						break;

					}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}catch(Exception e) {
				System.out.println("Enter a correct choice");
				sc.next();
			}
			
		}
		
		
		
	}
	
}

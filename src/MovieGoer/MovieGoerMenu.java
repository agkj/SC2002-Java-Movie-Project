package MovieGoer;

import java.io.IOException;
import java.util.Scanner;

import Admin.MovieListingApp;
import MovieGoer.AppInterface;
import Util.AppHelper;
import Util.Serializer;

public class MovieGoerMenu extends AppHelper {


	public MovieGoerMenu(AppHelper prevApp) {
		// TODO Auto-generated constructor stub
		super(prevApp);
	}


	public void runInterface() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------");
		System.out.println("Welcome to Customer Module");
		System.out.println("-----------------------");
		//call list movie functions
		
		System.out.println("1) View movies"); // ->select movies -> booking ->check seat availability
		System.out.println("2) Rate movies");
		System.out.println("3) List top 5 movies by ratings");
		System.out.println("4) List top 5 movies by ticket sales");
		System.out.println("5) Make a booking"); //based on booking id/movie code
		System.out.println("6) View booking history\n");

		System.out.println("7) Return to main menu");
		System.out.println("-----------------------");
		System.out.println("Select an option :");
		int choice = sc.nextInt();	
		
		MovieGoerApp movieApp = new MovieGoerApp(this);
		
		
		switch(choice) {
		
		case 1:
			//View movies
			//movieReview.viewMovie();
			movieApp.movieView();
			break;
		case 2:
			
			//movieReview.setReview();
			movieApp.movieRate();
			//rate movies
			
			break;
		case 3:
			//list top 5 movies
			
			//movieTop5.movieSort();
			movieApp.movieTopRatings();
			
			break;
		case 4:
			movieApp.movieTopSales();
			break;
			
		case 5: 
			
			//Make booking
			movieApp.movieBooking();
			break;
		case 6:
			movieApp.movieViewBooking();
			break;
		case 7:
			break;
			
			
			
		default: break;
		
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}

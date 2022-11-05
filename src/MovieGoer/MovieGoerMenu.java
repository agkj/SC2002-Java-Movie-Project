package MovieGoer;

import java.io.IOException;
import java.util.Scanner;

import MovieGoer.AppInterface;
import Util.Serializer;

public class MovieGoerMenu {


	public MovieGoerMenu() {
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
		
		MovieGoerApp movieApp = new MovieGoerApp(null);
		
		
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
			movieApp.movieTop();
			
			
			break;
		case 4:
			//Make booking
			movieApp.movieBooking();

			break;
			
		case 5:
			
			
			
		default: break;
		
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}

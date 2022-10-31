package MovieGoer;

import java.util.Scanner;

import MovieGoer.AppInterface;

public class MovieGoerApp {


	public MovieGoerApp() {
		// TODO Auto-generated constructor stub
	}


	public void runInterface() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("------------------");
		System.out.println("Welcome to customer module");
		//call list movie functions
		
		System.out.println("1) Search movies"); // ->select movies -> booking ->check seat availability
		System.out.println("2) Rate movies");
		System.out.println("3) List top 5 movies");
		System.out.println("4) Check booking history"); //based on booking id/movie code
		System.out.println("5) Return to main menu");
		
		
		int choice = sc.nextInt();	
		
		switch(choice) {
		
		case 1:
			//search movies
			break;
		case 2:
			MovieGoerReview review = new MovieGoerReview();
			review.MovieGoerReview();
			
			//rate movies
			
			break;
		case 3:
			//list top 5 movies
			break;
		case 4:
			//check booking history
			
			System.out.println("-------Booking history--------");
			
			System.out.println("Enter your transaction ID");
			String TID = sc.next();
			
		
			break;
			
		case 5:
			
			
			
		default: break;
		
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}

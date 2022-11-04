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
		
		System.out.println("1) View movies"); // ->select movies -> booking ->check seat availability
		System.out.println("2) Rate movies");
		System.out.println("3) List top 5 movies");
		System.out.println("4) Check booking history"); //based on booking id/movie code
		System.out.println("5) Return to main menu");
		
		
		int choice = sc.nextInt();	
		MovieGoerReview movieReview = new MovieGoerReview(null);
		MovieGoerTop5 movieTop5 = new MovieGoerTop5(null);
		
		switch(choice) {
		
		case 1:
			//View movies
			;
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
			MovieGoerBooking movieBooking = new MovieGoerBooking();
			System.out.println("-------Make a Booking--------");
			
			//list available movies
			System.out.println("Choose a movie to book");
			
			//Choose cinema
			
			//Choose timing 
			
			//show available seats
			System.out.println("Choose your seat");
			
			
			System.out.println("Enter your name: ");
			System.out.println("Enter your email: ");
			System.out.println("Enter your phone number: ");
		
			break;
			
		case 5:
			
			
			
		default: break;
		
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
}

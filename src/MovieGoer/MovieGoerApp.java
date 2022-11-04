package MovieGoer;

import java.util.Scanner;

import Admin.AppInterface;

public class MovieGoerApp {

	public MovieGoerApp() {
		// TODO Auto-generated constructor stub
	}

	public void runInterface() {

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("------------------");
			System.out.println("Welcome to customer module");
			// call list movie functions

			System.out.println("1) View movies"); // ->select movies -> booking ->check seat availability
			System.out.println("2) Rate movies");
			System.out.println("3) List top 5 movies");
			System.out.println("4) Make a booking"); // login to customer mode 
			System.out.println("5) Return to main menu");
			

			int choice = sc.nextInt();
			MovieGoerReview movieReview = new MovieGoerReview(null);
			MovieGoerTop5 movieGoerSort = new MovieGoerTop5(null);
			switch (choice) {

			case 1:
				// search movies
				movieReview.viewMovie();

				break;
			case 2:

				movieReview.setReview();
				
				// rate movies

				break;
			case 3:
				// list top 5 movies
				movieGoerSort.movieSort();
				
				
				break;
			case 4:
				// Make a booking

				MovieGoerBooking movieBooking = new MovieGoerBooking();
				
				System.out.println("****Booking*****");
				
				System.out.println("Enter your name: ");
				String movieGoerName = sc.nextLine();
				sc.nextLine();
				System.out.println("Enter your email: ");
				String movieGoerEmail = sc.nextLine();
				
				System.out.println("Enter your mobile number: ");
				int movieGoerNumber = sc.nextInt();
				
				System.out.println("Enter cinema code: ");
				
				movieBooking.setCinemaCode("CD");
				System.out.println(movieBooking.setTicketID());
				
				
				
				

				break;

			case 5:

			default:
				break;

			}

		}

	}

}

package MovieGoer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Admin.MovieListingApp;
import Entities.Holiday;
import MovieGoer.AppInterface;
import Util.AppHelper;
import Util.FileReader;
import Util.MovieHelper;
import Util.Serializer;

public class MovieGoerMenu extends AppHelper {

	String root = System.getProperty("user.dir");

	public MovieGoerMenu(AppHelper prevApp) {
		// TODO Auto-generated constructor stub
		super(prevApp);
	}

	public void runInterface() {
		Scanner sc = new Scanner(System.in);
		Boolean stay = true;
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
			System.out.print("| 3) List top 5 movies by ticket sales (DISABLED) \t |");
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

		MovieGoerApp movieApp = new MovieGoerApp(this);
		MovieHelper movieHelper = new MovieHelper();
		switch(choice) {
			case 0:
				break;
			case 1:
				//View movies
				movieApp.movieView();
				break;
			case 2:
				// Rate Movies
				movieApp.movieRate();
				break;
			case 3:
				//list top 5 movies
				if(set_control_ticket == 1) {
					movieHelper.getTopListings(1);
				}
				else{
					System.out.println("THIS FEATURE IS DISABLED");
				}
				runInterface();
				break;
			case 4:
				if(set_control_rating == 1){
					movieHelper.getTopListings(2);

				}
				else{
					System.out.println("THIS FEATURE IS DISABLED");
				}
				runInterface();
				break;
			case 5:
				//Make booking
				movieApp.movieBooking();
				break;
			case 6:
				movieApp.movieViewBooking();
				break;
			default: 
				break;

			}
	}
	
}

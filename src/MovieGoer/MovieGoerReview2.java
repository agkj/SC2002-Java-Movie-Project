package MovieGoer;

import java.io.IOException;

import Admin.AppInterface;
import Admin.MovieListingApp;
import Entities.Movie;
import Util.Serializer;
import java.util.Scanner;

public class MovieGoerReview2 extends MovieListingApp {
	
	Scanner sc = new Scanner(System.in);

	public MovieGoerReview2(AppInterface prevApp) {
		super(prevApp);
		// TODO Auto-generated constructor stub
	}

	public void viewMovie() {
		super.viewMovies();
	}

	public void setReview() {

		
			
			try {
				// Read all available Movies
				if (movieFiles != null) {
					for (int i = 0; i < movieFiles.length; i++) {
						Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
						System.out.println((i + 1) + ") " + curr.getTitle());

					}
					
					System.out.println("Enter the movie you want to review");
					int choice = sc.nextInt();
					
					
					
					
					
				}

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
			
			
			
		

	}

}

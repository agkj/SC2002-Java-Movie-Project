package MovieGoer;

import java.io.File;
import java.io.IOException;

import Admin.AppInterface;
import Admin.MovieListingApp;
import Entities.ContentRating;
import Entities.Movie;
import Entities.MovieGenre;
import Entities.Review;
import Entities.ShowingStatus;
import Util.Serializer;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieGoerReview extends MovieListingApp {

	Scanner sc = new Scanner(System.in);

	public MovieGoerReview(AppInterface prevApp) {
		super(prevApp);
		// TODO Auto-generated constructor stub
	}

	public void viewMovie() {
		try {
			// Read all available Movies
			if (movieFiles != null) {
				for (int i = 0; i < movieFiles.length; i++) {
					Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
					System.out.println((i + 1) + ") " + curr.getTitle());
					System.out.println("  Showing Status: " + curr.getShowingStatus());
					System.out.println("  Synopsis: " + curr.getSynopsis());
					System.out.println("  Director: " + curr.getDirector());
					System.out.println("  Cast: " + curr.getCast());
					System.out.println("  Overall Ratings: " + curr.getOverallRating());
					System.out.println("  Past and Present Reviews: " + curr.getReviews());
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setReview() {

		System.out.println("****REVIEW MODULE****");
		try {

			for (int i = 0; i < movieFiles.length; i++) {
				Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
				System.out.println((i + 1) + ") " + curr.getTitle());
			}
			System.out.println("*********************");

			System.out.print("Enter movie index: ");
			int index = sc.nextInt() - 1;

			File selected = movieFiles[index];
			Movie movieToUpdate = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index].getName());

			ArrayList<Review> review = movieToUpdate.getReviews();

			Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index].getName());

			System.out.println("Now creating a new review for: " + curr.getTitle());
			System.out.println("Enter a rating from 1 to 5 stars");

			double movieOverallRating = sc.nextDouble();
			movieToUpdate.setOverallRating(movieOverallRating);

			// To save

			Serializer.serialize(root + "\\data\\movies\\" + selected.getName(), movieToUpdate);

			// Reload Movies
			this.load();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}

package MovieGoer;

import java.io.File;

import java.io.IOException;

import Util.AppHelper;
import Admin.MovieListingApp;
import Entities.Movie;
import Entities.Review;
import Util.Serializer;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieGoerReview extends MovieListingApp {

	Scanner sc = new Scanner(System.in);

	public MovieGoerReview(AppHelper prevApp) {
		super(prevApp);
		// TODO Auto-generated constructor stub
	}

	public void viewMovie() {
		System.out.println("------- VIEW MOVIE LISTING -------\n");

        try {
            // Read all available Movies
            if(movieFiles != null) {
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    System.out.println((i+1) + ") " + curr.getTitle());
                    System.out.println( "  Showing Status: " + curr.getShowingStatus());
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

		try {
			// Read all available Movies
			for (int i = 0; i < movieFiles.length; i++) {
				Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
				System.out.println((i + 1) + ") " + curr.getTitle());
			}

			System.out.print("Enter Movie Index to Update: ");
			int index = sc.nextInt() - 1;
			File selected = movieFiles[index];
			Movie movieToUpdate = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index].getName());
			ArrayList<Review> review = movieToUpdate.getReviews();
			double overallRating = movieToUpdate.getOverallRating();
			overallRating = overallRating * review.size();
			System.out.print("Enter Ratings (1 to 5): ");
			double userRating = sc.nextInt();
			String userReviewId = String.valueOf(review.size()+1); // get latest ID

			while (userRating < 1 || userRating > 5) {
				userRating = sc.nextInt();
				System.out.println("Please Enter Ratings (1 to 5): ");
			}
			System.out.println("Enter Review: ");
			 
			String buffer = sc.nextLine(); // required for previous sc's "\n"
			String userTextReview = sc.nextLine();

			review.add(new Review(userReviewId, userRating, userTextReview));
			movieToUpdate.setReviews(review);
			overallRating = (overallRating + userRating) / review.size();
			movieToUpdate.setOverallRating(overallRating);

			try {
				Serializer.serialize(path + "\\" + selected.getName(), movieToUpdate);

				// Reload Movies
				this.load();

				System.out.println("\n------- SUCCESS: UPDATED REVIEW -------\n");
				System.out.println(movieToUpdate);
			} catch (IOException e) {
				System.out.println("\n------- ERROR: PLEASE TRY AGAIN -------\n");
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("\n------- ERROR: MOVIE NOT IN RANGE -------\n");
			e.printStackTrace();
		}

	}
}
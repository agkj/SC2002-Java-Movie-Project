package MovieGoer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import Util.AppHelper;
import Admin.MovieListingApp;
import Entities.Movie;
import Entities.Review;
import Entities.ShowingStatus;
import Util.Serializer;

/**
 * [Movie-Goer Module] Movie Rating App to rate and review a movie.
 */
public class MovieRate extends MovieListingApp implements Serializable {

	Scanner sc = new Scanner(System.in);
	protected File pathCinema;
	protected File[] cinemaFiles;

	public MovieRate(AppHelper prevApp) {
		super(prevApp);

		// Try to read all cinema .dat files in movie directory
		pathCinema = new File(System.getProperty("user.dir") + "\\data\\cinema");

		// Store all movie .dat files
		cinemaFiles = pathCinema.listFiles();
	}


	public void movieRates() {
		try {
			// Read all available Movies
			System.out.println("==========================================================");
			System.out.println("\t\t RATE A MOVIE");

			ArrayList<Movie> filteredMovie = new ArrayList<Movie>();
			for (int i = 0; i < movieFiles.length; i++) {
				Movie curr = (Movie) Serializer.deSerialize(movieFiles[i].getAbsolutePath());

				if (curr.getShowingStatus() != ShowingStatus.Coming_Soon) // TODO Coming soon to filter
					filteredMovie.add(curr);
			}
			for (int i = 0; i < filteredMovie.size(); i++) {
				System.out.println((i + 1) + ") " + filteredMovie.get(i).getTitle());
			}

			System.out.println("0) Go back");
			System.out.print("Enter a movie index to rate: ");
			int index = sc.nextInt() - 1;
			if (index == -1) {
				AppHelper prevApp = goBack();
				prevApp.runInterface();
			}

			//File selected = movieFiles[index];
			//Movie movieToUpdate = (Movie) Serializer.deSerialize(movieFiles[index].getAbsolutePath());
			Movie movieToUpdate = filteredMovie.get(index);

			ArrayList<Review> review = movieToUpdate.getReviews();
			double overallRating = movieToUpdate.getOverallRating();
			overallRating = overallRating * review.size();
			System.out.print("Enter Ratings (1 to 5): ");
			double userRating = sc.nextDouble();
			String userReviewId = String.valueOf(review.size() + 1); // get latest ID

			while (userRating < 1 || userRating > 5) {
				userRating = sc.nextDouble();
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
				Serializer.serialize(path + "\\" + movieToUpdate.getMovieId() + ".dat", movieToUpdate);

				// Reload Movies
				this.load();
				System.out.println("==========================================================");
				System.out.println("\t\t SUCCESS: UPDATED REVIEW ");
				System.out.println("==========================================================");

			} catch (IOException e) {
				System.out.println("==========================================================");
				System.out.println("\t\t ERROR: PLEASE TRY AGAIN ");
				System.out.println("==========================================================");
				//e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("==========================================================");
			System.out.println("\t\t ERROR: MOVIE NOT IN RANGE ");
			System.out.println("==========================================================");
			//e.printStackTrace();
		}
		goBack().runInterface();

	}

	public void movieLoad() {
		// Try to read all movie .dat files in movie directory
		path = new File(System.getProperty("user.dir") + "\\data\\movies");

		// Store all movie .dat files
		movieFiles = path.listFiles();
	}
}

package MovieGoer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import Util.AppHelper;
import Admin.MovieListingApp;
import Entities.Movie;
import Util.Serializer;


/**
 * [Movie-Goer Module] Movie App to view movie listings.
 * Allow movie-gowers to view all available movie listings in the system (except for End_Of_Showing).
 */
public class MovieView extends MovieListingApp implements Serializable {

	Scanner sc = new Scanner(System.in);
	protected File pathCinema;
	protected File[] cinemaFiles;

	public MovieView(AppHelper prevApp) {
		super(prevApp);

		// Try to read all cinema .dat files in movie directory
		pathCinema = new File(System.getProperty("user.dir") + "\\data\\cinema");

		// Store all movie .dat files
		cinemaFiles = pathCinema.listFiles();
	}


	public void movieViews() {

		System.out.println("|------------------------------VIEW MOVIE LISTING-------------------------------|");


		try {
			// Read all available Movies
			ArrayList<Movie> filteredMovie = new ArrayList<Movie>();
			for (int i = 0; i < movieFiles.length; i++) {
				Movie curr = (Movie) Serializer.deSerialize(movieFiles[i].getAbsolutePath());

				if (!curr.isEndOfShowing())
					filteredMovie.add(curr);
			}

			if (filteredMovie != null) {
				for (int i = 0; i < filteredMovie.size(); i++) {
					System.out.println("|" + (i + 1) + ")Movie: " + filteredMovie.get(i).getTitle());
					System.out.println("|  Showing Status: " + filteredMovie.get(i).getShowingStatus());
					System.out.println("|  Synopsis: " + filteredMovie.get(i).getSynopsis());
					System.out.println("|  Director: " + filteredMovie.get(i).getDirector());
					System.out.println("|  Cast: " + filteredMovie.get(i).getCast());
					if(filteredMovie.get(i).getReviews().size() > 1) {
						System.out.printf("|  Overall Ratings: %.1f\n",filteredMovie.get(i).getOverallRating());
						System.out.println("|  Past and Present Reviews: " + filteredMovie.get(i).getReviews());
					}

					else {
						System.out.println("|  Overall Ratings: NA");
						System.out.println("|  Past and Present Reviews: NA");
					}
					System.out.println(
							"|-------------------------------------------------------------------------------|");				}
				System.out.println();
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		goBack().runInterface();
	}

}

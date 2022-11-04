package MovieGoer;

import java.io.File;
import java.io.IOException;

import Admin.AppInterface;
import Admin.MovieListingApp;
import Entities.Movie;
import Entities.Review;
import Util.Serializer;

import java.util.ArrayList;
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
				for(int i=0; i < movieFiles.length; i++) {
		            Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
		            System.out.println((i+1) + ") " + curr.getTitle());
		        }

		        System.out.print("Enter Movie Index to Update: ");
		        int index = sc.nextInt()-1;
		        //File selected = movieFiles[index];
		        File selected = movieFiles[index];
		        Movie movieToUpdate = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index].getName());
		        ArrayList<Review> review = movieToUpdate.getReviews();
		        //ArrayList<Review> review = new ArrayList<Review>();
		        //review = movieToUpdate.getReviews();
                System.out.print("Enter Ratings (1 to 5): ");
                double userRating = sc.nextInt();
                String userReviewId = String.valueOf(review.size());

                while(userRating < 1 || userRating > 5) {
                	userRating = sc.nextInt();
                    System.out.println("Please Enter Ratings (1 to 5): ");
                }
                System.out.println("Enter Review: ");

                //int j = review.size();
                //userReviewId = String.valueOf(j);
                String buffer = sc.nextLine();
                String userTextReview = sc.nextLine();

                //while(!userTextReview.equals("\n")) {
                //    System.out.print("Review " + (j++));

                //}
                //Review newUserReview = new Review("1", userRating, userTextReview);
                //newUserReview.setReviewId("1");
                //newUserReview.setReviewRating(userRating);
                //newUserReview.setReviewContent(userTextReview);
                //review.add(newUserReview);
                //review.add(newUserReview);
                //movieToUpdate.setReviews(review);
                review.add(new Review(userReviewId, userRating, userTextReview));
                //System.out.println("Review: "+ review);
                movieToUpdate.setReviews(review);
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
                
				/*if (movieFiles != null) {
					int i;
					for (i = 0; i < movieFiles.length; i++) {
						Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
						System.out.println((i + 1) + ") " + curr.getTitle());

					}
					
					System.out.println("Enter the movie you want to review");
					int choice = sc.nextInt();
					movieFiles[i].setOverallRating(choice); 
				*/
					
					
					
					

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
			
			
			
		

	}

}

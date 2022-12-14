package Admin;

import Entities.*;
import Util.AppHelper;
import Util.MovieHelper;
import Util.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * [Admin Module] Movie Listing App containing CRUD functions for Movies.
 */
public class MovieListingApp extends AppHelper {
    Scanner sc = new Scanner(System.in);

    protected String root = System.getProperty("user.dir");

    protected File path;
    protected File[] movieFiles;

    public MovieListingApp(AppHelper prevApp) {
        super(prevApp);

        this.load();
    }

    public void load() {
        // Try to read all movie .dat files in movie directory
        path = new File(System.getProperty("user.dir") + "\\data\\movies");

        // Store all movie .dat files
        movieFiles = path.listFiles();
    }

    @Override
    public void runInterface() {
    	System.out.println("==========================================================");
        System.out.println(" \t\t MANAGE MOVIE LISTINGS \t\t\t ");
        System.out.println("==========================================================");
        System.out.println("1) Create Movie Listing \t\t\t\t ");
        System.out.println("2) View Movie Listings \t\t\t\t ");
        System.out.println("3) Update Movie Listings \t\t\t\t ");
        System.out.println("4) Remove Movie Listings \t\t\t\t ");
        System.out.println("5) View Top Five Movies \t\t\t\t ");
        System.out.println("0) Return to Previous Menu \t\t\t\t ");
        System.out.println("==========================================================");
        //here try catch?TODO
        System.out.println("Select an option: ");

        int input = sc.nextInt();

        switch(input) {
            case 0:
                // Return to Previous
                AppHelper prevApp = goBack();
                prevApp.runInterface();

                break;
            case 1:
                // Create Listing
                createMovie();
                break;
            case 2:
                // View Listings
                viewMovies();
                break;
            case 3:
                // Update Listing
                updateMovie(); // to do
                break;
            case 4:
                // Delete Listing
                removeMovie();
                break;
            case 5:
                // View Top 5
                viewTopFive();
                break;
            default:
                break;
        }
    }

    /**
     * Create a new Movie listing.
     */
    //// (1) CREATE LISTING
    public void createMovie() {
    	System.out.println("==========================================================");
        System.out.println("\t\t CREATE MOVIE LISTING \t\t\t ");
        System.out.println("==========================================================");

        Movie newMovie = new Movie();

        //// MOVIE ID
        System.out.print("Enter Movie ID: ");
        String id = sc.nextLine();

        while(id.isEmpty()) {
            id = sc.nextLine();

            if(id.isEmpty())
                System.out.println("Please enter an ID.");
        }

        newMovie.setMovieId(id);

        //// TITLE
        System.out.print("Enter Movie Title: ");
        String title = sc.nextLine();

        while(title.isEmpty()) {
            title = sc.nextLine();

            if(title.isEmpty())
                System.out.println("Please enter a title.");
        }

        newMovie.setTitle(title);                           // Set Value

        //// SYNOPSIS
        System.out.print("Enter Synopsis: ");
        String synopsis = sc.nextLine();

        while(synopsis.isEmpty()) {
            synopsis = sc.nextLine();

            if(synopsis.isEmpty())
                System.out.println("Please enter a synopsis.");
        }

        newMovie.setSynopsis(synopsis);

        //// DIRECTOR
        System.out.print("Enter Director: ");
        String director = sc.nextLine();

        while(director.isEmpty()) {
            director = sc.nextLine();

            if(director.isEmpty())
                System.out.println("Please enter a director.");
        }

        newMovie.setDirector(director);

        //// MOVIE TYPE (i.e., 2D, 3D, etc)
        System.out.println("Select Movie Type: ");

        // Loop through all MovieGenre options
        for(int i=0; i < MovieType.values().length; i++) {
            System.out.println(i+1 + ") " + MovieType.values()[i]);
        }

        int type = sc.nextInt();

        while(type < 1 || type > MovieType.values().length) {
            type = sc.nextInt();

            if(type < 1 || type > MovieType.values().length)
                System.out.println("Please enter a valid movie type option.");
        }

        MovieType movieType = MovieType.values()[type-1];
        newMovie.setMovieType(movieType);

        //// GENRE (Want to allow multiple?)
        System.out.println("Select Genre: ");

        // Loop through all MovieGenre options
        for(int i=0; i < MovieGenre.values().length; i++) {
            System.out.println(i+1 + ") " + MovieGenre.values()[i]);
        }

        int genre = sc.nextInt();

        while(genre < 1 || genre > MovieGenre.values().length) {
            genre = sc.nextInt();

            if(genre < 1 || genre > MovieGenre.values().length)
                System.out.println("Please enter a valid genre option.");
        }

        MovieGenre movieGenre = MovieGenre.values()[genre-1];
        newMovie.setGenre(movieGenre);

        //// SHOWING STATUS
        System.out.println("Select Showing Status: ");

        // Loop through all ShowingStatus options
        for(int i=0; i < ShowingStatus.values().length; i++) {
            System.out.println( i + 1 + ") " + ShowingStatus.values()[i]);
        }

        int status = sc.nextInt();

        while(status < 1 || status > ShowingStatus.values().length) {
            System.out.println("Please enter a valid showing status option.");

            status = sc.nextInt();
        }

        ShowingStatus showStatus = ShowingStatus.values()[status-1];
        newMovie.setShowingStatus(showStatus);

        //// RUNTIME
        System.out.print("Enter Movie Runtime Duration: ");
        while(!sc.hasNextInt())
            System.out.println("Please enter a valid runtime duration");
        newMovie.setRuntime(sc.nextInt());

        //// CONTENT RATING (PG, etc)
        System.out.println("Select Content Rating: ");

        // Loop through all Content Rating options
        for(int i=0; i < ContentRating.values().length; i++)
            System.out.println(i+1 + ") " + ContentRating.values()[i]);

        int contentRating = sc.nextInt();

        while(contentRating < 1 || contentRating > ContentRating.values().length) {
            System.out.println("Please enter a valid content rating option.");
        }

        ContentRating rating = ContentRating.values()[contentRating-1];
        newMovie.setContentRating(rating);

        ////  CAST
        ArrayList<String> cast = new ArrayList<String>();
        int j = 1;
        String castName = sc.nextLine();

        while(!castName.equals("-1")) {
            System.out.print("Enter name of cast member " + (j++) + " (type -1 to end list): ");
            if(!castName.isEmpty())
                cast.add(castName);
            castName = sc.nextLine();
        }
        newMovie.setCast(cast);

        //// FIN, Show listing created

        try {
            Serializer.serialize(root + "\\data\\movies\\" + id + ".dat", newMovie);

            // Reload Movies
            this.load();

            System.out.println("\t\t SUCCESS: CREATED NEW MOVIE LISTING");
            System.out.println("==========================================================");
            //System.out.println(newMovie);
        } catch (IOException e) {
            e.printStackTrace();
        }

        runInterface();
    }

    /**
     * View all Movie listings.
     */
    //// (2) VIEW LISTINGS
    public void viewMovies() {
    	System.out.println("==========================================================");
        System.out.println("\t\t VIEW MOVIE LISTING \t\t\t ");
        System.out.println("==========================================================");

        try {
            // Read all available Movies
            if(movieFiles != null) {
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    System.out.println((i+1) + ") " + curr.getTitle());
                    System.out.println("  Showing Status: " + curr.getShowingStatus());
                    System.out.println("  Synopsis: " + curr.getSynopsis());
                    System.out.println("  Director: " + curr.getDirector());
                    System.out.println("  Cast: " + curr.getCast());
                    if(curr.getReviews().size() > 1) {
						System.out.printf("  Overall Ratings: %.2f\n",curr.getOverallRating());
						System.out.println("  Past and Present Reviews: " + curr.getReviews());
					}
					
				else {
					System.out.println("|  Overall Ratings: NA");
					System.out.println("|  Past and Present Reviews: NA");
				}
                    System.out.println("==========================================================");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("| \t\t END OF MOVIE LISTING \t\t\t ");
        System.out.println("==========================================================");
        runInterface();

    }

    /**
     * Update an existing Movie listing.
     */
    //// (3) UPDATE LISTING
    public void updateMovie() {
    	System.out.println("==========================================================");
        System.out.println(" \t\t UPDATE MOVIE LISTING \t\t\t ");
        System.out.println("==========================================================");
        try {
            for(int i=0; i < movieFiles.length; i++) {
                Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                System.out.println((i+1) + ") " + curr.getTitle());
            }

            System.out.print("\nEnter Movie Index to Update: ");
            int index = sc.nextInt()-1;

            File selected = movieFiles[index];
            Movie movieToUpdate = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index].getName());
            ArrayList<String> cast = movieToUpdate.getCast();

            int input;

            do {
                System.out.println("\nSelect Field to Update: ");

                //System.out.println("1) ID");
                System.out.println("1) Title");
                System.out.println("2) Synopsis");
                System.out.println("3) Director");
                System.out.println("4) Genre");
                System.out.println("5) Showing Status");
                System.out.println("6) Runtime");
                System.out.println("7) Content Rating");
                System.out.println("8) Add Cast");
                System.out.println("9) Remove Cast");

                System.out.println("\n0) Save and Return");
                System.out.println("-1) Discard and Return");

                System.out.println("\nEnter your choice: ");
                input = sc.nextInt();

                switch(input) {
                    case -1:
                        break;
                    case 0:
                        // Try to save file
                        try {
                            Serializer.serialize(root + "\\data\\movies\\" + selected.getName(), movieToUpdate);

                            // Reload Movies
                            this.load();

                            System.out.println("\t\t SUCCESS: UPDATED MOVIE LISTING \t\t\t ");
                            System.out.println("==========================================================");
                        } catch (IOException e) {
                            System.out.println("\t\t ERROR: PLEASE TRY AGAIN \t\t\t ");
                            System.out.println("==========================================================");
                            e.printStackTrace();
                        }

                        break;
                    case 1:
                        //// TITLE
                        System.out.print("Enter Updated Movie Title: ");
                        String title = sc.nextLine();

                        while(title.isEmpty()) {
                            title = sc.nextLine();

                            if(title.isEmpty())
                                System.out.println("Please enter a title.");
                        }

                        movieToUpdate.setTitle(title);

                        break;
                    case 2:
                        //// SYNOPSIS
                        System.out.print("Enter Updated Movie Synopsis: ");
                        String synopsis = sc.nextLine();

                        while(synopsis.isEmpty()) {
                            synopsis = sc.nextLine();

                            if(synopsis.isEmpty())
                                System.out.println("Please enter a synopsis.");
                        }

                        movieToUpdate.setSynopsis(synopsis);
                        break;
                    case 3:
                        //// DIRECTOR
                        System.out.print("Enter Updated Director: ");
                        String director = sc.nextLine();

                        while(director.isEmpty()) {
                            director = sc.nextLine();

                            if(director.isEmpty())
                                System.out.println("Please enter a director.");
                        }

                        movieToUpdate.setDirector(director);

                        break;
                    case 4:
                        //// GENRE
                        System.out.print("Select new updated genre: \n");

                        // Loop through all MovieGenre options
                        for(int i=0; i < MovieGenre.values().length; i++) {
                            System.out.println(i+1 + ") " + MovieGenre.values()[i]);
                        }
                        System.out.println("\nEnter your choice: ");
                        int genre = sc.nextInt();

                        while(genre < 1 || genre > MovieGenre.values().length) {
                            genre = sc.nextInt();

                            if(genre < 1 || genre > MovieGenre.values().length)
                                System.out.println("Please enter a valid genre option.");
                        }

                        MovieGenre movieGenre = MovieGenre.values()[genre-1];
                        movieToUpdate.setGenre(movieGenre);
                        

                        break;
                    case 5:
                        //// SHOWING STATUS
                        System.out.println("Select Updated Showing Status: ");

                        // Loop through all ShowingStatus options
                        for(int i=0; i < ShowingStatus.values().length; i++) {
                            System.out.println(i+1 + ") " + ShowingStatus.values()[i]);
                        }

                        int status = sc.nextInt();

                        while(status < 1 || status > ShowingStatus.values().length) {
                            System.out.println("Please enter a valid showing status option.");

                            status = sc.nextInt();
                        }

                        ShowingStatus showStatus = ShowingStatus.values()[status-1];
                        movieToUpdate.setShowingStatus(showStatus);

                        break;
                    case 6:
                        //// RUNTIME
                        System.out.print("Enter Updated Runtime Duration: ");
                        while(!sc.hasNextInt())
                            System.out.println("Please enter a valid runtime duration");
                        movieToUpdate.setRuntime(sc.nextInt());

                        break;
                    case 7:
                        //// CONTENT RATING (PG, etc)
                        System.out.println("Select Updated Content Rating: ");

                        // Loop through all Content Rating options
                        for(int i=0; i < ContentRating.values().length; i++)
                            System.out.println(i+1 + ") " + ContentRating.values()[i]);
                        
                        int contentRating = sc.nextInt();
                        	
                        while(contentRating < 1 || contentRating > ContentRating.values().length) {
                            System.out.println("Please enter a valid content rating option.");
                        }

                        ContentRating rating = ContentRating.values()[contentRating-1];
                        movieToUpdate.setContentRating(rating);

                        break;
                    case 8:
                        //// ADD CAST
                        // Print out current cast saved
                        System.out.println("Current Cast: ");
                        for(int i=0; i < cast.size(); i++) {
                            System.out.println((i+1) + ") " + cast.get(i));
                        }

                        int j = cast.size();

                        String castName = sc.nextLine();

                        while(!castName.equals("-1")) {
                            System.out.print("Enter name of cast member " + (j++) + " (type -1 to end list): ");

                            // Validation
                            if(!castName.isEmpty())
                                cast.add(castName);     // Add new Member

                            castName = sc.nextLine();
                        }

                        movieToUpdate.setCast(cast);    // Update Cast ArrayList for Movie Instance
                        

                        break;
                    case 9:
                        //// REMOVE CAST

                        // Print out current cast saved
                        System.out.println("Current Cast: ");
                        for(int i=0; i < cast.size(); i++) {
                            System.out.println((i+1) + ") " + cast.get(i));
                        }

                        System.out.println("Enter Cast Member Index to Delete: ");

                        while(!sc.hasNextInt() && sc.nextInt() < cast.size() && sc.nextInt() > 1)
                            System.out.println("Please enter a valid index.");


                        cast.remove(sc.nextInt()-1);      // Remove Member

                        movieToUpdate.setCast(cast);    // Update Cast ArrayList for Movie Instance

                        break;
                    default:
                        runInterface();
                        break;
                }

            } while (input > 0);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        runInterface();
    }

    /**
     * Remove an existing Movie from listing by setting its showing status to End_Of_Showing.
     */
    //// (4) DELETE LISTING
    public void removeMovie() {
        //delete movie is changing the status to END OF SHOWING
    	System.out.println("==========================================================");
        System.out.println("\t\t DELETE MOVIE LISTING \t\t\t ");
        System.out.println("==========================================================");

        // Delete from Listing (by index)
        try {
            for(int i=0; i < movieFiles.length; i++) {
                Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                System.out.println((i+1) + ") " + curr.getTitle());
            }

            System.out.print("Enter Movie Index to Delete: ");
            int indexToUpdate = sc.nextInt()-1;

            // Mark Movie as End_Of_Showing
            Movie selectedMovie = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[indexToUpdate].getName());

            selectedMovie.setShowingStatus(ShowingStatus.End_Of_Showing);

            //show the end of showing status
            System.out.println("Showing status has been set to : " + ShowingStatus.End_Of_Showing.toString());
            // Save Movie File
            try {
                Serializer.serialize(path + "\\" + movieFiles[indexToUpdate].getName(), selectedMovie);

                System.out.println("\t\t MOVIE SET TO END OF SHOWING ");
                System.out.println("==========================================================");

                // Reload movies
                this.load();

                runInterface();
            } catch (IOException e) {
                System.out.println("\t\t ERROR: PLEASE TRY AGAIN");
                System.out.println("==========================================================");
                e.printStackTrace();
            }

            /*
            // Delete movie .dat file
            if(movieFiles[indexToUpdate].delete()) {
                System.out.println("------- SUCCESSFULLY DELETED MOVIE LISTING -------\n");

                // Reload movies
                this.load();

                runInterface();
            } else {
                System.out.println("------- FAILED TO DELETE MOVIE LISTING -------\n");
            }
             */

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * View the Top 5 Movies by either Ticket Sales or Review Ratings
     */
    //view the top five by ranking movies by TicketSales and Overall reviewers' rating
    //TicketSales (Display the movie title and total sales)
    //Overall reviewers' rating (Display the movie title and overall rating)
    public void viewTopFive() {
    	System.out.println("==========================================================");
        System.out.println("\t\t VIEW TOP 5 MOVIES \t\t\t ");
        System.out.println("==========================================================");
        System.out.println("1) View by Ticket Sales");
        System.out.println("2) View by Overall Reviewers' Rating");

        int choice;
        System.out.println("\nEnter your choice: ");
        choice = sc.nextInt();

        MovieHelper movieHelper = new MovieHelper();

        switch(choice){
            case 1:
                // Show Top 5 by Ticket Sales
                movieHelper.getTopListings(1);
                runInterface();
                break;
            case 2:
                // Show Top 5 by Ratings
                movieHelper.getTopListings(2);
                runInterface();
                break;
            default:
                runInterface();
                break;
        };
    }
}

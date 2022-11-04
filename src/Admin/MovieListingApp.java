package Admin;

import Entities.*;
import Util.Serializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieListingApp extends AppInterface {
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir");

    protected File path;
    protected File[] movieFiles;

    public MovieListingApp(AppInterface prevApp) {
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
        System.out.println("------- MANAGE MOVIE LISTINGS -------\n");

        System.out.println("1) Create Movie Listing");
        System.out.println("2) View Movie Listings");
        System.out.println("3) Update Movie Listings");
        System.out.println("4) Delete Movie Listings");
        System.out.println("5) View Top Five Movies");
        System.out.println("\n0) Return to Previous Menu");

        while(!sc.hasNextInt())
            System.out.println("Please enter your choice");

        int input = sc.nextInt();

        switch(input) {
            case 0:
                // Return to Previous
                AppInterface prevApp = goBack();
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
                deleteMovie();
                break;
            case 5:
                // View Top 5
                viewTopFive();
                break;
            default:
                break;
        }
    }

    //// (1) CREATE LISTING
    public void createMovie() {
        System.out.println("------- CREATE MOVIE LISTING -------\n");

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

            System.out.println("\n------- SUCCESS: CREATED NEW MOVIE LISTING -------\n");
            System.out.println(newMovie);
        } catch (IOException e) {
            e.printStackTrace();
        }

        runInterface();
    }

    //// (2) VIEW LISTINGS
    public void viewMovies() {
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
        runInterface();

    }

    //// (3) UPDATE LISTING
    public void updateMovie() {
        try {
            for(int i=0; i < movieFiles.length; i++) {
                Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                System.out.println((i+1) + ") " + curr.getTitle());
            }

            System.out.print("Enter Movie Index to Update: ");
            int index = sc.nextInt()-1;

            File selected = movieFiles[index];
            Movie movieToUpdate = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index].getName());
            ArrayList<String> cast = movieToUpdate.getCast();

            int input;

            do {
                System.out.println("Select Field to Update: ");

                //System.out.println("1) ID");
                System.out.println("1) Title");
                System.out.println("2) Director");
                System.out.println("3) Genre");
                System.out.println("4) Showing Status");
                System.out.println("5) Runtime");
                System.out.println("6) Content Rating");
                System.out.println("7) Add Cast");
                System.out.println("8) Remove Cast");

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

                            System.out.println("\n------- SUCCESS: UPDATED MOVIE LISTING -------\n");
                            System.out.println(movieToUpdate);
                        } catch (IOException e) {
                            System.out.println("\n------- ERROR: PLEASE TRY AGAIN -------\n");
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
                    case 3:
                        //// GENRE
                        System.out.print("Select Updated Genre: ");

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
                        movieToUpdate.setGenre(movieGenre);

                        break;
                    case 4:
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
                    case 5:
                        //// RUNTIME
                        System.out.print("Enter Updated Runtime Duration: ");
                        while(!sc.hasNextInt())
                            System.out.println("Please enter a valid runtime duration");
                        movieToUpdate.setRuntime(sc.nextInt());

                        break;
                    case 6:
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
                    case 7:
                        ////  CAST
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
                    case 8:
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

    //// (4) DELETE LISTING
    public void deleteMovie() {
        //delete movie is changing the status to END OF SHOWING
        System.out.println("------- DELETE MOVIE LISTING -------\n");

        // Delete from Listing (by index)
        try {
            for(int i=0; i < movieFiles.length; i++) {
                Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                System.out.println((i+1) + ") " + curr.getTitle());
            }

            System.out.print("Enter Movie Index to Delete: ");
            int indexToRemove = sc.nextInt()-1;

            if(movieFiles[indexToRemove].delete()) {
                System.out.println("------- SUCCESSFULLY DELETED MOVIE LISTING -------\n");

                // Reload movies
                this.load();

                runInterface();
            } else {
                System.out.println("------- FAILED TO DELETE MOVIE LISTING -------\n");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //view the top five by ranking movies by TicketSales and Overall reviewers' rating
    //TicketSales (Display the movie title and total sales)
    //Overall reviewers' rating (Display the movie title and overall rating)
    public void viewTopFive(){
        System.out.println("------- VIEW TOP 5 MOVIES -------\n");
        System.out.println("1) View by TicketSales");
        System.out.println("2) View by Overall Reviewers' Rating");

        int choice;
        System.out.println("\nEnter your choice: ");
        choice = sc.nextInt();

        switch(choice){
            case 1:
                //movieFiles
                break;
            case 2:

                break;

            default:
                runInterface();
                break;
        };
    }
}

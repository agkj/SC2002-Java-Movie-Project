package Admin;

import Entities.ContentRating;
import Entities.Movie;
import Entities.MovieGenre;
import Entities.ShowingStatus;
import Util.Serializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieListingApp implements AppInterface {
    Scanner sc = new Scanner(System.in);
    ArrayList<Movie> movieList;

    Path currentRelativePath = Paths.get("");
    String root = currentRelativePath.toAbsolutePath().toString();

    public MovieListingApp() {
        try {
            // If file exists, deserialize
            movieList = (ArrayList) Serializer.deSerialize(root+"\\data\\Movies.dat");
        } catch (FileNotFoundException e) {
            // Else create new ArrayList
            movieList = new ArrayList<Movie>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void runInterface() {
        System.out.println("------- MANAGE MOVIE LISTINGS -------\n");

        System.out.println("1) Create Movie Listing");
        System.out.println("2) View Movie Listings");
        System.out.println("3) Update Movie Listening");
        System.out.println("4) Delete Movie Listening");

        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        switch(input) {
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
            default:
                break;
        }
    }

    //// (1) CREATE LISTING
    public void createMovie() {
        System.out.println("------- CREATE MOVIE LISTING -------\n");

        Movie newMovie = new Movie();

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

        //// Showing Status
        System.out.println("Select Showing Status: ");

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
        System.out.println("Successfully created movie listing!");
        System.out.println(newMovie.toString());


        try {
            movieList.add(newMovie);

            Serializer.serialize(root+"\\data\\Movies.dat", movieList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        runInterface();
    }

    //// (2) VIEW LISTINGS
    public void viewMovies() {
        System.out.println("------- VIEW MOVIE LISTING -------\n");

        try {
            ArrayList<Movie> movies = (ArrayList) Serializer.deSerialize(root+"\\data\\Movies.dat");

            for(int i=0; i < movies.size(); i++) {
                System.out.println("Movie " + (i+1) + ": " + movies.get(i).getTitle());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        runInterface();
    }

    //// (3) UPDATE LISTING
    public void updateMovie() {
        runInterface();
    }

    //// (4) DELETE LISTING
    public void deleteMovie() {
        System.out.println("------- DELETE MOVIE LISTING -------\n");
        System.out.println("1) Delete by Title");
        System.out.println("2) Delete from Listing");

        switch(sc.nextInt()) {
            case 1:
                System.out.print("Enter Movie Title: ");
                break;
            case 2:
                try {
                    ArrayList<Movie> movies = (ArrayList) Serializer.deSerialize(root+"\\data\\Movies.dat");

                    for(int i=0; i < movies.size(); i++) {
                        System.out.println("Movie " + (i+1) + ": " + movies.get(i).getTitle());
                    }

                    System.out.print("Enter Movie Index to Delete: ");
                    int indexToRemove = sc.nextInt()-1;
                    movies.remove(indexToRemove);

                    try {
                        System.out.println("------- SUCCESSFULLY DELETED MOVIE LISTING -------\n");
                        Serializer.serialize(root+"\\data\\Movies.dat", movies);

                        runInterface();      // go back to menu
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;
            default:
                runInterface();
                break;
        }
    }
}

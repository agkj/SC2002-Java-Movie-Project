package Admin;

import Entities.*;
import Util.Serializer;

import javax.swing.text.View;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class ShowtimeApp extends AppInterface {
    Scanner sc = new Scanner(System.in);
    ArrayList<Movie> movieList;

    String root = System.getProperty("user.dir");
    File path;
    File path_cineplex;
    File[] movieFiles;
    File[] cineplexFiles;
    public ShowtimeApp(AppInterface prevApp) {
        super(prevApp);
        //load the moviefiles
        this.load();
    }
    public void load() {
        // Try to read all movie .dat files in movie directory
        path = new File(System.getProperty("user.dir") + "\\data\\movies");

        // Store all movie .dat files
        movieFiles = path.listFiles();

        path_cineplex = new File(System.getProperty("user.dir") + "\\data\\cineplex");

        cineplexFiles = path_cineplex.listFiles();
    }


    @Override
    public void runInterface() {
        System.out.println("------- MANAGE SHOWTIME LISTINGS -------\n");

        System.out.println("1) Create Showtime Listing");
        System.out.println("2) View Showtime Listings");
        System.out.println("3) Update Showtime Listening");
        System.out.println("4) Delete Showtime Listening");
        System.out.println("\n0) Return to Previous Menu");

        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        switch(input) {
            case 0:
                goBack().runInterface();
                break;
            case 1:
                // Create Listing
                createShowtime();
                break;

            case 2:
                // View Listings
                viewShowtime();
                break;

            case 3:
                // Update Listing
                updateShowtime();
                break;
            case 4:
                // Delete Listing
                deleteShowtime();
                break;

            default:
                break;
        }
    }

    //// (1) CREATE LISTING
    public void createShowtime() {
        System.out.println("------- CREATE SHOWTIME LISTING -------\n");
        //View all the movies
        MovieListingApp app = new MovieListingApp(this);
        app.viewMovies();
        //check the movie ID
        // Read all available Movies
        try {
            // Read all available Movies
            System.out.println("Displaying all movies available.");
            if(movieFiles != null) {
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    System.out.println((i+1) + ") " + curr.getTitle());
                    System.out.println("Title : " + curr.getMovieId());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("What is the index of the movie u want to create the showtime?");
        int index;
        index = sc.nextInt();
        try {
            // Read all available Movies
            System.out.println("You have selected :\n");
            Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index - 1].getName());
            System.out.println((index+1) + ") " + curr.getTitle());
            System.out.println("MovieID : " + curr.getMovieId());
            System.out.println("\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ShowTime showtime = new ShowTime();

        //// Showtime (ps my whole program just crash and burn)
        System.out.println("Enter a showtime: in 2018-05-05 11:50 format");
        boolean showtimeValid = false;
        while(!showtimeValid) {
            String showtimeinput;
            showtimeinput = sc.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime  = LocalDateTime.parse(showtimeinput, formatter);
                showtime.setShowDateTime(dateTime);
                showtimeValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid showtime.");
            }
        }
        try {
            // Read all available Movies
            System.out.println("Movie" + index + "have add the showtime :\n");
            Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index - 1].getName());
            curr.addShowTime(showtime);
            System.out.println((index+1) + ") " + curr.getTitle());
            System.out.println("MovieID : " + curr.getMovieId());
            System.out.println("ShowTime" + showtime.getShowDateTime().toString());
            System.out.println("\n");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // Read all available Movies
            System.out.println("Displaying all Cineplex available.");
            //get the Cinema


            if(cineplexFiles != null) {
                for(int i=0; i < cineplexFiles.length; i++) {
                    Cineplex curr_cineplex = (Cineplex) Serializer.deSerialize(path + "\\" + cineplexFiles[index - 1].getName());
                    System.out.println((i+1) + ") " + curr_cineplex.getVenue());
                    System.out.println("CineplexID : " + curr_cineplex.getCineplexID());
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            }

        runInterface();
    }

    //// (2) VIEW LISTING
    public void viewShowtime() {
        System.out.println("------- VIEW SHOWTIME LISTING -------\n");

    }

    //// (3) UPDATE LISTING
    public void updateShowtime() {
        System.out.println("------- UPDATE SHOWTIME LISTING -------\n");

    }

    //// (4) DELETE LISTING
    public void deleteShowtime() {
        System.out.println("------- DELETE SHOWTIME LISTING -------\n");

    }
}

package Admin;

import Entities.*;
import Util.Serializer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowtimeApp extends AppInterface {
    Scanner sc = new Scanner(System.in);
    ArrayList<Movie> movieList;

    Path currentRelativePath = Paths.get("");
    String root = currentRelativePath.toAbsolutePath().toString();

    public ShowtimeApp(AppInterface prevApp) {
        super(prevApp);
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
        //need to get the id of the movie?
        ShowTime newShowTime = new ShowTime();

        //// Showtime (ps my whole program just crash and burn)
        System.out.println("Enter a showtime: in 2018-05-05T11:50:55 format");
        String showtime = sc.nextLine(); //enter in this format "2018-05-05T11:50:55"

        boolean showtimeValid = false;

        while(!showtimeValid) {
            showtime = sc.nextLine();

            try {
                LocalDateTime dateTime = LocalDateTime.parse(showtime);
                showtimeValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid showtime.");
            }

        }

        //View all the movies
//        MovieListingApp app = new MovieListingApp(this);
//        app.viewMovies();
//        //check the movie ID
//        // Read all available Movies
//        try {
//            // Read all available Movies
//            System.out.println("Displaying all movies available.");
//            if(movieFiles != null) {
//                for(int i=0; i < movieFiles.length; i++) {
//                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
//                    System.out.println((i+1) + ") " + curr.getTitle());
//                    System.out.println("Title : " + curr.getMovieId());
//                }
//            }
//
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println("What is the index of the movie u wish to create the ticket for?");
//        int index;
//        index = sc.nextInt();
//        try {
//            // Read all available Movies
//            System.out.println("You have selected :\n");
//            Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index - 1].getName());
//            System.out.println((index+1) + ") " + curr.getTitle());
//            System.out.println("MovieID : " + curr.getMovieId());
//            System.out.println("\n");
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Ticket ticket = new Ticket();
//        System.out.println("Enter the ticket type: (STANDARD, SENIOR, STUDENT)");
//        String tickettype = sc.nextLine();
//
//        System.out.println("Enter");
////        this.ticketPrice = price;
////        this.ticketType = ticketType;
////        this.moviegenre = moviegenre;
////        this.cinemaclass = cinemaclass;
////        this.holiday = holiday;


        //movieList =


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

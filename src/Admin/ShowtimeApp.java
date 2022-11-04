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
        System.out.println("3) Update Showtime Listings");
        System.out.println("4) Delete Showtime Listings");
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
        ShowTime newShowtime = new ShowTime();

        // Read all available Movies
        int index, cine_index;
        try {
            // Read all available Movies
            System.out.println("Displaying all movies available.");
            if(movieFiles != null) {
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    System.out.println((i + 1) + ") " + curr.getTitle());
                }
            }
            System.out.print("\nSelect Movie to create Showtime for: ");
            index = sc.nextInt();

            // Show Selected Movie
            System.out.println("You have selected :\n");
            Movie selectedMovie = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index - 1].getName());
            System.out.println((index) + ") " + selectedMovie.getTitle());

            // Get Showtime Date and Time
            System.out.println("Enter a showtime: in 2022-05-05 11:50 format");

            // Validation for DateTime Input
            boolean showtimeValid = false;
            while(!showtimeValid) {
                String showtimeinput;
                showtimeinput = sc.nextLine();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTime  = LocalDateTime.parse(showtimeinput, formatter);

                    newShowtime.setShowDateTime(dateTime);
                    showtimeValid = true;

                    //selectedMovie.addShowTime(newShowtime);
                    //System.out.println("The showtime added is : " + newShowtime.getShowDateTime().toString());

                } catch (DateTimeParseException e) {
                    System.out.println("Please enter a valid showtime.");
                }
            }
            System.out.println("\n");

            // Assign Cineplex to Showtime
            try {
                // Read all available Cineplex
                System.out.println("Displaying all Cineplex available: ");

                if(cineplexFiles != null) {
                    for(int i=0; i < cineplexFiles.length; i++) {
                        Cineplex curr_cineplex = (Cineplex) Serializer.deSerialize(path_cineplex + "\\" + cineplexFiles[i].getName());
                        System.out.println((i+1) + ") " + curr_cineplex.getVenue());
                    }
                }

                // Select Cineplex to assign
                System.out.print("Select Cineplex: ");
                cine_index = sc.nextInt();
                Cineplex selectedCineplex = (Cineplex) Serializer.deSerialize(path_cineplex + "\\" + cineplexFiles[cine_index - 1].getName());
                System.out.println("You have selected "+ selectedCineplex.getVenue());

                newShowtime.setCineplexID(selectedCineplex.getCineplexID());

                // Select Cinema to assign

                // Display available cinemas of selected Cineplex
                ArrayList<Cinema> cinemaList = selectedCineplex.getListOfCinemas();
                for(int i=0; i < cinemaList.size(); i++) {
                    System.out.println((i+1) + ") " + cinemaList.get(i).getCinemaID());
                }

                System.out.print("Select Cinema: ");
                int cinema_index = sc.nextInt();
                Cinema selectedCinema = cinemaList.get(cinema_index-1);

                newShowtime.setCinemaID(selectedCinema.getCinemaID());

                selectedMovie.addShowTime(newShowtime);

                // Try to save file
                try {
                    // Update Movie File with updated showtime arraylist
                    Serializer.serialize(root + "\\data\\movies\\" + selectedMovie.getMovieId() + ".dat", selectedMovie);

                    System.out.println("\n------- SUCCESS: CREATED SHOWTIME -------\n");
                } catch (IOException e) {
                    System.out.println("\n------- ERROR: PLEASE TRY AGAIN -------\n");
                    e.printStackTrace();
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        runInterface();
    }

    //// (2) VIEW LISTING
    public void viewShowtime() {
        System.out.println("------- VIEW SHOWTIME LISTING -------\n");

        try {
            // Read all available Movies
            //show movies and get their list of showtimes
            System.out.println("Available Movies: ");
            if(movieFiles != null) {
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    System.out.println((i+1) + ") " + curr.getTitle());
                }
            }
            //next tell them to select the movie index then we get the movieID
            System.out.println("\nSelect a Movie: ");
            int index = sc.nextInt();
            Movie selectedMovie = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index - 1].getName());
            System.out.println("You have selected: " + selectedMovie.getTitle() + "\n");

            // Get Cineplex
            try {
                // Read all available Cineplex
                System.out.println("Displaying all Cineplex: ");
                //get the Cinema
                if(cineplexFiles != null) {
                    for(int i = 0; i < cineplexFiles.length; i++) {
                        Cineplex curr_cineplex = (Cineplex) Serializer.deSerialize(path_cineplex + "\\" + cineplexFiles[i].getName());
                        System.out.println((i+1) + ") " + curr_cineplex.getVenue());
                    }
                }
                System.out.println("Select your Cineplex: ");
                int cine_index = sc.nextInt();

                Cineplex selectedCineplex = (Cineplex) Serializer.deSerialize(path_cineplex + "\\" + cineplexFiles[cine_index-1].getName());
                String selectedCineplexId = selectedCineplex.getCineplexID();

                //if the choosen ID is the same as the CineplexID
                System.out.println("Available Showtimes for: " + selectedMovie.getTitle());
                System.out.println(selectedMovie.getShowTimes().size());
                for(int i = 0; i <  selectedMovie.getShowTimes().size(); i++){
                    ShowTime currShowTime = selectedMovie.getShowTimes().get(i);

                    if(currShowTime.getCineplexID().equals(selectedCineplexId)) {
                        System.out.println("Cinema Hall: " + currShowTime.getCinemaID());
                        System.out.println("Time: " + currShowTime.getShowDateTime() + " (" + currShowTime.getShowTimeStatus() + ")");

                        System.out.print("\n");
                    }
                }

                System.out.println("");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        runInterface();
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

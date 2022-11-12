package Admin;

import Entities.*;
import Util.AppHelper;
import Util.Serializer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * [Admin Module] Showtime App containing CRUD functions for Movie ShowTimes.
 */
public class ShowtimeApp extends AppHelper {
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir");
    File path, path_cineplex, path_cinema;
    File[] movieFiles, cineplexFiles, cinemaFiles;

    public ShowtimeApp(AppHelper prevApp) {
        super(prevApp);
        //load the moviefiles
        this.load();
    }

    /**
     * Load necessary data files (i.e., cineplexes, cinemas, movies).
     */
    public void load() {
        // Try to read all movie .dat files in movie directory
        path = new File(System.getProperty("user.dir") + "\\data\\movies");

        // Store all movie .dat files
        movieFiles = path.listFiles();

        path_cineplex = new File(System.getProperty("user.dir") + "\\data\\cineplex");
        cineplexFiles = path_cineplex.listFiles();

        path_cinema = new File(System.getProperty("user.dir") + "\\data\\cinema");
        cinemaFiles = path_cinema.listFiles();
    }


    @Override
    public void runInterface() {
        System.out.println("------- MANAGE SHOWTIME LISTINGS -------\n");

        System.out.println("1) Create Showtime Listing");
        System.out.println("2) View Showtime Listings");
        System.out.println("3) Update Showtime Listings");
        System.out.println("4) Delete Showtime Listings");
        System.out.println("\n0) Return to Previous Menu");
        System.out.println("------------------------------------------");

        System.out.println("Select an option: ");

        //TODO try catch
        //int input = sc.nextInt();

        /*
        boolean check = false;
        while(!check) {
            System.out.println("Please enter a valid input");
            input = sc.nextInt();
        }

         */
        while(!sc.hasNextInt()) {
            System.out.println("Please enter a valid input");

        }

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

    /**
     * Create new movie ShowTime at a particular Cineplex and Cinema
     */
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
            System.out.println("You have selected :");
            Movie selectedMovie = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index - 1].getName());
            System.out.println((index) + ") " + selectedMovie.getTitle() + "\n");

            // Get Showtime Date and Time
            System.out.println("Enter a showtime: in YYYY-MM-DD HH:MM format. eg: 2022-04-21 13:00");
            

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
                    //System.out.println("Please enter a valid showtime."); // TODO magic
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
                System.out.println("");

                ArrayList<Cinema> cinemaList = selectedCineplex.getListOfCinemas();
                for(int i=0; i < cinemaList.size(); i++) {
                    System.out.println((i+1) + ") " + cinemaList.get(i).getCinemaID());
                }

                System.out.print("Select Cinema: ");
                int cinema_index = sc.nextInt();
                Cinema selectedCinema = cinemaList.get(cinema_index-1);

                newShowtime.setCinemaID(selectedCinema.getCinemaID());

                // Initialise Showtime layout to follow Cinema layout
                newShowtime.setShowTimeLayout(selectedCinema.getLayout());

                // Initialise Num Of Available Seats to follow Cinema
                newShowtime.setNumOfAvailSeats(selectedCinema.getNumOfSeats());

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

    /**
     * View all ShowTime listings of a particular movie at a specified Cineplex.
     */
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
                    System.out.println((i+1) + ") " + curr.getTitle() + " [" + curr.getContentRating().toString() + "] - " + curr.getShowingStatus()) ;
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
                System.out.println("\nAvailable Showtimes at " + selectedCineplex.getVenue() + " for " + selectedMovie.getTitle() + ": \n");

                for(int i = 0; i <  selectedMovie.getShowTimes().size(); i++){
                    ShowTime currShowTime = selectedMovie.getShowTimes().get(i);

                    if(currShowTime.getCineplexID().equals(selectedCineplexId)) {

                        String dateTime = currShowTime.getShowDateTime().toString();

                        if(currShowTime.checkDayType().equals(DayType.HOLIDAY))
                            dateTime += " (PH)";

                        Cinema selectedCinema = (Cinema) Serializer.deSerialize(path_cinema + "\\" + currShowTime.getCinemaID() + ".dat");
                        System.out.println("Cinema Hall " + currShowTime.getCinemaID() + " (" + selectedCinema.getCinemaClass() + " Class)");
                        System.out.println("Date and Time: " + dateTime + " (" + currShowTime.getShowTimeStatus() + ")");
                        System.out.println("No. of Seats Available: " + currShowTime.getNumOfAvailSeats());
                        System.out.println("Seating Layout: ");
                        currShowTime.showLayout();

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

    /**
     * Update details of an existing ShowTime listing such as the DateTime and Showing Status (i.e., Available, Sold_Out).
     */
    //// (3) UPDATE LISTING
    public void updateShowtime() {
        System.out.println("------- UPDATE SHOWTIME LISTING -------\n");

        try {
            // Select Movie
            // Read all available Movies
            if(movieFiles != null) {
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    System.out.println((i + 1) + ") " + curr.getTitle());
                }
            }
            System.out.print("Select Movie: ");
            int movieIndex = sc.nextInt();

            // Show Selected Movie
            Movie selectedMovie = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[movieIndex-1].getName());

            System.out.println("------------------");
            System.out.println("You have selected :");
            System.out.println(selectedMovie.getTitle());

            // Select Showtime to update
            ArrayList<ShowTime> showtimeList = selectedMovie.getShowTimes();
            //
            System.out.println("These are all the showtimes:");
            if(showtimeList.size() > 0) {
                // Display all available showtimes
                for(int i=0; i < showtimeList.size(); i++) {
                    ShowTime currShowTime = showtimeList.get(i);

                    Cineplex cineplex = (Cineplex) Serializer.deSerialize(root + "\\data\\cineplex\\" + currShowTime.getCineplexID() + ".dat");
                    String cinemaId = currShowTime.getCinemaID();

                    System.out.println((i+1) + ") " + cineplex.getVenue() + " (Cinema ID: " + cinemaId + ")");
                    System.out.println("Time: " + currShowTime.getShowDateTime().toString());

                    System.out.print("\n");
                }

                System.out.print("\nSelect which showtime to update: ");
                int showtime_index = sc.nextInt();

                ShowTime selectedShowTime = showtimeList.get(showtime_index-1);

                int input;

                do {
                    System.out.println("\nSelect action to update for: " + selectedMovie.getTitle() + " Showtime");
                    System.out.println("1) Update Date and Time");
                    System.out.println("2) Update Showing Status");

                    System.out.println("\n0) Save and Return");

                    input = sc.nextInt();

                    switch(input) {
                        case 0:
                            // Try to save file
                            try {
                                // Update Movie Object
                                selectedMovie.updateShowTime(showtime_index-1, selectedShowTime);

                                // Update Movie File with updated showtime arraylist
                                Serializer.serialize(root + "\\data\\movies\\" + selectedMovie.getMovieId() + ".dat", selectedMovie);

                                System.out.println("\n------- SUCCESS: UPDATED SHOWTIME -------\n");

                                runInterface();
                            } catch (IOException e) {
                                System.out.println("\n------- ERROR: PLEASE TRY AGAIN -------\n");
                                e.printStackTrace();
                            }
                        case 1:
                            // Update Date and Time
                            System.out.println("Enter updated Date and Time in 2022-05-05 11:50 format");

                            boolean showtimeValid = false;
                            while(!showtimeValid) {
                                String showtimeinput;
                                showtimeinput = sc.nextLine();

                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                    LocalDateTime dateTime  = LocalDateTime.parse(showtimeinput, formatter);

                                    selectedShowTime.setShowDateTime(dateTime);
                                    showtimeValid = true;

                                    System.out.print("Saved Showing Date and Time.");

                                } catch (DateTimeParseException e) {
                                    //System.out.println("Please enter a valid showtime."); TODO magic
                                }
                            }

                            break;
                        case 2:
                            // Update Showing Status

                            // Display all available showtime status
                            System.out.print("Select Updated Show Time Status: \n");
                            for(int i=0; i < ShowTimeStatus.values().length; i++) {
                                System.out.println((i+1) + ") " + ShowTimeStatus.values()[i]);
                            }
                            // Get updated showtime status
                            int statusUpdate = sc.nextInt();

                            selectedShowTime.setShowTimeStatus(ShowTimeStatus.values()[statusUpdate-1]);

                            System.out.print("Saved Showing Status.\n");

                            break;
                        default:
                            runInterface();
                            break;
                    }
                } while(input > 0);

            } else {
                // No showtimes available
                System.out.println("No showtimes available for this movie.");
                runInterface();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\n------- ERROR: PLEASE TRY AGAIN -------\n");
            e.printStackTrace();
        }
    }

    /**
     * Delete an existing ShowTime listing.
     */
    //// (4) DELETE LISTING
    public void deleteShowtime() {
        System.out.println("------- DELETE SHOWTIME LISTING -------\n");

        try {
            // Select Movie
            // Read all available Movies
            if(movieFiles != null) {
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    System.out.println((i + 1) + ") " + curr.getTitle());
                }
            }
            System.out.print("Select Movie: ");
            int movieIndex = sc.nextInt();

            // Show Selected Movie
            Movie selectedMovie = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[movieIndex-1].getName());

            System.out.println("You have selected :\n");
            System.out.println(selectedMovie.getTitle());

            // Select Showtime to remove
            ArrayList<ShowTime> showtimeList = selectedMovie.getShowTimes();

            if(showtimeList.size() > 0) {
                // Display all available showtimes
                for(int i=0; i < showtimeList.size(); i++) {
                    ShowTime currShowTime = showtimeList.get(i);

                    Cineplex cineplex = (Cineplex) Serializer.deSerialize(root + "\\data\\cineplex\\" + currShowTime.getCineplexID() + ".dat");
                    String cinemaId = currShowTime.getCinemaID();

                    System.out.println((i+1) + ") " + cineplex.getVenue() + " (Cinema ID: " + cinemaId + ")");
                    System.out.println("Time: " + currShowTime.getShowDateTime().toString());
                }

                System.out.print("Select which showtime to remove: ");
                int showtime_index = sc.nextInt();

                selectedMovie.removeShowTime(showtime_index-1);

                // Try to save file
                try {
                    // Update Movie File with updated showtime arraylist
                    Serializer.serialize(root + "\\data\\movies\\" + selectedMovie.getMovieId() + ".dat", selectedMovie);

                    System.out.println("\n------- SUCCESS: CREATED SHOWTIME -------\n");

                    runInterface();
                } catch (IOException e) {
                    System.out.println("\n------- FAILED: PLEASE TRY AGAIN -------\n");
                    e.printStackTrace();
                }
            } else {
                // No showtimes available
                System.out.println("No showtimes available for this movie.");
                runInterface();
            }


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\n------- ERROR: PLEASE TRY AGAIN -------\n");
            e.printStackTrace();
        }

    }
}

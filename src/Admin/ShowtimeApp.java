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
        ShowTime showtime = new ShowTime();
        //check the movie ID
        // Read all available Movies
        int index, cine_index;
        try {
            // Read all available Movies
            System.out.println("Displaying all movies available.");
            if(movieFiles != null) {
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    System.out.println((i + 1) + ") " + curr.getTitle());
                    System.out.println("Title : " + curr.getMovieId());

                }
            }
            System.out.println("\nWhat is the index of the movie u want to create the showtime?");
            index = sc.nextInt();
            System.out.println("You have selected :\n");
            Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index - 1].getName());
            System.out.println((index) + ") " + curr.getTitle());
            System.out.println("MovieID : " + curr.getMovieId());
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
                    System.out.println("Movie" + index + "have add the showtime :\n"+ showtime.getShowDateTime().toString());
                    //curr.setShowTimes(new ArrayList<ShowTime>());
                    curr.addShowTime(showtime);
                    System.out.println("The showtime added is : " + showtime.getShowDateTime().toString());
                    //check the size of arraylist (it is 1 here leh)
                    System.out.println("Size of arrayList : " + curr.getShowTimes().size());


                } catch (DateTimeParseException e) {
                    System.out.println("Please enter a valid showtime.");
                }
            }
            System.out.println("\n");

            try {
                // Read all available Cineplex
                System.out.println("Displaying all Cineplex available.");
                //get the Cinema
                if(cineplexFiles != null) {
                    for(int i=0; i < cineplexFiles.length; i++) {
                        Cineplex curr_cineplex = (Cineplex) Serializer.deSerialize(path_cineplex + "\\" + cineplexFiles[i].getName());
                        System.out.println((i+1) + ") " + curr_cineplex.getVenue());
                        System.out.println("CineplexID : " + curr_cineplex.getCineplexID());
                    }
                }
                System.out.println("Enter the Cineplex Type no.: ");
                cine_index = sc.nextInt();
                Cineplex current = (Cineplex) Serializer.deSerialize(path_cineplex + "\\" + cineplexFiles[cine_index - 1].getName());
                System.out.println("You have selected "+ current.getVenue());
                //set the showtime cineplex and link to the movie?? done
                curr.getShowTimes().get(index-1).setCineplex(current);
                System.out.println("This is the Cineplex : \n" + curr.getShowTimes().get(index - 1).getCineplex().toString());
                // Try to save file
                try {
                    Serializer.serialize(root + "\\data\\movies\\" + curr.getMovieId() + ".dat", curr);

                    System.out.println("\n------- SUCCESS: UPDATED MOVIE LISTING -------\n");
                    System.out.println(curr);
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
            if(movieFiles != null) {
                for(int i=0; i < movieFiles.length; i++) {
                    Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[i].getName());
                    System.out.println((i+1) + ") " + curr.getTitle());
                }
            }
            //next tell them to select the movie index then we get the movieID
            System.out.println("Select the movie index you wish to see.\n");
            System.out.println("Enter your option : ");
            int index = sc.nextInt();
            Movie curr = (Movie) Serializer.deSerialize(path + "\\" + movieFiles[index - 1].getName());
            System.out.println("You have selected : " + curr.getTitle() + "\n");
            //why is this arraylist 0???  cos of the initialisation when curr object is created, the arraylist is reset... i think
            System.out.println("Size of arrayList : " + curr.getShowTimes().size());
            Cineplex cineplexChoose = curr.getShowTimes().get(index - 1).getCineplex();
            System.out.println("What is the Cineplex you wish to select?");

            try {
                // Read all available Cineplex
                System.out.println("Displaying all Cineplex available.");
                //get the Cinema
                if(cineplexFiles != null) {
                    for(int i = 0; i < cineplexFiles.length; i++) {
                        Cineplex curr_cineplex = (Cineplex) Serializer.deSerialize(path_cineplex + "\\" + cineplexFiles[i].getName());
                        System.out.println((i+1) + ") " + curr_cineplex.getVenue());
                    }
                }
                System.out.println("Enter the Cineplex Type no. : ");
                int cine_index = sc.nextInt();
                Cineplex current = (Cineplex) Serializer.deSerialize(path_cineplex + "\\" + cineplexFiles[cine_index - 1].getName());
                String cineplexID = current.getCineplexID();
                //if the choosen ID is the same as the CinplexID
                if(cineplexID == cineplexChoose.getCineplexID()){
                    System.out.println("These are all the ShowTimes: ");
                    for(int i = 0; i <  curr.getShowTimes().size(); i++){
                        System.out.println("Time: " + curr.getShowTimes().get(i));
                    }
                }

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

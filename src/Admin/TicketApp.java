package Admin;

import Entities.*;
import Util.Serializer;

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
public class TicketApp extends AppInterface{
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir");
    File path;
    File[] movieFiles;
    public TicketApp(AppInterface prevApp) {
        super(prevApp);
        //load the moviefiles
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


        System.out.println("------- MANAGE TICKET PRICING -------\n");

        System.out.println("1) Create Ticket Pricing");
        System.out.println("2) View Ticket Pricing");
        System.out.println("3) Update Ticket Pricing");
        System.out.println("4) Delete Ticket Pricing");
        System.out.println("\n0) Return to Previous Menu");

        while(!sc.hasNextInt())
            System.out.println("Please enter your choice");

        int input = sc.nextInt();

        switch(input){
            case 1:
                createTickets();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                runInterface();
                break;
        };
    }
    public void createTickets(){
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
        System.out.println("What is the index of the movie u wish to create the ticket for?");
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
        Ticket ticket = new Ticket();
        System.out.println("Enter the ticket type: (STANDARD, SENIOR, STUDENT)");
        String tickettype = sc.nextLine();

        System.out.println("Enter");
//        this.ticketPrice = price;
//        this.ticketType = ticketType;
//        this.moviegenre = moviegenre;
//        this.cinemaclass = cinemaclass;
//        this.holiday = holiday;

    }

    public void viewTickets(){

    }
    public void updateTickets(){

    }
    public void removeTickets(){

    }
}

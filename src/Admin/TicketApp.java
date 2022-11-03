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

    }

    public void viewTickets(){

    }
    public void updateTickets(){

    }
    public void removeTickets(){

    }
}

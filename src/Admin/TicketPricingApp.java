package Admin;

import Entities.CinemaClass;
import Entities.TicketType;
import Util.FileReader;

import java.util.Scanner;
public class TicketPricingApp extends AppInterface{
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir") + "\\data\\ticket_pricing\\";
    public TicketPricingApp(AppInterface prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
        System.out.println("------- CONFIGURE TICKET PRICING -------\n");

        System.out.println("1) Standard Ticket Prices");
        System.out.println("2) Movie Type (e.g., 3D, blockbuster)");
        System.out.println("3) Cinema Class (e.g., Platinum)");
        System.out.println("4) Ticket Type (e.g., Adult)");
        System.out.println("5) Day of the Week/Holiday");
        System.out.println("\n0) Return to Previous Menu");

        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        // Let Base Ticket Price = $10

        switch(input) {
            case 0:
                goBack().runInterface();
                break;
            case 1:
                // Standard Ticket Prices (i.e., base price, booking fee)
                configureBase();
                break;
            case 2:
                // Movie Type
                configureMovieType();
                break;
            case 3:
                // Cinema Class
                configureCinemaClass();
                break;
            case 4:
                // Ticket Type
                configureTicketType();
                break;
            case 5:
                // Day of the Week
                break;
            default:
                break;
        }
    }

    // (1) Standard Ticket Prices
    public void configureBase() {
        System.out.println("------- CONFIGURE BASE TICKET PRICING -------\n");

        System.out.println("1) Base Ticket Price");
        System.out.println("2) Booking Fee");
        System.out.println("\n0) Return to Previous Menu");

        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        // Let Base Ticket Price = $10

        switch(input) {
            case 0:
                runInterface();
                break;
            case 1:
                // Base Ticket Price
                System.out.print("Enter Standard Ticket Price: ");

                while(!sc.hasNextInt())
                    System.out.println("Please enter a valid price.");

                int standardPrice = sc.nextInt();

                String data = "Standard," + standardPrice;

                FileReader.replaceLine(root + "standard_ticket.txt", "Standard", "Standard," + standardPrice, false);

                runInterface();

                break;
            case 2:
                // Booking Fee
                System.out.print("Enter Booking Fee: ");

                while(!sc.hasNextInt())
                    System.out.println("Please enter a valid fee.");

                int bookingFee = sc.nextInt();

                FileReader.replaceLine(root + "standard_ticket.txt", "Booking", "Booking," + bookingFee, false);

                runInterface();

                break;
            default:
                break;
        }
    }

    // (2) Modify Movie Type Pricing
    public void configureMovieType() {
        System.out.println("------- CONFIGURE MOVIE TYPE PRICING -------\n");

        FileReader.readFile(root + "\\data\\ticket_settings\\ticket_type.txt");

        // Display all movie types

        //// Get Movie Type to Change
        System.out.print("Enter Movie Type to Configure: ");
        String movieType = sc.nextLine();

        while(movieType.isEmpty()) {
            movieType = sc.nextLine();

            if(movieType.isEmpty())
                System.out.println("Please enter a movie type.");
        }

        //// Get New Price
        System.out.print("Enter New Price Modifier: ");
        while(!sc.hasNextInt())
            System.out.println("Please enter a valid price modifier.");

        int newPrice = sc.nextInt();
    }

    // (3) Cinema Class
    public void configureCinemaClass() {
        System.out.println("------- CONFIGURE CINEMA CLASS PRICING -------\n");

        // Print out all cinema classes
        for(int i=0; i < CinemaClass.values().length; i++) {
            System.out.println((i+1) + ") " + CinemaClass.values()[i]);
        }

        System.out.print("Select Cinema Class to Configure: ");
        while(!sc.hasNextInt())
            System.out.println("Please enter a valid option.");

        int classOption = sc.nextInt();
        String selectedClass = CinemaClass.values()[classOption-1].toString();

        System.out.print("Enter New Price: ");
        while(!sc.hasNextInt())
            System.out.println("Please enter a valid option.");

        int classPrice = sc.nextInt();

        //FileReader.replaceLine(root + "cinema_class_ticket.txt", "Booking", "Booking," + bookingFee, false);

        FileReader.writeFile(root + "cinema_class_ticket.txt", selectedClass + ", " + classPrice, true);

        runInterface();
    }

    // (4) Configure Ticket Type
    public void configureTicketType() {
        System.out.println("------- CONFIGURE TICKET TYPE PRICING -------\n");

        // Display all ticket types
        for(int i=0; i < TicketType.values().length; i++) {
            System.out.println((i+1) + ") " + TicketType.values()[i]);
        }

        // Get Ticket Type
        System.out.println("\nSelect Ticket Type to Configure: ");
        while(!sc.hasNextInt())
            System.out.println("Please enter a valid option.");



    }

    // (5) Configure Day of Week
    public void configureDays() {
        System.out.println("------- CONFIGURE DAY PRICING -------\n");
    }
}

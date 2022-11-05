package Admin;

import Entities.CinemaClass;
import Entities.DayType;
import Entities.MovieType;
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
                configureDays();
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

                while(!sc.hasNextFloat())
                    System.out.println("Please enter a valid price.");

                float standardPrice = sc.nextFloat();

                String data = "Standard," + standardPrice;

                FileReader.replaceLine(root + "standard_ticket.txt", "Standard", "Standard," + standardPrice, false);

                runInterface();

                break;
            case 2:
                // Booking Fee
                System.out.print("Enter Booking Fee: ");

                while(!sc.hasNextFloat())
                    System.out.println("Please enter a valid fee.");

                float bookingFee = sc.nextFloat();

                FileReader.replaceLine(root + "standard_ticket.txt", "Booking", "Booking," + bookingFee, false);

                runInterface();

                break;
            default:
                break;
        }
    }

    // (2) Modify Movie Type Pricing
    public void configureMovieType() {
        System.out.println("------- CONFIGURE MOVIE TYPE PRICE MODIFIER -------\n");

        // Display all movie types
        for(int i=0; i < MovieType.values().length; i++) {
            System.out.println((i+1) + ") " + MovieType.values()[i]);
        }

        // Get Movie Type to Change
        System.out.print("Enter Movie Type to Configure: ");

        while(!sc.hasNextInt())
            System.out.println("Please enter a valid option.");

        String selectedType = MovieType.values()[sc.nextInt()-1].toString();

        // Get New Price
        System.out.print("Enter New Price Modifier: ");
        while(!sc.hasNextFloat())
            System.out.println("Please enter a valid price modifier.");

        float typeModifier = sc.nextFloat();

        FileReader.replaceLine(root + "cinema_class_ticket.txt", selectedType, selectedType + "," + typeModifier, false);

        runInterface();
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

        System.out.print("Enter New Price Modifier: ");
        while(!sc.hasNextFloat())
            System.out.println("Please enter a valid price modifier.");

        float classPrice = sc.nextFloat();

        FileReader.replaceLine(root + "cinema_class_price.txt", selectedClass, selectedClass + "," + classPrice, false);
        //FileReader.writeFile(root + "cinema_class_ticket.txt", selectedClass + ", " + classPrice, true);

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

        String selectedTicket = TicketType.values()[sc.nextInt()-1].toString();

        System.out.print("Enter New Price Modifier: ");
        while(!sc.hasNextFloat())
            System.out.println("Please enter a valid price modifier.");

        float ticketTypePrice = sc.nextFloat();

        FileReader.replaceLine(root + "ticket_type_price.txt", selectedTicket, selectedTicket + "," + ticketTypePrice, false);

        runInterface();
    }

    // (5) Configure Day of Week
    public void configureDays() {
        System.out.println("------- CONFIGURE DAY PRICING -------\n");

        // Display all ticket types
        for(int i = 0; i < DayType.values().length; i++) {
            System.out.println((i+1) + ") " + DayType.values()[i]);
        }

        // Get Ticket Type
        System.out.println("\nSelect Day Type to Configure: ");
        while(!sc.hasNextInt())
            System.out.println("Please enter a valid option.");

        String selectedDay = DayType.values()[sc.nextInt()-1].toString();

        System.out.print("Enter New Price Modifier: ");
        while(!sc.hasNextFloat())
            System.out.println("Please enter a valid price modifier.");

        float dayPrice = sc.nextFloat();

        FileReader.replaceLine(root + "day_type_price.txt", selectedDay, selectedDay + "," + dayPrice, false);

        runInterface();
    }
}
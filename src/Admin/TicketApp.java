package Admin;

import Util.FileReader;

import java.util.Scanner;

public class TicketApp extends AppInterface {
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir");

    public TicketApp(AppInterface prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
        System.out.println("------- CONFIGURE TICKET PRICING -------\n");

        System.out.println("1) Movie Type (e.g., 3D, blockbuster)");
        System.out.println("2) Cinema Class (e.g., Platinum)");
        System.out.println("3) Ticket Type (e.g., Adult)");
        System.out.println("4) Day of the Week/Holiday");
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
                // Movie Type
                configureMovieType();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    // Modify Movie Type Pricing
    public void configureMovieType() {
        System.out.println("------- CONFIGURE MOVIE TYPE PRICING -------\n");

        FileReader.readFile(root + "\\data\\ticket_settings\\ticket_type.txt");

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
}

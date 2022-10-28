package Admin;

import java.util.Scanner;

public class CineplexApp extends AppInterface {
    Scanner sc = new Scanner(System.in);

    public CineplexApp(AppInterface prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
        System.out.println("------- CONFIGURE SYSTEM SETTINGS -------\n");

        System.out.println("1) Configure Cineplex Outlets");
        System.out.println("3) Configure Ticket Pricing");
        System.out.println("4) Configure Holidays");
        System.out.println("\n0) Return to Previous Menu");

        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        switch(input) {
            case 0:
                goBack().runInterface();
                break;
            case 1:
                // Configure Cineplex Outlets

                break;
            case 2:
                // Configure Ticket Price

                break;
            case 3:
                // Configure Holidays

                break;
            default:
                break;
        }
    }
}

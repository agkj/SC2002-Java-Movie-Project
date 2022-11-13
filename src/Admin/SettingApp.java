package Admin;

import Util.AppHelper;

import javax.swing.text.View;
import java.util.Scanner;

/**
 * [Admin Module] Configure Settings App.
 * Allow admin users to configure cineplex outlets, ticket pricing, public holidays and viewing of top 5 movies for movie-goers.
 */
public class SettingApp extends AppHelper {
    Scanner sc = new Scanner(System.in);

    public SettingApp(AppHelper prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
    	System.out.println("==========================================================");
        System.out.println("\t\t CONFIGURE SYSTEM SETTINGS ");
        System.out.println("==========================================================");

        System.out.println("1) Configure Cineplex Outlets");
        System.out.println("2) Configure Ticket Pricing");
        System.out.println("3) Configure Holidays");
        System.out.println("4) Configure Top Five for Moviegoer");
        System.out.println("\n0) Return to Previous Menu");
        System.out.println("==========================================================");
        System.out.println("Select an option: ");

        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        switch(input) {
            case 0:
                goBack().runInterface();
                break;
            case 1:
                // Configure Cineplex Outlets
                CineplexApp cineplexApp = new CineplexApp(this);
                cineplexApp.runInterface();
                
                break;
            case 2:
                // Configure Ticket Price
                TicketPricingApp ticketApp = new TicketPricingApp(this);
                ticketApp.runInterface();

                break;
            case 3:
                // Configure Holidays
                HolidayApp holidayApp = new HolidayApp(this);
                holidayApp.runInterface();

                break;

            case 4:
                // Configure view in Moviergoer
                TopFiveApp topFiveStatus = new TopFiveApp(this);
                //ViewtopfiveApp topfiveStatus = new ViewtopfiveApp(this);
                topFiveStatus.runInterface();
                break;

            default:
                break;
        }
    }
}

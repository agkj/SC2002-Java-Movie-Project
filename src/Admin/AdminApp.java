package Admin;

import Util.AppHelper;

import java.util.Scanner;

/**
 * [Admin Module] Admin Starter App containing list of Admin Functions.
 * Allow Admin users to manage the movie listings, cinema showtimes and system settings (i.e., ticket pricing, holidays)
 */

public class AdminApp extends AppHelper {
    Scanner sc = new Scanner(System.in);

    public AdminApp(AppHelper prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
        System.out.println("|--------------------------------------------------------|");
        System.out.println("| \t\tWelcome to Admin Module \t\t |");
        System.out.println("|--------------------------------------------------------|");

        System.out.println("| 1) Manage Movie Listing \t\t\t\t");
        System.out.println("| 2) Manage Cinema Showtimes \t\t\t\t");
        System.out.println("| 3) Configure System Settings \t\t\t\t");
        System.out.println("| 0) Logout \t\t\t\t\t\t |");

    //    while(!sc.hasNextInt()) {
        	System.out.println("|--------------------------------------------------------|");
            System.out.println("Select an option :");
     //       sc.next();
    //    }

        int input = sc.nextInt();

        switch(input) {
            case 0:
                //goBack().runInterface();
                break;
            case 1:
                // Manage Movie Listing
                MovieListingApp ml = new MovieListingApp(this);
                ml.runInterface();

                break;
            case 2:
                // Manage Showtime
                ShowtimeApp showtime = new ShowtimeApp(this);
                showtime.runInterface();

                break;
            case 3:
                // Configure system settings
                SettingApp settings = new SettingApp(this);
                settings.runInterface();

                break;
            default:
                break;
        }
    }
}

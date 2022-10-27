package Admin;

import java.util.Scanner;

public class AdminApp {

    Scanner sc = new Scanner(System.in);

    public AdminApp() {}

    public void startAdminApp() {
        System.out.println("------------------");
        System.out.println("Welcome to Admin Module");

        /*
        do {
            System.out.println("1) Manage Movie Listing");
            System.out.println("2) Manage Cinema Showtimes");
            System.out.println("3) Configure System Settings");

            input = sc.nextInt();
        } while (input > 0);
         */

        System.out.println("1) Manage Movie Listing");
        System.out.println("2) Manage Cinema Showtimes");
        System.out.println("3) Configure System Settings");

        int input = sc.nextInt();

        switch(input) {
            case 1:
                // Manage Movie Listing
                MovieListingApp ml = new MovieListingApp();
                ml.runInterface();
                //sc.close();

                break;
            case 2:
                // Manage Showtime
                System.out.println("1) Create Showtime Listing");
                System.out.println("2) View Showtime Listings");
                System.out.println("3) Update Showtime Listening");
                System.out.println("3) Delete Showtime Listening");

                break;
            case 3:
                // Configure system settings
                break;
            default:
                break;
        }
    }
}

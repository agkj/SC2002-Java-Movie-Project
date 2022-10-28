package Admin;

import java.util.Scanner;

public class AdminApp extends AppInterface {

    Scanner sc = new Scanner(System.in);

    public AdminApp(AppInterface prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
        System.out.println("------------------");
        System.out.println("Welcome to Admin Module");

        System.out.println("1) Manage Movie Listing");
        System.out.println("2) Manage Cinema Showtimes");
        System.out.println("3) Configure System Settings");
        System.out.println("\n0) Logout");

        int input = sc.nextInt();

        switch(input) {
            case 0:
                goBack().runInterface();
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

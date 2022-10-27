package Admin;

import Entities.Movie;
import Entities.ShowTime;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowtimeApp implements AppInterface {
    Scanner sc = new Scanner(System.in);
    ArrayList<Movie> movieList;

    Path currentRelativePath = Paths.get("");
    String root = currentRelativePath.toAbsolutePath().toString();

    public ShowtimeApp() {}

    @Override
    public void runInterface() {
        System.out.println("------- MANAGE SHOWTIME LISTINGS -------\n");

        System.out.println("1) Create Showtime Listing");
        System.out.println("2) View Showtime Listings");
        System.out.println("3) Update Showtime Listening");
        System.out.println("4) Delete Showtime Listening");

        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        switch(input) {
            case 1:
                // Create Listing

                break;
            case 2:
                // View Listings

                break;
            case 3:
                // Update Listing

                break;
            case 4:
                // Delete Listing

                break;
            default:
                break;
        }
    }

    //// (1) CREATE LISTING
    public void createShowtime() {
        System.out.println("------- CREATE SHOWTIME LISTING -------\n");

        ShowTime newShowTime = new ShowTime();
    }

    //// (2) VIEW LISTING
    public void viewShowtime() {
        System.out.println("------- VIEW SHOWTIME LISTING -------\n");
    }

    //// (3) UPDATE LISTING
    public void updateShowtime() {
        System.out.println("------- UPDATE SHOWTIME LISTING -------\n");
    }

    //// (4) DELETE LISTING
    public void deleteShowtime() {
        System.out.println("------- DELETE SHOWTIME LISTING -------\n");
    }
}

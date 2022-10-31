package Admin;

import Entities.*;
import Util.Serializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class CineplexApp extends AppInterface {
    Scanner sc = new Scanner(System.in);

    Path currentRelativePath = Paths.get("");
    String root = currentRelativePath.toAbsolutePath().toString();

    public CineplexApp(AppInterface prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
        System.out.println("------- CONFIGURE CINEPLEX OUTLETS -------\n");

        System.out.println("1) Create New Outlet");
        System.out.println("2) Create New Cinema");

        System.out.println("\n0) Return to Previous Menu");

        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        switch(input) {
            case 0:
                goBack().runInterface();
                break;
            case 1:
                // Create new Outlet
                System.out.println("------- CREATE NEW OUTLET -------\n");

                Cineplex outlet = new Cineplex();

                //// CINEPLEX ID
                System.out.print("Enter Cineplex ID: ");
                String id = sc.nextLine();

                while(id.isEmpty()) {
                    id = sc.nextLine();

                    if(id.isEmpty())
                        System.out.println("Please enter a title.");
                }

                outlet.setCineplexID(id);                           // Set Value

                //// VENUE
                System.out.print("Enter Venue: ");
                String venue = sc.nextLine();

                while(venue.isEmpty()) {
                    venue = sc.nextLine();

                    if(venue.isEmpty())
                        System.out.println("Please enter a venue.");
                }

                outlet.setVenue(venue);

                //// VENUE
                System.out.print("Enter Address: ");
                String addr = sc.nextLine();

                while(addr.isEmpty()) {
                    addr = sc.nextLine();

                    if(addr.isEmpty())
                        System.out.println("Please enter an address.");
                }

                outlet.setAddress(addr);

                //// FIN, Show Cineplex Created
                try {
                    Serializer.serialize(root+"\\data\\cineplex\\"+outlet.getCineplexID()+".dat", outlet);

                    System.out.println("\n------- SUCCESS: CREATED NEW OUTLET -------\n");
                    System.out.println(outlet);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                runInterface();
                break;
            case 2:
                // Create new Cinema
                System.out.println("------- CREATE NEW CINEMA -------\n");

                Cineplex selectedCineplex;

                try {
                    File path = new File(System.getProperty("user.dir") + "\\data\\cineplex");

                    File[] files = path.listFiles();

                    // Read all available Cineplex created
                    if(files != null) {
                        for(int i=0; i < files.length; i++) {
                            Cineplex curr = (Cineplex) Serializer.deSerialize(path + "\\" + files[i].getName());
                            System.out.println((i+1) + ") " + curr.getVenue());
                        }

                        System.out.print("\nSelect Cineplex (to add cinema to): ");

                        // Get selected Cineplex file and object
                        int selected = sc.nextInt();
                        selectedCineplex = (Cineplex) Serializer.deSerialize(path + "\\" + files[selected-1].getName());

                        System.out.println("------- " + selectedCineplex.getVenue().toUpperCase() + ": ADD CINEMA -------\n");

                        // Create Cinema object
                        Cinema newCinema = new Cinema();

                        //// SET CINEMA CLASS
                        for(int i = 0; i < CinemaClass.values().length; i++)
                            System.out.println(i+1 + ") " + CinemaClass.values()[i]);

                        int cinemaClass = sc.nextInt();

                        while(cinemaClass < 1 || cinemaClass > CinemaClass.values().length) {
                            System.out.println("Please enter a valid cinema class option.");
                        }

                        newCinema.setCinemaClass(CinemaClass.values()[cinemaClass-1]);

                        //// SET NUM OF SEATS
                        /*
                        System.out.print("Enter Total (Max) Seats Available: ");
                        while(!sc.hasNextInt())
                            System.out.println("Please enter a valid number of seats.");
                        newCinema.setNumOfSeats(sc.nextInt());
                         */

                        //// SET LAYOUT
                        System.out.print("Enter number of rows: ");
                        while(!sc.hasNextInt())
                            System.out.println("Please enter a valid number of rows.");
                        int rows = sc.nextInt();

                        System.out.print("Enter number of columns: ");
                        while(!sc.hasNextInt())
                            System.out.println("Please enter a valid number of columns.");
                        int cols = sc.nextInt();

                        Seat[][] seats = new Seat[rows][cols];

                        // Initialise entire cinema to be vacant seats first
                        char seatAlpha = 65;
                        for(int i=0; i < rows; i++) {
                            for(int j=0; j < cols; j++) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(seatAlpha);
                                sb.append(j+1);

                                String seatNum = sb.toString();                     // output e.g, A1
                                seats[i][j] = new Seat(seatNum, 0);
                            }

                            seatAlpha++;
                        }

                        boolean runArrangement = false;

                        int seatInput = 0;

                        while(runArrangement); {
                            runArrangement = true;
                            char rowNum = 65;   // start at A (65), ends at Z (90)

                            // Print Layout
                            for(int i=0; i < rows; i++) {
                                System.out.print(rowNum++ + " | ");

                                for(int j=0; j < cols; j++) {
                                    // Print seat status
                                    System.out.print(" ["+ seats[i][j].getSeatStatus() +"] ");
                                }

                                System.out.print("\n");
                            }

                            System.out.print("Enter seat number followed by arrangement (-1: Aisle, 0: Vacant): ");

                            String seatNum = sc.next();
                            seatInput = sc.nextInt();

                            if(seatInput != -2) {


                                break;
                            } else {
                                // Modify Seating
                                for(int i=0; i < rows; i++) {
                                    for(int j=0; j < cols; j++) {
                                        if(seats[i][j].getSeatNum().equals(seatNum)) {
                                            seats[i][j].setSeatStatus(seatInput);
                                        }
                                    }
                                }
                            }
                        }

                        newCinema.setLayout(seats);

                        // Update Cinema list for selected Cineplex
                        selectedCineplex.addCinema(newCinema);

                        try {
                            Serializer.serialize(root + "\\data\\cineplex\\" + files[selected-1].getName(), selectedCineplex); // files[selected-1].getName() is the selected cineplex

                            System.out.println("\n------- SUCCESS: CREATED NEW CINEMA -------\n");
                            newCinema.showLayout();
                        } catch (IOException e) {
                            System.out.println("\n------- ERROR: PLEASE TRY AGAIN -------\n");
                            e.printStackTrace();
                        }

                    } // end if files != null


                } catch (Exception e) {
                    e.printStackTrace();
                }

                runInterface();
                break;
            default:
                runInterface();
                break;
        }
    }
}
package Admin;

import Entities.*;
import Util.AppHelper;
import Util.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * [Admin Module] Cineplex App under Configure System Settings.
 * Allow admin users to create a new cineplex outlet, new cinemas (under a particular outlet), view cinemas and delete existing cinemas.
 */

public class CineplexApp extends AppHelper {

    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir");

    File path;
    File[] files;
    boolean runArrangement;

    public CineplexApp(AppHelper prevApp) {
        super(prevApp);

        this.load();
    }

    /**
     Load Cineplex Files
     */
    public void load() {
        path = new File(root + "\\data\\cineplex");
        files = path.listFiles();
    }

    @Override
    public void runInterface() {
    	System.out.println("==========================================================");
        System.out.println("\t\t CONFIGURE CINEPLEX OUTLETS");
        System.out.println("==========================================================");

        System.out.println("1) Create New Outlet");
        System.out.println("2) Create Cinema");
        System.out.println("3) View Cinema");
        System.out.println("4) Delete Cinema");

        System.out.println("\n0) Return to Previous Menu");
        System.out.println("==========================================================");
        System.out.println("Select an option: ");
        while(!sc.hasNextInt())
            System.out.println("Please enter a valid input");

        int input = sc.nextInt();

        Cineplex selectedCineplex;

        switch(input) {
            case 0:
                goBack().runInterface();
                break;
            case 1:
                // Create new Outlet
            	System.out.println("==========================================================");
                System.out.println("\t\t CREATE NEW OUTLET ");
                System.out.println("==========================================================");
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
                    this.load();
                    
                    System.out.println("\t\t SUCCESS: CREATED NEW OUTLET\t\t ");
                    System.out.println("==========================================================");
                    System.out.println(outlet);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                runInterface();
                break;
            case 2:
                // Create new Cinema
            	System.out.println("==========================================================");
                System.out.println("\t\t CREATE NEW CINEMA ");
                System.out.println("==========================================================");

                try {
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

                        //// SET CINEMA ID
                        System.out.print("Enter Cinema Hall ID: ");
                        String hallId = sc.nextLine();

                        while(hallId.isEmpty()) {
                            hallId = sc.nextLine();

                            if(hallId.isEmpty())
                                System.out.println("Please enter a hall ID.");
                        }

                        String cinemaID = selectedCineplex.getCineplexID() + "_" + hallId;        // e.g. CC_JEM_A
                        newCinema.setCinemaID(cinemaID);

                        //// SET CINEMA CLASS
                        System.out.println("==========================================================");
                        System.out.println(" t\t CINEMA CLASS ");
                        System.out.println("==========================================================");
                        for(int i = 0; i < CinemaClass.values().length; i++)
                            System.out.println(i+1 + ") " + CinemaClass.values()[i]);
                        System.out.print("");
                        System.out.print("Select a cinema class option: ");

                        int cinemaClass = sc.nextInt();

                        while(cinemaClass < 1 || cinemaClass > CinemaClass.values().length) {
                            System.out.println("Please enter a valid cinema class option.");
                        }

                        newCinema.setCinemaClass(CinemaClass.values()[cinemaClass-1]);

                        //// SET LAYOUT
                        System.out.println("==========================================================");
                        System.out.println("\t\t SET LAYOUT OF CINEMA");
                        System.out.println("==========================================================");
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

                        runArrangement = true;
                        int seatInput = 0;

                        do {
                            char rowNum = 65;   // start at A (65), ends at Z (90)

                            // Display Layout
                            System.out.println("\nCinema Layout: ");
                            System.out.println();

                            // Print Screen
                            System.out.print("  ");
                            for(int i=0; i < cols; ++i) {
                                System.out.print("--");
                            }
                            System.out.print("SCREEN");
                            for(int i=0; i < cols; ++i) {
                                System.out.print("--");
                            }
                            System.out.println();

                            // Print Seat Layout
                            System.out.print("  |  ");
                            for(int k=1; k <= cols; k++) {
                                // Print seat status
                                System.out.print("  "+ k +"  ");
                            }
                            System.out.println("");
                            for(int i=0; i < rows; i++) {
                                System.out.print(rowNum++ + " | ");

                                for(int j=0; j < cols; j++) {
                                    // Print seat status
                                    System.out.print(" ["+ seats[i][j].getSeatStatus() +"] ");
                                }

                                System.out.print("\n");
                            }

                            System.out.print("Enter seat number followed by arrangement (0: Vacant, -1: Aisle, ,-2: Save): ");

                            String seatNum = sc.next();

                            if(seatNum.equals("-2")) {
                                runArrangement = false;
                                break;
                            } else {
                                seatInput = sc.nextInt();

                                // Modify Seating
                                for(int i=0; i < rows; i++) {
                                    for(int j=0; j < cols; j++) {
                                        if(seats[i][j].getSeatNum().equals(seatNum)) {
                                            seats[i][j].setSeatStatus(seatInput);
                                        }
                                    }
                                }
                            }

                        } while(runArrangement);

                        newCinema.setLayout(seats);

                        // Get number of seats
                        int numOfSeats = 0;
                        for(int i=0; i < seats.length; i++) {
                            for(int j=0; j < seats[0].length; j++) {
                                if(seats[i][j].getSeatStatus() == 0)
                                    numOfSeats++;
                            }
                        }

                        newCinema.setNumOfSeats(numOfSeats);

                        // Update Cinema list for selected Cineplex
                        System.out.println(selectedCineplex);
                        selectedCineplex.addCinema(newCinema);

                        try {
                            // Update List of Cinemas for Cineplex
                            Serializer.serialize(root + "\\data\\cineplex\\" + files[selected-1].getName(), selectedCineplex); // files[selected-1].getName() is the selected cineplex

                            // Create new Cinema data file
                            Serializer.serialize(root + "\\data\\cinema\\" + newCinema.getCinemaID() + ".dat", newCinema);
                            
                            System.out.println("\t\t SUCCESS: CREATED NEW CINEMA");
                            System.out.println("==========================================================");
                            newCinema.showLayout();
                        } catch (IOException e) {
                            System.out.println("\t\t ERROR: PLEASE TRY AGAIN");
                            System.out.println("==========================================================");
                            e.printStackTrace();
                        }

                    } // end if files != null


                } catch (Exception e) {
                    e.printStackTrace();
                }

                runInterface();
                break;
            case 3:
                // View cinemas
            	System.out.println("==========================================================");
                System.out.println("\t\t VIEW CINEMA BY CINEPLEX");
                System.out.println("==========================================================");

                try {
                    // Read all available Cineplex created
                    if (files != null) {
                        for (int i = 0; i < files.length; i++) {
                            Cineplex curr = (Cineplex) Serializer.deSerialize(path + "\\" + files[i].getName());
                            System.out.println((i + 1) + ") " + curr.getVenue());
                        }

                        System.out.print("\nSelect Cineplex: ");

                        // Get selected Cineplex file and object
                        int selected = sc.nextInt();
                        selectedCineplex = (Cineplex) Serializer.deSerialize(path + "\\" + files[selected - 1].getName());

                        ArrayList<Cinema> cinemas = selectedCineplex.getListOfCinemas();

                        for(int i=0; i < cinemas.size(); i++) {
                            System.out.print((i+1) + ") " + cinemas.get(i) + "\n");
                            cinemas.get(i).showLayout();

                            System.out.println();
                        }
                    }

                    runInterface();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                // Delete Cinema
            	System.out.println("==========================================================");
                System.out.println("\t\t DELETE CINEMA FROM CINEPLEX ");
                System.out.println("==========================================================");

                try {
                    // Read all available Cineplex created
                    if (files != null) {
                        for (int i = 0; i < files.length; i++) {
                            Cineplex curr = (Cineplex) Serializer.deSerialize(path + "\\" + files[i].getName());
                            System.out.println((i + 1) + ") " + curr.getVenue());
                        }

                        System.out.print("\nSelect Cineplex: ");

                        // Get selected Cineplex file and object
                        int selected = sc.nextInt();
                        selectedCineplex = (Cineplex) Serializer.deSerialize(path + "\\" + files[selected - 1].getName());

                        System.out.println("------- " + selectedCineplex.getVenue().toUpperCase() + ": DELETE CINEMA -------\n");


                        // Show all available cinemas under selected cineplex
                        ArrayList<Cinema> cinemas = selectedCineplex.getListOfCinemas();

                        for(int i=0; i < cinemas.size(); i++) {
                            Cinema curr = cinemas.get(i);

                            System.out.println((i+1) + ") " + curr.getCinemaID() + " (" + curr.getCinemaClass() + ")");
                        }

                        System.out.print("\nEnter Cinema ID to delete: ");

                        while(!sc.hasNextInt())
                            System.out.println("Please enter a cinema ID.");

                        int idToDelete = sc.nextInt()-1;
                        Cinema cinemaToDelete = cinemas.get(idToDelete);

                        // Remove from ArrayList
                        cinemas.remove(idToDelete);

                        // Try to save respective Cineplex and Cinema files
                        try {
                            // Delete Cinema data file
                            File fileToDelete = new File(root + "\\data\\cinema\\" + cinemaToDelete.getCinemaID() + ".dat");
                            if(fileToDelete.delete()) {
                                // Update List of Cinemas for Cineplex
                                Serializer.serialize(root + "\\data\\cineplex\\" + files[selected-1].getName(), selectedCineplex); // files[selected-1].getName() is the selected cineplex

                                System.out.println("\t\t SUCCESSFULLY DELETED CINEMA \t\t");
                                System.out.println("==========================================================");

                                // Reload
                                this.load();
                            } else {
                                System.out.println("\t\t FAILED TO DELETE CINEMA ");
                                System.out.println("==========================================================");
                            }

                            runInterface();

                        } catch (IOException e) {
                            System.out.println("\t\t ERROR: PLEASE TRY AGAIN -------\n");
                            System.out.println("==========================================================");
                            runInterface();

                            e.printStackTrace();
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                break;
            default:
                runInterface();
                break;
        }
    }
}

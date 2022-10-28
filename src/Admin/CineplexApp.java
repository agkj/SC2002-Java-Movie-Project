package Admin;

import Entities.Cineplex;
import Util.Serializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

                try {
                    File path = new File(System.getProperty("user.dir") + "\\data\\cineplex");

                    File[] files = path.listFiles();

                    // Read all available Cineplex created
                    if(files != null) {
                        for(int i=0; i < files.length; i++) {
                            Cineplex curr = (Cineplex) Serializer.deSerialize(path + "\\" + files[i].getName());
                            System.out.println((i+1) + ") " + curr.getVenue());
                        }
                    }

                    // Select Cineplex
                    System.out.print("Select Cineplex (to add cinema to): ");
                    int selectedCineplex = sc.nextInt();

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

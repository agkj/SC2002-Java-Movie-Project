package Admin;

import Entities.Holiday;
import Util.Serializer;
import Util.FileReader;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class HolidayApp extends AppInterface {
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir");

    //**** Let Holiday data be stored in a .txt file

    public HolidayApp(AppInterface prevApp) {
        super(prevApp);

    }

    @Override
    public void runInterface() {
        System.out.println("------- CONFIGURE CINEPLEX OUTLETS -------\n");

        System.out.println("1) Create New Holiday");
        //System.out.println("2) Update Holiday");
        System.out.println("2) Delete Holiday");

        System.out.println("\n0) Return to Previous Menu");

        int input = sc.nextInt();

        do {
            switch(input) {
                case 0:
                    goBack().runInterface();
                    break;
                case 1:
                    // Create new holiday
                    createHoliday();
                    break;
                    /*
                case 2:
                    // Update existing holidays
                    break;

                     */
                case 2:
                    // Delete existing holidays
                    deleteHoliday();
                    break;
                default:
                    runInterface();

            }
            input = sc.nextInt();
        } while (input > 0);
    }

    //// (1) CREATE HOLIDAY
    public void createHoliday() {
        System.out.println("------- CREATE HOLIDAY -------\n");

        Holiday newHoliday = new Holiday();

        //// HOLIDAY NAME
        System.out.print("Enter Name of Holiday: ");
        String holidayName = sc.nextLine();

        while(holidayName.isEmpty()) {
            holidayName = sc.nextLine();

            if(holidayName.isEmpty())
                System.out.println("Please enter a holiday name.");
        }

        newHoliday.setHolidayName(holidayName);

        //// HOLIDAY DATE
        boolean dateValid = false;
        String date;
        LocalDate holidayDate;

        while(!dateValid) {
            System.out.print("Enter Date of Holiday (in YYYY-MM-DD e.g., 2022-10-24): ");
            date = sc.nextLine();

            try {
                holidayDate = LocalDate.parse(date);
                dateValid = true;

                newHoliday.setHolidayDate(holidayDate);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid showtime.");
            }
        }

        //// SAVE HOLIDAY
        writeTXT(root + "\\data\\holiday\\holidays.txt", newHoliday.getHolidayName(), false);
        writeTXT(root + "\\data\\holiday\\holidays.txt", newHoliday.getHolidayDate().toString(), true);

        System.out.println("\n------- SUCCESS: CREATED NEW HOLIDAY -------\n");

        // Go back to holiday menu
        runInterface();
    }

    public static void writeTXT(String filePath, String data, boolean endLine) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(filePath, true); // true is used so that previous data is not overwritten
            BufferedWriter out = new BufferedWriter(fw);

            out.append(data);

            if(endLine)
                out.append("\n");
            else
                out.append(",");

            out.close(); // close BufferedWriter
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //// (2) DELETE HOLIDAY
    public void deleteHoliday() {
        System.out.println("------- DELETE HOLIDAY -------\n");

        // Read and display existing holidays
        FileReader.readFile(root + "\\data\\holiday\\holidays.txt");

        System.out.println("Enter Holiday Name to Delete");
        String holidayName = sc.nextLine();

        while(holidayName.isEmpty()) {
            holidayName = sc.nextLine();

            if(holidayName.isEmpty())
                System.out.println("Please enter a holiday name to delete.");
        }

        //removeLine(root + "\\data\\holiday\\holidays.txt", holidayName, true);

        System.out.println("\n------- SUCCESS: DELETE HOLIDAY -------\n");

        runInterface();
    }

    /*
    public static void removeLine(String filePath, String lineToRemove, boolean containsFlag) {
        // containsFlag checks if the string passed in lineToRemove is a substring

        String line;
        lineToRemove = lineToRemove.toLowerCase();

        File file = new File(filePath);
        File tempFile = new File("tempFile.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));

            System.out.println("trying to delete " + lineToRemove);

            // File Reader(*Where you have installed the CSV file)
            while ((line = br.readLine()) != null) { // Read till end of data
                String trimmedLine = line.trim();   // Line in file to compare

                if(containsFlag) {
                    System.out.println("comparing with " + trimmedLine.toLowerCase());

                    if(trimmedLine.toLowerCase().contains(lineToRemove)) {
                        System.out.println("detected");
                        continue;
                    }

                    out.write(line + System.getProperty("line.separator"));

                }
            }
            br.close(); // close BufferedReader
            out.close();

            // Replace old file with new temp file (with removed value)
            file.delete();
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}

package Admin;

import Entities.Holiday;
import Util.AppHelper;
import Util.FileReader;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HolidayApp extends AppHelper {
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir");

    public HolidayApp(AppHelper prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
        System.out.println("-------- CONFIGURE HOLIDAY SETTING -------\n");

        System.out.println("1) Create New Holiday");
        System.out.println("2) Delete Holiday");

        System.out.println("\n0) Return to Previous Menu");
        System.out.println("------------------------------------------");
        System.out.println("Select an option: ");
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
        FileReader.writeFile(root + "\\data\\holiday\\holidays.txt", newHoliday.getHolidayName(), false);
        FileReader.writeFile(root + "\\data\\holiday\\holidays.txt", newHoliday.getHolidayDate().toString(), true);

        System.out.println("\n------- SUCCESS: CREATED NEW HOLIDAY -------\n");

        // Go back to holiday menu
        runInterface();
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

        FileReader.deleteLine(root + "\\data\\holiday\\holidays.txt", holidayName, true);

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

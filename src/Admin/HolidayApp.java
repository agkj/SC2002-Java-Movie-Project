package Admin;

import Entities.Holiday;
import Util.AppHelper;
import Util.FileReader;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * [Admin Module] Holiday App under Configure System Settings.
 * Allow admin users to create new public holidays and delete existing ones.
 */

public class HolidayApp extends AppHelper {
    Scanner sc = new Scanner(System.in);

    String root = System.getProperty("user.dir");

    public HolidayApp(AppHelper prevApp) {
        super(prevApp);
    }

    @Override
    public void runInterface() {
    	System.out.println("==========================================================");
        System.out.println("\t\t CONFIGURE HOLIDAY SETTING ");
        System.out.println("==========================================================");

        System.out.println("1) Create New Holiday");
        System.out.println("2) Delete Holiday");

        System.out.println("\n0) Return to Previous Menu");
        System.out.println("==========================================================");
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

    /**
     * Create new public holiday.
     */
    public void createHoliday() {
    	System.out.println("==========================================================");
        System.out.println("\t\t CREATE HOLIDAY -------\n");
        System.out.println("==========================================================");

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

        System.out.println("\t\t SUCCESS: CREATED NEW HOLIDAY");
        System.out.println("==========================================================");

        // Go back to holiday menu
        runInterface();
    }

    /**
     * Delete existing public holiday.
     */
    //// (2) DELETE HOLIDAY
    public void deleteHoliday() {
    	System.out.println("==========================================================");
        System.out.println("DELETE HOLIDAY ");
        System.out.println("==========================================================");

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

        System.out.println("\t\t SUCCESS: DELETE HOLIDAY ");
        System.out.println("==========================================================");

        runInterface();
    }
}

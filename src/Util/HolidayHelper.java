package Util;

import Entities.Holiday;

import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class HolidayHelper {
    String filePath = System.getProperty("user.dir") + "\\data\\holiday\\holidays.txt";
    File path = new File(filePath);

    // Constructor
    public HolidayHelper() { }

    public ArrayList<Holiday> getHolidays() {
        ArrayList<Holiday> listOfHolidays = new ArrayList<Holiday>();

        StringBuffer holidayBuffer = FileReader.copyFile(filePath);
        String[] holidayStrings = holidayBuffer.toString().split("\n");

        for(int i=0; i < holidayStrings.length; i++) {
            Holiday holiday = new Holiday();

            String[] currLine = holidayStrings[i].split(",");

            String name = currLine[0].trim();
            String date = currLine[1].trim();

            try {
                // Set Name
                holiday.setHolidayName(name);

                // Set Date
                LocalDate holidayDate = LocalDate.parse(date);
                holiday.setHolidayDate(holidayDate);

                // Add into arraylist
                listOfHolidays.add(holiday);
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing localdate.");
            }
        }

        return listOfHolidays;
    }
}

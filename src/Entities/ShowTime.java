package Entities;

import Util.HolidayHelper;

import java.io.Serial;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a movie showtime.
 * A showtime is shown at one cinema.
 */
public class ShowTime implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    /**
     * Unique ID of the movie showtime
     */
    private String showtimeID;
    /**
     * Cinema ID that the movie showtime is showing at.
     */
    private String cinemaID;
    /**
     * Cineplex ID that the movie showtime is showing at.
     */
    private String cineplexID;
    /**
     * Date and Time of the movie showtime.
     */
    private LocalDateTime showDateTime;
    /**
     * Status of the movie showtime (i.e., Available, Sold Out).
     */
    private ShowTimeStatus showTimeStatus;
    /**
     * Available seats of the movie showtime.
     */
    private Seat[][] showTimeLayout;

    private int numOfAvailSeats;

    public ShowTime() {
        this.showTimeStatus = ShowTimeStatus.Available;
    }

    public String getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(String cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getShowtimeID() {
        return showtimeID;
    }

    public void setShowtimeID(String showtimeID) {
        this.showtimeID = showtimeID;
    }

    public String getCineplexID() {
        return cineplexID;
    }

    public void setCineplexID(String cineplexID) {
        this.cineplexID = cineplexID;
    }

    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    /**
     * Check the Day Type of the movie showtime (E.g., falls on a weekend, public holiday, etc.).
     * @return Returns the DayType (E.g., WEEKEND, HOLIDAY).
     */
    // Determine Day Type based on ShowDateTime
    public DayType checkDayType() {
        DayOfWeek day = showDateTime.getDayOfWeek();
        int hour = showDateTime.getHour();
        DayType dayType = null;

        HolidayHelper holidayHelper = new HolidayHelper();
        ArrayList<Holiday> listOfHolidays = holidayHelper.getHolidays();

        // Check if falls on a public holiday
        for(int i=0; i < listOfHolidays.size(); i++) {
            LocalDate showTimeDate = this.showDateTime.toLocalDate();
            LocalDate holidayDate = listOfHolidays.get(i).getHolidayDate();

            if(showTimeDate.equals(holidayDate)) {
                dayType = DayType.HOLIDAY;
                return dayType;
            }
        }

        // Continue to check day of the week and time
        if(day.equals(DayOfWeek.MONDAY) || day.equals(DayOfWeek.TUESDAY) || day.equals(DayOfWeek.WEDNESDAY))
            dayType = DayType.MON_WED;
        else if(day.equals(DayOfWeek.THURSDAY))
            dayType = DayType.THU;
        else if(day.equals(DayOfWeek.FRIDAY)) {
            if(hour >= 18)
                dayType = DayType.FRI_AFTER_6;
            else
                dayType = DayType.FRI_BEFORE_6;
        } else if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY))
            dayType = DayType.WEEKEND;

        return dayType;
    }

    public ShowTimeStatus getShowTimeStatus() {
        return showTimeStatus;
    }

    public void setShowTimeStatus(ShowTimeStatus showTimeStatus) {
        this.showTimeStatus = showTimeStatus;
    }

    public Seat[][] getShowTimeLayout() {
        return showTimeLayout;
    }

    /**
     * Display the latest movie showtime seating layout
     */
    // Print Seating Layout
    public void showLayout() {
        char rowNum = 65;   // start at A (65), ends at Z (90)
        
        System.out.println("\nMovie Layout");
        
        System.out.println();
        System.out.print("  ");
        for(int i=0; i < showTimeLayout[0].length; ++i) {
        	System.out.print("--");
        }
        System.out.print("SCREEN");
        for(int i=0; i < showTimeLayout[0].length; ++i) {
        	System.out.print("--");
        }
        System.out.println();
        System.out.print("  |  ");
        for(int k=0; k < showTimeLayout[0].length; ++k) {
            // Print seat status
        	
            System.out.print("  "+ (k+1) +"  ");
        }
        System.out.println("");
        
        for(int i=0; i < showTimeLayout.length; i++) {
            System.out.print(rowNum++ + " | ");

            for(int j=0; j < showTimeLayout[0].length; j++) {       // showTimeLayout[0] can be any index, just need to get the number of cols
                // Print seat status
            	if(showTimeLayout[i][j].getSeatStatus() != -1) {
            		System.out.print(" ["+ showTimeLayout[i][j].getSeatStatus() +"] ");
            	} else {
            		System.out.print("  |  ");
            	}
            }

            System.out.print("\n");
        }
    }

    /**
     * Update/Book a seat for a movie showtime.
     * @param seatNum Seat number to book (E.g., A3).
     * @return Return result of update of seat (i.e., True of seat is vacant and successfully booked).
     */
    // Mark a seat as taken
    public boolean bookSeat(String seatNum) {
        for(int i=0; i < showTimeLayout.length; i++) {
            for(int j=0; j < showTimeLayout[0].length; j++) {       // showTimeLayout[0] can be any index, just need to get the number of cols
                if(showTimeLayout[i][j].getSeatNum().equals(seatNum))
                    if(showTimeLayout[i][j].getSeatStatus() == 0) {
                        showTimeLayout[i][j].setSeatStatus(1);

                        return true;
                    }
            }
        }

        return false;
    }

    public void setShowTimeLayout(Seat[][] showTimeLayout) {
        this.showTimeLayout = showTimeLayout;
    }

    public int getNumOfAvailSeats() {
        return numOfAvailSeats;
    }

    public void setNumOfAvailSeats(int numOfAvailSeats) {
        this.numOfAvailSeats = numOfAvailSeats;
    }
}

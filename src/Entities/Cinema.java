package Entities;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a cinema hall located at a particular cineplex.
 * A cinema can be located at only one cineplex.
 */
public class Cinema implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    /**
     * Unique ID of the cinema hall.
     */
    private String cinemaID;

    /**
     * Type of cinema class (i.e., Regular, Platinum).
     */
    private CinemaClass cinemaClass;
    /**
     * Total number of seats available at the cinema hall.
     */
    private int numOfSeats;
    /**
     * Seating layout of the cinema hall.
     */
    private Seat[][] layout;

    public Cinema() {}

    public Cinema(String cinemaID, CinemaClass cinemaClass, int seats, Seat[][] layout) {
        this.cinemaID = cinemaID;
        this.cinemaClass = cinemaClass;
        this.numOfSeats = seats;
        this.layout = layout;
    }

    public String getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(String cinemaID) {
        this.cinemaID = cinemaID;
    }

    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public Seat[][] getLayout() {
        return layout;
    }

    public void setLayout(Seat[][] layout) {
        this.layout = layout;
    }

    /**
     * Display the seating layout of the cinema hall.
     */
    public void showLayout() {
        char rowNum = 65;   // start at A (65), ends at Z (90)
        System.out.println("\nMovie Layout");
        System.out.println();
        System.out.print("  ");
        for(int i=0; i < layout[0].length; ++i) {
        	System.out.print("---");
        }
        System.out.print("SCREEN");
        for(int i=0; i < layout[0].length; ++i) {
        	System.out.print("---");
        }
        System.out.println();
        System.out.print("  |  ");
        for(int k=1; k <= layout[0].length; k++) {
            // Print seat status
            System.out.print("  "+ k +"  ");
        }
        System.out.println("");
        for(int i=0; i < layout.length; i++) {
            System.out.print(rowNum++ + " | ");

            for(int j=0; j < layout[0].length; j++) {       // layout[0] can be any index, just need to get the number of cols
                // Print seat status
            	if(layout[i][j].getSeatStatus() != -1)
            		System.out.print(" ["+ layout[i][j].getSeatStatus() +"] ");
            	else System.out.print("  ||  ");
            }

            System.out.print("\n");
        }
    }

    @Override
    public String toString() {
        return this.cinemaID + "\nCinema Class: " + this.cinemaClass + "\nTotal Seats: " + this.numOfSeats;
    }
}

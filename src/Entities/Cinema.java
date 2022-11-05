package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class Cinema implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    private String cinemaID;
    private CinemaClass cinemaClass;
    private int numOfSeats;
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

    public void showLayout() {
        char rowNum = 65;   // start at A (65), ends at Z (90)

        for(int i=0; i < layout.length; i++) {
            System.out.print(rowNum++ + " | ");

            for(int j=0; j < layout[0].length; j++) {       // layout[0] can be any index, just need to get the number of cols
                // Print seat status
                System.out.print(" ["+ layout[i][j].getSeatStatus() +"] ");
            }

            System.out.print("\n");
        }
    }

    @Override
    public String toString() {
        return this.cinemaID + "\nCinema Class: " + this.cinemaClass + "\nTotal Seats: " + this.numOfSeats;
    }
}

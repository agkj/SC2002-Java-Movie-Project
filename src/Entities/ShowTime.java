package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ShowTime implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    private String showtimeID;
    private String cinemaID;
    private String cineplexID;
    private LocalDateTime showDateTime;
    private ShowTimeStatus showTimeStatus;
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

    public ShowTimeStatus getShowTimeStatus() {
        return showTimeStatus;
    }

    public void setShowTimeStatus(ShowTimeStatus showTimeStatus) {
        this.showTimeStatus = showTimeStatus;
    }

    public Seat[][] getShowTimeLayout() {
        return showTimeLayout;
    }

    // Print Seating Layout
    public void showLayout() {
        char rowNum = 65;   // start at A (65), ends at Z (90)
        
        System.out.println("\nMovie Layout");
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

package Entities;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a cinema seat.
 * A cinema can hold many seats.
 */
public class Seat implements Serializable {
    /**
     * Seat number of the seat based on row and column (E.g., A3).
     */
    private String seatNum;
    /**
     * Status of the seat. Legends = 0: Vacant, 1: Taken, -1: Aisle
     */
    private int seatStatus;

    public Seat() {}

    public Seat(String seatNum, int seatStatus) {
        this.seatNum = seatNum;
        this.seatStatus = seatStatus;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public int getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(int seatStatus) {
        this.seatStatus = seatStatus;
    }
}

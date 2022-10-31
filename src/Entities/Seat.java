package Entities;

public class Seat {
    private String seatNum;
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

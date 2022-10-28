package Entities;

public class Cinema {
    private String cinemaID;
    private CinemaClass cinemaClass;
    private int numOfSeats;
    private int[][] layout;

    public Cinema() {}

    public Cinema(String cinemaID, CinemaClass cinemaClass, int seats, int[][] layout) {
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

    public int[][] getLayout() {
        return layout;
    }

    public void setLayout(int[][] layout) {
        this.layout = layout;
    }
}

package Entities;

import java.util.ArrayList;

public class Cineplex {
    private String cineplexID;
    private String venue;
    private String address;
    private ArrayList<Cinema> listOfCinemas;

    public Cineplex() {}

    public Cineplex(String id, String venue, String address) {
        this.cineplexID = id;
        this.venue = venue;
        this.address = address;

        // Initialise List of Cinemas as empty
        this.listOfCinemas = new ArrayList<Cinema>();
    }

    public String getCineplexID() {
        return cineplexID;
    }

    public void setCineplexID(String cineplexID) {
        this.cineplexID = cineplexID;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Cinema> getListOfCinemas() {
        return listOfCinemas;
    }

    public void setListOfCinemas(ArrayList<Cinema> listOfCinemas) {
        this.listOfCinemas = listOfCinemas;
    }
}

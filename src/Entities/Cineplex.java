package Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
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
        return this.cineplexID;
    }

    public void setCineplexID(String cineplexID) {
        this.cineplexID = cineplexID;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Cinema> getListOfCinemas() {
        return this.listOfCinemas;
    }

    public void setListOfCinemas(ArrayList<Cinema> listOfCinemas) {
        this.listOfCinemas = listOfCinemas;
    }

    public void addCinema(Cinema cinema) {
        this.listOfCinemas.add(cinema);
    }

    @Override
    public String toString() {
        return "Cineplex ID: " + this.cineplexID + "\nVenue: " + this.venue + "\nAddress: " + this.address;
    }
}

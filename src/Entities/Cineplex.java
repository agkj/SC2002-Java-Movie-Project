package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a cineplex outlet.
 * A cineplex can hold zero to many cinemas.
 */
public class Cineplex implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    /**
     * Unique ID of the cineplex.
     */
    private String cineplexID;
    /**
     * Venue Name of the cineplex.
     */
    private String venue;
    /**
     * Address of the cineplex.
     */
    private String address;
    /**
     * List of cinema halls located at the cineplex.
     */
    private ArrayList<Cinema> listOfCinemas;

    public Cineplex() {
        // Initialise List of Cinemas as empty
        this.listOfCinemas = new ArrayList<Cinema>();
    }

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

    /**
     * Add a new cinema hall into the cineplex.
     * @param cinema New cinema object.
     */
    public void addCinema(Cinema cinema) {
        this.listOfCinemas.add(cinema);
    }

    @Override
    public String toString() {
        return "Cineplex ID: " + this.cineplexID + "\nVenue: " + this.venue + "\nAddress: " + this.address;
    }
}

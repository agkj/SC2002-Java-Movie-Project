package Entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import Entities.MovieType;

public class Movie implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    private String movieId;
    private String title, synopsis, director;
    private MovieType movieType;
    private MovieGenre genre;
    private ShowingStatus showingStatus;
    private int runtime;
    private ContentRating contentRating;
    private double overallRating;

    private ArrayList<String> cast;
    private ArrayList<Review> reviews;
    private ArrayList<ShowTime> showTimes;

    private int ticketsSold;
    private double totalSales;

    public Movie() {
        // Initialise to Empty and/or 0

        this.overallRating = 0;
        this.reviews = new ArrayList<Review>();
        this.showTimes = new ArrayList<ShowTime>();
        this.ticketsSold = 0;
        this.totalSales = 0;
    }

    public Movie(String title, String synopsis, String director, MovieType movieType, MovieGenre genre, ShowingStatus showingStatus, int runtime, ContentRating rating, ArrayList<String> cast) {

        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.movieType = movieType;
        this.genre = genre;
        this.showingStatus = showingStatus;
        this.runtime = runtime;
        this.contentRating = rating;
        this.cast = cast;


    //// Getter and Setters

    // Movie ID - Getter and Setter
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    // Title - Getter and Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Synopsis - Getter and Setter
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    // Director - Getter and Setter
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    // Movie Genre - Getter and Setter
    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    // Movie Type - Getter and Setter
    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    // Showing Status - Getter and Setter
    public ShowingStatus getShowingStatus() {
        return showingStatus;
    }

    public void setShowingStatus(ShowingStatus showingStatus) {
        this.showingStatus = showingStatus;
    }

    // Runtime - Getter and Setter
    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    // Content Rating - Getter and Setter
    public ContentRating getContentRating() {
        return contentRating;
    }

    public void setContentRating(ContentRating contentRating) {
        this.contentRating = contentRating;
    }

    // OverallRating - Getter and Setter
    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    // Cast - Getter and Setter
    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    // Reviews - Getter and Setter
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    // Tickets Sold
    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    // Total Sales ($)
    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
    public void setShowTimes(ArrayList<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }

    public void addShowTime(ShowTime showtime) {
        this.showTimes.add(showtime);
    }
    public void RemoveShowTime(ShowTime showtime) {
        this.showTimes.remove(showtime);
        //this.showTimes.remove(); use this rmeove by index
    }

    public ArrayList<ShowTime> getShowTimes() {
        return showTimes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", director='" + director + '\'' +
                ", genre=" + genre +
                ", showingStatus=" + showingStatus +
                ", runtime=" + runtime +
                ", contentRating=" + contentRating +
                ", overallRating=" + overallRating +
                ", cast=" + cast +
                ", reviews=" + reviews +
                ", showTimes=" + showTimes +
                ", ticketsSold=" + ticketsSold +
                ", totalSales=" + totalSales +
                '}';
    }
}

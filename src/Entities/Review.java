package Entities;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a movie review made by a movie-goer.
 * A movie review is made by one movie-goer.
 * A movie can have many movie reviews.
 */
public class Review implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    /**
     * Unique ID of the review.
     */
    String reviewId;
    /**
     * Review Rating of the movie (from 1 to 5).
     */
    double reviewRating;        // rating from 1-5
    /**
     * Review Content of the movie (in text).
     */
    String reviewContent;        // review in text
    
    public Review() {
    	this.reviewRating = 0;
    	this.reviewContent = null;
    }

    public Review(String reviewId, double reviewRating, String reviewContent) {
        this.reviewId = reviewId;
        this.reviewRating = reviewRating;
        this.reviewContent = reviewContent;
    }

    // Review ID
    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    // Review Rating (1-5)
    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    // Review Content
    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
    @Override
    public String toString() {
        return  "\n" +
        		"  Review [" + reviewId + "]:" +
                " rating= " + reviewRating +
                ", review=" + reviewContent + " ";
    }

}

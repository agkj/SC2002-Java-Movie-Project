package Entities;

import java.io.Serial;
import java.io.Serializable;

public class Review implements Serializable {
    @Serial
    private static final long serialVersionUID = 2002;

    String reviewId;

    //String movieId;           // foreign key, linked to which movie?
    //String userId;            // foreign key, linked to which user wrote the review?

    double reviewRating;        // rating from 1-5
    String reviewContent;        // review in text

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
}

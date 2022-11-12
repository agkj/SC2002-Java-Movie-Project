package Entities;

import java.io.Serializable;

/**
 * Represents a group of constants related to the movie content ratings.
 */
public enum ContentRating implements Serializable {
    G,          // General Audiences
    PG,         // Parental Guidance
    PG13,       // Parents Strongly Cautioned
    R,          // Restricted
    NC17;       // Adults Only
}

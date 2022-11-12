package Entities;

import java.io.Serializable;

/**
 * Represents a group of constants related to the type of movie (i.e., 2D, 3D).
 */
public enum MovieType implements Serializable {
    Two_D("2D"),
    Three_D("3D"),
    IMAX("IMAX");

    private final String type;

    private MovieType(String type) {
        this.type = type;
    }

    public String toString() {
        return this.type;
    }
}

package Entities;

/**
 * Represents a group of constants related to the type of available cinema classes.
 */
public enum CinemaClass {
    Regular("Regular"),
    Platinum("Platinum Movie Suites"),
    Elite("Elite Club");

    private final String name;

    private CinemaClass(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}

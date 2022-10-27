package Entities;

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

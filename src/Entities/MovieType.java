package Entities;

import java.io.Serializable;

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

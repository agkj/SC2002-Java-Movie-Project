package Entities;

import java.io.Serializable;

public enum ShowingStatus implements Serializable {
    Coming_Soon("Coming Soon"),
    Preview("Preview"),
    Now_Showing("Now Showing"),
    End_Of_Showing ("End of Showing");

    private final String status;

    private ShowingStatus(String statusName) {
        this.status = statusName;
    }

    public String toString() {
        return this.status;
    }
}

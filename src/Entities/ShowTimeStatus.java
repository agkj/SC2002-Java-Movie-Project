package Entities;

import java.io.Serializable;

public enum ShowTimeStatus implements Serializable {
    Available("Available"),
    Sold_Out("Sold Out"),
    Selling_Fast("Selling Fast");

    private final String status;

    private ShowTimeStatus(String statusName) {
        this.status = statusName;
    }

    public String toString() {
        return this.status;
    }
    
}

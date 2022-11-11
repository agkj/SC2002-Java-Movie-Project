package Entities;

public enum DayType {
    MON_WED("Mon to Wed"),
    THU("Thu"),
    FRI_BEFORE_6("Fri (before 6PM)"),
    FRI_AFTER_6("Fri (after 6PM)"),
    WEEKEND("Weekend"),
    HOLIDAY("Public Holiday");

    //this is the name of the TicketType
    private final String day;

    private DayType(String s) {
        day = s;
    }

    public String toString() {
        return this.day;
    }
}

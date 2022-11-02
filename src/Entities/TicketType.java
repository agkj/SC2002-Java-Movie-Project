package Entities;
// types of ticket that will affect price
public enum TicketType {
    STANDARD ("STANDARD"),
    SENIOR ("SENIOR"),
    STUDENT ("STUDENT");

    //this is the name of the TicketType
    private final String name;

    private TicketType(String s) {
        name = s;
    }

    //compare the string
    public boolean equalsString(String otherName) {
        return name.equals(otherName);
    }

    //get the String value of TicketTYpe for comparison
    public String toString() {
        return this.name;
    }
};

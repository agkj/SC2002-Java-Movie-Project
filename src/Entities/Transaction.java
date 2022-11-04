package Entities;
import java.io.Serial;
import java.util.*;
import java.io.Serializable;

public class Transaction implements Serializable{
    @Serial
    private static final long serialVersionUID = 2002;

    //Attributes
    private String transactionID;
    private String creditCardNo;
    private List <Ticket> ticketList;
    private double totalPrice;

    //Constructor

    public Transaction(String transactionID, String creditCardNo, List<Ticket> ticketList, double totalPrice) {
        this.transactionID = transactionID;
        this.creditCardNo = creditCardNo;
        this.ticketList = ticketList;
        this.totalPrice = totalPrice;
    }

    //getter
    public String getTransactionID() {
        return transactionID;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    //setter
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

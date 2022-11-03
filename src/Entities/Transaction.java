package Entities;
import java.util.*;
import java.io.Serializable;

public class Transaction implements Serializable{
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

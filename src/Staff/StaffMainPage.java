package Staff;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffMainPage{

    private String username;
    private String password; 
    
    public StaffMainPage(){
    }
    public void execute(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username");
        String inputUsername = sc.next();
        this.username = inputUsername;
        System.out.println("Enter password");
        this.password = password;
        String inputPassword = sc.next();

        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            System.out.println("Welcome!");

        } else {
            System.out.println("Wrong username or password, please try again");
        }

    }
}
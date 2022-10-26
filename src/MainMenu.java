
import Admin.AdminApp;
import Util.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;

public class MainMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		String username = "admin";
		String password = "admin";

		boolean doNotQuit = true;
		
		do {
			
			try {
				
				System.out.println("-----Login-----");
				System.out.println("| 1) Admin    |");
				System.out.println("| 2) Customer |");
				System.out.println("| 3) Quit     |");
				System.out.println("---------------");
				
				
				int choice = sc.nextInt();
				
				switch (choice) {

				case 1:

					// go to admin app
					System.out.println("--------------");
					System.out.println("Enter username");
					String inputUsername = sc.next();
					System.out.println("Enter password");
					String inputPassword = sc.next();

					if (inputUsername.equals(username) && inputPassword.equals(password)) {
						System.out.println("--------------");
						System.out.println("Welcome!");

						// Go to AdminApp
						AdminApp adminApp = new AdminApp();
						adminApp.startAdminApp();

					} else {
						System.out.println("--------------");
						System.out.println("Wrong username or password, please try again");

					}

					break;

				case 2:
					// go to customer app
					break;

				case 3:
					System.out.println("Thanks for using the app!");
					doNotQuit = false;
					break;

				default:
					System.out.println("----------------");
					System.out.println("Enter a correct option");
					break;
				}

			} 
			catch (InputMismatchException e) {
				System.out.println("----------------");
				System.out.println("Enter a correct option");
				sc.next();
				continue;

			}
	
			
		}
		while(doNotQuit);


	}

}
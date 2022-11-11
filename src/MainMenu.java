
import Admin.AdminApp;
import Util.AppHelper;
import MovieGoer.MovieGoerApp;
import MovieGoer.MovieGoerMenu;
import Util.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu extends AppHelper {

	public MainMenu(AppHelper prevApp) {
		super(null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		String username = "admin";
		String password = "admin";
		TXTEditor adminDB = new TXTEditor();

		boolean doNotQuit = true;

		do {

			try {

				System.out.println("-----Login-----");
				System.out.println("| 1) Admin    |");
				System.out.println("| 2) Customer |");
				System.out.println("| 3) Quit     |");
				System.out.println("---------------");
				System.out.print("Select an option: ");
				int choice = sc.nextInt();

				switch (choice) {

				case 1:

					// go to admin app
					System.out.println("--------------");
					System.out.println("Enter username: ");
					String inputUsername = sc.next();
					System.out.println("Enter password: ");
					String inputPassword = sc.next();

					if (inputUsername.equals(username) && inputPassword.equals(password)) {
						// Go to AdminApp
						AdminApp adminApp = new AdminApp(new MainMenu(null));
						adminApp.runInterface();

					} else {
						System.out.println("------------------------");
						System.out.println("Wrong username or password, please try again");
					}

					break;

				case 2:
					// go to MovieGoer application

					MovieGoerMenu movieGoerApp = new MovieGoerMenu(null);
					movieGoerApp.runInterface();

					break;

				case 3:
					System.out.println("------------------------");
					System.out.println("Thanks for using the app!");
					System.out.println("------------------------");
					doNotQuit = false;
					break;

				default:
					System.out.println("----------------");
					System.out.println("Enter a correct option");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("----------------");
				System.out.println("Enter a correct option");
				sc.next();
				continue;

			}

		} while (doNotQuit);

	}

}
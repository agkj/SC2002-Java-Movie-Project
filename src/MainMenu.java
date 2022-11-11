
import Admin.AdminApp;
import Admin.LoginApp;
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
					LoginApp loginApp = new LoginApp(null);
					loginApp.runInterface();

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
					System.out.println("----------------------");
					System.out.println("Enter a correct option");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("----------------------");
				System.out.println("Enter a correct option");
				sc.next();
				continue;

			}

		} while (doNotQuit);

	}

}
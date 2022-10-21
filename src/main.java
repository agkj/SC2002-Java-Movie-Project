import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("---------LOGIN---------");
		Scanner sc = new Scanner(System.in);

		String username = "admin";
		String password = "admin";

		boolean a = true;
		while (a) {
			System.out.println("1) Admin");
			System.out.println("2) Customer");
			System.out.println("3) Quit");
			int choice = sc.nextInt();

			switch (choice) {

			case 1: {

				// go to admin app

				System.out.println("Enter username");
				String inputUsername = sc.next();
				System.out.println("Enter password");
				String inputPassword = sc.next();

				if (inputUsername.equals(username) && inputPassword.equals(password)) {

					System.out.println("Welcome!");

				} else {
					System.out.println("Wrong username or password, please try again");

				}

				break;

			}
			case 2: {
				// go to customer app
				
				CustomerApp customerapp = new CustomerApp();
				customerapp.start();
				
				break;
			}

			case 3: {
				System.out.println("Thanks for using the app!");
				a = false;
				break;
			}
			default: {
				System.out.println("Please enter an option");
				break;
			}
				

			}

		}

	}

}

package MovieGoer;

import java.util.Scanner;

import Entities.Booking;
import Util.Serializer;

/**
 * [Movie-Goer Module] Movie Booking History App to check booking history. Allow
 * movie-gowers to view their booking history by their email address.
 */
public class MovieGoerCheckBooking extends Booking {

	Scanner sc = new Scanner(System.in);

	public MovieGoerCheckBooking() {
		// TODO Auto-generated constructor stub
	}

	public void getBookingDetails() {
		super.load();
		System.out.println("Enter your email address: ");
		String userEmail = sc.nextLine();

		String substring = "@gmail.com";

		while (!userEmail.contains(substring)) {
			System.out.println("Invalid email format");
			System.out.println("Enter your email: ");
			userEmail = sc.next();
		}

//		System.out.println("Enter your mobile number: ");
//		String userMobile= sc.nextLine();

		int setter = 0;
		System.out.println("==========================================================");
		System.out.println("\t\t Your bookings:");
		
		try {
			for (int i = 0; i <= movieBookings.length; i++) {
				// System.out.println(movieBookings[i].getAbsolutePath());

				Booking movieBooking = (Booking) Serializer.deSerialize(movieBookings[i].getAbsolutePath());
				// System.out.println(movieBooking.getEmail());

				if (userEmail.equals(movieBooking.getEmail())) {
					movieBooking.getInfo();

					setter++;
				}

			}

		} catch (Exception e) {
			System.out.print("");
		}

		if (setter == 0) {
			System.out.println("\t    You have no bookings made.\t\t ");
		} else if (setter == 1) {
			System.out.println("\t You have " + setter + " movie booking.\t\t");

		} else {
			System.out.println("\t You have " + setter + " movie bookings.\t\t ");
		}
		System.out.println("==========================================================");

	}

}

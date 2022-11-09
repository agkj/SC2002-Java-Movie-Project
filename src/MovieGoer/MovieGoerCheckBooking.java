package MovieGoer;

import java.io.Serializable;
import java.util.Scanner;

import Entities.MovieGoerBooking;
import Util.Serializer;

public class MovieGoerCheckBooking extends MovieGoerBooking implements Serializable{
	
	Scanner sc = new Scanner(System.in);
	public MovieGoerCheckBooking() {
		// TODO Auto-generated constructor stub
	}
	
	public void getBookingDetails() {
		

		super.load();
		System.out.println("Enter your email address: ");
		String userEmail = sc.nextLine();
//		System.out.println("Enter your mobile number: ");
//		String userMobile= sc.nextLine();
		
		int setter = 0;
		
		System.out.println("|-------------Your bookings--------------|");
		try {
			for(int i =0;i<movieBookings.length;i++) {
				//System.out.println(movieBookings[i].getAbsolutePath());
				
				MovieGoerBooking movieBooking = (MovieGoerBooking) Serializer.deSerialize(movieBookings[i].getAbsolutePath());
				//System.out.println(movieBooking.getEmail());
				
				if(userEmail.equals(movieBooking.getEmail())) {
					
					System.out.println("| Cinema code: " + movieBooking.getCinemaCode()+ "\t\t\t |");
					System.out.println("| Movie code: "+movieBooking.getSelectedMovie()+ "\t\t\t |");
					System.out.println("| TransactionID:"+movieBooking.getTicketID()+"\t |");
					//movieBooking.getInfo();
					//maybe show date and time where movie is booked
					System.out.println("|----------------------------------------|");
					setter ++;
				}
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		if(setter ==0) {
			System.out.println("No bookings made");
		}
		else {
			System.out.println("You have "+ setter+ " movie bookings.");
		}
		
		

	}
	
	

}

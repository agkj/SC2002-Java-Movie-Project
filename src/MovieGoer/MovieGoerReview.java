package MovieGoer;
import Util.TXTEditor;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MovieGoerReview extends TXTEditor{

	public MovieGoerReview() {
		
	}
	
	public void MovieGoerReview() {
		
		Scanner sc = scan();
		
		System.out.println("Enter Movie to Review: ");
		String filePath = "C:\\\\Users\\\\alger\\\\Desktop\\\\" + sc.nextLine() + ".txt";
		int choice = 1;
		while (choice != 0) {
			
			// !!!!!!!! filepath edit your txt file location !!!!!!!!
		System.out.println();
		System.out.println("------------------");
		System.out.println("Movie Reviews");
		System.out.println("1: Enter inputs");
		System.out.println("2: View inputs");
		System.out.println("0: Exit");
		System.out.print("Choice: ");
		choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("*********REVIEWING*********" + filePath);
				writeTXT(filePath);
				break;
			case 2:
				System.out.println("starting read user.csv file");
				readTXT(filePath);
				break;
			case 0: break;
			default: break;

			}
		}
	}
}
	
/*	public void writeTXT(String filePath) {

		FileWriter fw = null;
		Scanner sc = scan();

		try {
			fw = new FileWriter(filePath, true); // true is used so that previous data is not overwritten
			BufferedWriter out = new BufferedWriter(fw);

			System.out.println("Enter username");
			String user = sc.nextLine();

			System.out.println("Enter password");
			String pass = sc.nextLine();

			out.write(user + ", " + pass + "\n");
			// get username and password, ',' is used to separate the username and password,
			// '\n' is for end of entry
			out.close(); // close BufferedWriter
			sc.close(); // close scanner
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void readTXT(String filePath) {

		String line = "";
		String split = ", ";

		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			// File Reader(*Where you have installed the CSV file)
			while ((line = br.readLine()) != null) { // Read till end of data
				String[] user = line.split(split);
				// System.out.println("User= " + user[0] + " " + "Pass= " + user[1]);
				System.out.println("User[" + user[0] + "]" + "\t" + "Review[" + user[1] + "]");
				// user[0] is username and user[1] is password
			}
			br.close(); // close BufferedReader
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
}
*/
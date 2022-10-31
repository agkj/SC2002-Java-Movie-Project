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
import java.io.FileNotFoundException;

public class MovieGoerReview extends TXTEditor{


	
	public void MovieGoerReview() {
		
		//Scanner sc = new Scanner(System.in);
		Scanner sc = scan();
		
		// !!!!!!!! filepath edit your txt file location !!!!!!!!
		//String filePath = "D:\\TestTXT.txt";
		System.out.println("Enter Movie to Review: ");
		String filePath = "D:\\"+ sc.nextLine() + ".txt";	// Input movie name and goto that movie txt file
		int choice = 1;
		
		while (choice != 0) {
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
				System.out.println("starting read txt file");
				readTXT(filePath);
				break;
			case 0:
				default: break;
			}
			
		}

	}
	
/*	
	public void writeTXT(String filePath) {

		//FileWriter fw = null;
		//Scanner sc = new Scanner(System.in);
		Scanner sc = scan();
		String buffer;
		
		try {
			
			FileWriter fw = new FileWriter(filePath, true); // true is used so that previous data is not overwritten
			BufferedWriter out = new BufferedWriter(fw);
			buffer = sc.nextLine();	// buffer in order to read the data properly
			
			System.out.println("Enter username");
			String user = sc.nextLine();

			System.out.println("Enter review");
			String review = sc.nextLine();

			out.write(user + ", " + review + "\n");
			// get username and review, ',' is used to separate the username and review,
			// '\n' is for end of entry
			out.close(); // close BufferedWriter
			//sc.close(); // close scanner
			//fw.close();
		} catch (IOException e) {
			System.out.println("IOException caught!");
			e.printStackTrace();
		} 
		
	}

	public void readTXT(String filePath){

		String line = "";
		String split = ", ";
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			// File Reader(*Where you have installed the CSV file)
			
			while ((line = br.readLine()) != null) { // Read till end of data
				String[] user = line.split(split);
				// System.out.println("User= " + user[0] + " " + "Pass= " + user[1]);
				System.out.println("User[" + user[0] + "]" + "\t" + "Review[" + user[1] + "]");
				// user[0] is username and user[1] is password
			}
			br.close(); // close BufferedReader
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	*/
	
	
	
	
	
	
	
}

package Util;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TXTEditor {
	
	private Scanner sc = new Scanner(System.in);
	
	public Scanner scan() {
		return sc;
	}

	public void writeTXT(String filePath) {

		FileWriter fw = null;
		//Scanner sc = new Scanner(System.in);
		Scanner sc = scan();
		String buffer;
		
		try {
			fw = new FileWriter(filePath, true); // true is used so that previous data is not overwritten
			BufferedWriter out = new BufferedWriter(fw);
			buffer = sc.nextLine();	// require buffer to be able to read correctly

			System.out.println("Enter username");
			String user = sc.nextLine();

			System.out.println("Enter password");
			String pass = sc.nextLine();

			out.write(user + ", " + pass + "\n");
			// get username and password, ',' is used to separate the username and password,
			// '\n' is for end of entry
			out.close(); // close BufferedWriter
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
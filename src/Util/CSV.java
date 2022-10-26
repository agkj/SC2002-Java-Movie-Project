package Util;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


import java.io.IOException;

public class CSV {
	
	public static void main(String[] args) {
		  
		  String filePath = "D:\\Test.csv"; 
		  //Path that CSV File is saved at (can be passed to respective user/admin functions)
		  
		  System.out.println("starting write user.csv file: " + filePath);
		  writeCSV(filePath);
		  
		  System.out.println("starting read user.csv file");
		  readCSV(filePath);
		 }
	
	public static void writeCSV(String filePath) {
		
		FileWriter fw = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			   fw = new FileWriter(filePath, true);	//true is used so that previous data is not overwritten
			   BufferedWriter out = new BufferedWriter(fw);
			   
			   System.out.println("Enter username"); 
			   String user = sc.nextLine(); 
			   System.out.println("Enter password"); 
			   String pass = sc.nextLine(); 
			   
			   out.write(user + "," + pass + "\n");
			   //get username and password, ',' is used to separate the username and password, '\n' is for end of entry
			   out.close();	// close BufferedWriter
			   sc.close();	// close scanner
			  } 
		catch (Exception ex) {
			   ex.printStackTrace();
			  } 
	}
	
	
	public static void readCSV(String filePath) {
		
		String line = "";
		String split = ",";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			// File Reader(*Where you have installed the CSV file)
			while((line = br.readLine()) != null) { //Read till end of data
				String[] user = line.split(split);
				System.out.println("User= " + user[0] + " Pass= " + user[1]);
				//user[0] is username and user[1] is password
			}
			br.close();	//close BufferedReader
		}
		catch(IOException e) {
			e.printStackTrace();
		}
			
	}

}

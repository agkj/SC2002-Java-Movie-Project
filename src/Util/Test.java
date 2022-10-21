package Util;

import java.util.List;
import java.util.ArrayList;
//import java.io.File;
import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Scanner;
public class Test {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String line = "";
		String split = ",";
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:\\Test.csv"));
			// File Reader(*Where you have installed the CSV file)
			while((line = br.readLine()) != null) {
				String[] user = line.split(split);
				System.out.println("Staff[user]= " + user[0] + " Staff[pass]= " + user[1]);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
			
	}
	
	
	
	
}

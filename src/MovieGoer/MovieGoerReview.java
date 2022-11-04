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
				System.out.println("starting read txt file");
				readTXT(filePath);
				break;
			case 0:
				default: break;


			
			
		
	}
}
	
}
}
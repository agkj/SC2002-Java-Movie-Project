package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class FileReader {

	public static void readFile(String filePath) {

		String line = "";
		String split = ",";

		try {
			BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
			// File Reader(*Where you have installed the CSV file)
			while ((line = br.readLine()) != null) { // Read till end of data
				String[] content = line.split(split);

				for(int i=0; i < content.length; i++) {
					System.out.print(content[i]);

					if(i == content.length-1)
						System.out.print("\n");
					else
						System.out.print(", ");
				}
			}
			br.close(); // close BufferedReader
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

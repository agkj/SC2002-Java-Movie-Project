package Util;

import java.io.*;


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

	public static void writeFile(String filePath, String data, boolean endLine) {
		FileWriter fw = null;

		try {
			fw = new FileWriter(filePath, true); // true is used so that previous data is not overwritten
			BufferedWriter out = new BufferedWriter(fw);

			out.append(data);

			if(endLine)
				out.append("\n");
			else
				out.append(",");

			out.close(); // close BufferedWriter
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void replaceLine(String filePath, String stringToReplace, String newString, boolean exactWord) {
		try {
			String line;

			BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
			StringBuffer inputBuffer = new StringBuffer();

			while ((line = br.readLine()) != null) { // Read till end of data
				if(!exactWord) {
					if(line.toLowerCase().contains(stringToReplace.toLowerCase())) {
						line = newString;
					}
				} else {
					if(line.toLowerCase().equals(stringToReplace.toLowerCase())) {
						line = newString;
					}
				}

				inputBuffer.append(line);
				inputBuffer.append("\n");
			}

			br.close();

			FileOutputStream out = new FileOutputStream(filePath);
			out.write(inputBuffer.toString().getBytes());
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

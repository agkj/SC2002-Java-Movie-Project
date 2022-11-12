package Util;

import java.io.*;

/**
 * Helper class to perform CRUD functions on .txt files.
 */

public class FileReader {
	/**
	 * Read and print the content inside the specified .txt file.
	 * @param filePath Directory path of the .txt file to read
	 */
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

	/**
	 * Write into a specified .txt file.
	 * @param filePath Directory path of the file to write into.
	 * @param data Data content to write into the file.
	 * @param endLine Check if data content being written is the end of the current line. If true, insert a new line into the file.
	 */
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

	/**
	 * Copy and retrieve content of a specified file.
	 * @param filePath Directory path of the file to write into.
	 * @return Returns a StringBuffer containing the content of the file.
	 */
	public static StringBuffer copyFile(String filePath) {
		try {
			String line;

			BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
			StringBuffer inputBuffer = new StringBuffer();

			while ((line = br.readLine()) != null) { // Read till end of data
				inputBuffer.append(line);
				inputBuffer.append("\n");
			}

			br.close();

			FileOutputStream out = new FileOutputStream(filePath);
			out.write(inputBuffer.toString().getBytes());
			out.close();

			return inputBuffer;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Find and read one line from a .txt file.
	 * @param filePath Directory path of the file to write into.
	 * @param stringToFind String content to find in the file.
	 * @return Returns the entire line that contains the specified string.
	 */
	public static String readLine(String filePath, String stringToFind) {
		String line = "";
		String split = ",";

		try {
			BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
			// File Reader(*Where you have installed the CSV file)
			while ((line = br.readLine()) != null) { // Read till end of data
				if(line.toLowerCase().contains(stringToFind.toLowerCase())) {
					return line;
				}
			}
			br.close(); // close BufferedReader
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Delete a specified line from a .txt file.
	 * @param filePath Directory path of the file to write into.
	 * @param lineToRemove String content to delete in the file.
	 * @param containsFlag Indicate if trying to delete an exact line or a line that contains the string.
	 */
	public static void deleteLine(String filePath, String lineToRemove, boolean containsFlag) {
		// containsFlag checks if the string passed in lineToRemove is a substring

		String line;
		lineToRemove = lineToRemove.toLowerCase();

		File file = new File(filePath);
		File tempFile = new File("tempFile.txt");

		try {
			BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
			BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));

			while ((line = br.readLine()) != null) { // Read till end of data
				String trimmedLine = line.trim();   // Line in file to compare

				if(containsFlag) {
					if(trimmedLine.toLowerCase().contains(lineToRemove)) {
						continue;
					}

					out.write(line + System.getProperty("line.separator"));

				}
			}
			br.close(); // close BufferedReader
			out.close();

			// Replace old file with new temp file (with removed value)
			file.delete();
			tempFile.renameTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rewrite and replace a line in a specified .txt file.
	 * @param filePath Directory path of the file to write into.
	 * @param stringToReplace Old string content to replace in the file.
	 * @param newString New string content to replace in the file.
	 * @param exactWord Indicate if replacing an exact line or a line that contains the string.
	 */
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

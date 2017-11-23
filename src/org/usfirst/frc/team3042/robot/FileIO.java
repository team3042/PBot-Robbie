package org.usfirst.frc.team3042.robot;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**--------------------------------------------------------------------------------
 * Create and write to a text file.
 */
public class FileIO {

	PrintStream writer;

	/**---------------------------------------------------------------------------
	 * Create a new file. If the file already exists it will get over-written.
	 * 
	 * @param path 		specifies the directory and should include the trailing "/"
	 * @param filename 	specifies the name of the file to be created.
	 */
	public void openFile(String path, String filename) {
		File dir = new File(path);
		String url = path + filename;
		File file = new File(url);

		dir.mkdir();

		try {
			if (file.exists()) file.delete();
			file.createNewFile();
			writer = new PrintStream(file);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**---------------------------------------------------------------------------
	 * Write a line of text to the file.
	 * Each call creates a new line.
	 * 
	 * @param content contains the text to be appended to the file.
	 */
	public void writeToFile(String content) {
		writer.println(content);
		writer.flush();
	}

	/**---------------------------------------------------------------------------
	 * Close the text file.
	 */
	public void closeFile() {
		writer.close();
	}
}

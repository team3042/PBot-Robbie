package org.usfirst.frc.team3042.robot;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/** FileIO ********************************************************************
 * Create and write to a text file.
 ******************************************************************************/
public class FileIO {
	
	PrintStream writer;

	/** openFile **************************************************************
	 * Create a new file. If the file already exists it will get over-written.
	 * 
	 * String path 		the directory path; should include the trailing "/"
	 * String filename 	the name of the file to be created
	 **************************************************************************/
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

	/** writeToFile ***********************************************************
	 * Write a line of text to the file. Each call creates a new line.
	 * 
	 * String content 	Contains the text to be appended
	 **************************************************************************/
	public void writeToFile(String content) {
		writer.println(content);
		writer.flush();
	}

	/** closeFile *************************************************************
	 * Close the text file.
	 **************************************************************************/
	public void closeFile() {
		writer.close();
	}
}

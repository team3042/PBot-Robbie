package org.usfirst.frc.team3042.robot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/** Logger ********************************************************************
 * Creates specific formatting of output for the log file.
 ******************************************************************************/
public class Logger extends FileIO {
	private static final String FILE_DATE_FORMAT = "yyyy-MM-dd-hhmmss";
	private static final String LOG_TIME_FORMAT = "hh:mm:ss:SS";

	// Directory path where logs can be stored on the RoboRIO
	private static final String path = "/home/lvusers/logs/";
	
	boolean useConsole, useFile;
	int level;
		
	/** Logger ****************************************************************
	 * Create a new log file with name based on the current date and time.
	 * 
	 * boolean 	useConsole	true if output should be directed to the console
	 * boolean 	useFile		true if output should be directed to the file
	 * int 		level		the logging level; all statements less than or 
	 * 						equal to this value will be printed
	 **************************************************************************/
	public Logger(boolean useConsole, boolean useFile, int level) {
		if(useFile) {
			Date now = new Date();
			SimpleDateFormat fileTimeStamp = new SimpleDateFormat(FILE_DATE_FORMAT);
			fileTimeStamp.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
			String filename = fileTimeStamp.format(now);
			
			this.openFile(path, filename);
		}
		
		this.useConsole = useConsole;
		this.useFile = useFile;
		this.level = level;
	}
	
	/** log ***********************************************************************
	 * Write an entry into the log
	 * 
	 * String 	message	the contents of the log entry
	 * int 		level	the logging level of the entry
	 ******************************************************************************/
	public void log(String message, int level) {			
		if(level <= this.level) {
			// Add the name of the calling class to the message
			String cls = Thread.currentThread().getStackTrace()[2].getClassName();
			int i = cls.lastIndexOf(".") + 1;
			cls = cls.substring(i);
								
			//Add timestamp to the message
			Date now = new Date();
			SimpleDateFormat logTimeStamp = new SimpleDateFormat(LOG_TIME_FORMAT);
			logTimeStamp.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
			String time = logTimeStamp.format(now);			
							
			message = time + "\t" + "[" + cls + "] " + message;
				
			if(useFile) {this.writeToFile(message);}
			if(useConsole) {System.out.println(message);}
		}
	}
}


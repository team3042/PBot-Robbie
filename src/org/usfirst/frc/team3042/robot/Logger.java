package org.usfirst.frc.team3042.robot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/** Logger ********************************************************************
 * To use the logger, first create an instance with 
 * Logger(boolean, boolean, int, int, String), then create other instances with 
 * Logger(int, String) in each class you want to append log entries.
 * Only entries with a logging level that is less than or equal to the local 
 * level, as well as less than or equal to the global level, will be 
 * entered.
 ******************************************************************************/
public class Logger {
	// Logging levels
	static public int OFF = 0;
	static public int COMPETITION = 1;
	static public int DEBUG = 2;
	
	static private FileIO file = new FileIO();
	static private boolean useConsole = false;
	static private boolean useFile = false;
	static private int globalLevel = 0;

	int level; 		// logging level for the local instance
	String caller;	// The class name for this instance

	/** Logger ****************************************************************
	 * If the long format constructor is use, initialize the file and class 
	 * variables. If the short format is used, just create an instance for 
	 * appending formatted entries to an existing file.
	 * 
	 * boolean 	useConsole 	true if output should be directed to the console
	 * boolean 	useFile 	true if output should be directed to the file
	 * int 		globalLevel	the global logging level
	 * int 		level		the local logging level
	 * String	caller		the class name for the local instance
	 **************************************************************************/
	public Logger(boolean useConsole, boolean useFile, int globalLevel, 
			int level, String caller) {
		this(level, caller);
		init(useConsole, useFile, globalLevel);
	}
	public Logger(int level, String caller) {
		this.level = level;
		this.caller = caller;
	}

	/** init ******************************************************************
	 * Initialize a new log file with name based on the current date and time.
	 * 
	 * boolean 	useConsole 	true if output should be directed to the console
	 * boolean 	useFile 	true if output should be directed to the file
	 * int		level		the global logging level
	 **************************************************************************/
	static private void init(boolean useConsole, boolean useFile, int level) {
		if (useFile) { 
			String filename = formatDateTime(RobotMap.LOG_FILE_FORMAT);
			file.create(RobotMap.LOG_DIRECTORY_PATH, filename);
		}	
		Logger.useConsole = useConsole;
		Logger.useFile = useFile;
		Logger.globalLevel = level;
	}
	
	/** formatDateTime ********************************************************
	 * Return the current date and time as a String following the input
	 * formatting string.
	 * 
	 * String 	format	A parameterized string used by SimpleDateFormat to 
	 * 					format the date and time.
	 **************************************************************************/
	static private String formatDateTime(String format) {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getTimeZone(RobotMap.LOG_TIME_ZONE));
		return formatter.format(now);
	}

	/** add *******************************************************************
	 * Write a formatted entry into the log, including the time and calling 
	 * class.
	 * 
	 * String 	message	the contents of the log entry
	 * int 		level	the logging level of the entry
	 **************************************************************************/
	public void add(String message, int level) {			
		if ( (level <= globalLevel) && (level <= this.level) && (level > 0)) {
			String time = formatDateTime(RobotMap.LOG_TIME_FORMAT);			
							
			message = time + "\t" + "[" + caller + "] " + message;
				
			if (useFile) file.write(message);
			if (useConsole) System.out.println(message);
		}
	}
}


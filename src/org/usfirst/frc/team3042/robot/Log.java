package org.usfirst.frc.team3042.robot;


import static org.usfirst.frc.team3042.robot.RobotMap.*;

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
 */
public class Log {
	
	/** List of Logging Levels ************************************************/
	public static enum Level {
		OFF, ERROR, WARNING, TRACE, DEBUG, DEBUG_PERIODIC, ALL;
		public boolean isLessThanOrEqualTo(Level b) {
			return this.ordinal() <= b.ordinal();
			}
		public boolean isGreaterThan(Level b) {
			return this.ordinal() > b.ordinal();
			}
	}
	
	/** Class Variables *******************************************************/
	static private FileIO file = new FileIO();
	static private boolean useConsole = false;
	static private boolean useFile = false;
	static private Level globalLevel = Level.OFF;

	/** Instance Variables ****************************************************/
	Level level; 		// logging level for the this instance
	String caller;	// The calling class name for this instance

	
	/** Logger ****************************************************************
	 * If the long format constructor is use, initialize the file and class 
	 * variables. If the short format is used, just create an instance for 
	 * appending formatted entries to an existing file.
	 * 
	 * boolean 	useConsole 	true if output should be directed to the console
	 * boolean 	useFile 	true if output should be directed to the file
	 * Level 	globalLevel	the global logging level
	 * Level 	level		the local logging level
	 * String	caller		the class name for the local instance
	*/
	public Log(boolean useConsole, boolean useFile, Level globalLevel, 
			Level level, String caller) {
		this(level, caller);
		init(useConsole, useFile, globalLevel);
	}
	public Log(Level level, String caller) {
		this.level = level;
		this.caller = caller;
	}

	
	/** init ******************************************************************
	 * Initialize a new log file with name based on the current date and time.
	 * 
	 * boolean 	useConsole 	true if output should be directed to the console
	 * boolean 	useFile 	true if output should be directed to the file
	 * Level	level		the global logging level
	*/
	private void init(boolean useConsole, boolean useFile, Level level) {
		if (useFile) { 
			String filename = formatDateTime(LOG_FILE_FORMAT);
			file.create(LOG_DIRECTORY_PATH, filename);
		}	
		Log.useConsole = useConsole;
		Log.useFile = useFile;
		Log.globalLevel = level;
	}
	
	
	/** formatDateTime ********************************************************
	 * Return the current date and time as a String following the input
	 * formatting string.
	 * 
	 * String 	format	A parameterized string used by SimpleDateFormat to 
	 * 					format the date and time.
	*/
	static private String formatDateTime(String format) {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getTimeZone(LOG_TIME_ZONE));
		return formatter.format(now);
	}

	
	/** add *******************************************************************
	 * Write a formatted entry into the log, including the time and calling 
	 * class.
	 * 
	 * String 	message	the contents of the log entry
	 * Level	level	the logging level of the entry
	*/
	public void add(String message, Level level) {			
		if ( level.isLessThanOrEqualTo(globalLevel)
				&& level.isLessThanOrEqualTo(this.level) 
				&& (level.isGreaterThan(Level.OFF))) {
			
			String time = formatDateTime(LOG_TIME_FORMAT);							
			message = time + "\t" + "[" + caller + "] " + message;
				
			if (useFile) file.write(message);
			if (useConsole) System.out.println(message);
		}
	}
}


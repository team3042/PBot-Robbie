package org.usfirst.frc.team3042.robot;

import static org.usfirst.frc.team3042.robot.Logger.*;
import static org.usfirst.frc.team3042.robot.RobotMap.Bot.*;


/** RobotMap ******************************************************************
 * The robot configuration file.
 */
public class RobotMap {
	
	
	/** Robot selector ********************************************************/
	public static enum Bot {PBot, Artemis;}
	public static final Bot bot = PBot;
	
	
	/** USB ports *************************************************************/
	public static final int USB_GAMEPAD = 0;
	
	
	/** Logger Settings *******************************************************/
	public static final String 	LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 	LOG_TIME_FORMAT = "hh:mm:ss:SS";
	public static final String 	LOG_DIRECTORY_PATH = "/home/lvusers/logs/";
	public static final String 	LOG_TIME_ZONE = "America/Chicago";
	public static final boolean LOG_TO_CONSOLE = true;
	public static final boolean LOG_TO_FILE = true;
	public static final int 	LOG_LEVEL_GLOBAL = TRACE;
	public static final int 	LOG_LEVEL_ROBOT = TRACE;
	public static final int		LOG_LEVEL_OI = TRACE;
	//---------- Subsystems ----------
	public static final int		LOG_LEVEL_EXAMPLE_SUBSYSTEM = TRACE;
	//---------- Commands ----------
	public static final int		LOG_LEVEL_EXAMPLE_COMMAND = TRACE;

	
	/** isBot *****************************************************************
	 * Returns true if the input robot matches the selected robot.
	 * 
	 * Bot	bot	The comparison robot
	 */
	public static boolean isBot(Bot bot) {
		return bot == RobotMap.bot;
	}
	
	
	/** getBot ****************************************************************
	 * Returns the currently selected robot
	 */
	public static Bot getBot() {
		return bot;
	}
}

package org.usfirst.frc.team3042.robot;


/** RobotMap ******************************************************************
 * The robot configuration file.
 */
public class RobotMap {
	
	
	/** Robot selector ********************************************************/
	public static enum Bot {PBot, Artemis;}
	public static final Bot bot = Bot.PBot;
	

	/** USB ports *************************************************************/
	public static final int USB_GAMEPAD = 0;

	
	/** PWM ports *************************************************************/
	public static final int PAN_PORT = 0;
	public static final int TILT_PORT = 1;
	
	
	/** PanTilt Settings ******************************************************/
	//PWM bounds are for the HS-5685MH servo
	public static final double SERVO_PWM_MIN = 2.25;
	public static final double SERVO_PWM_MAX = 0.75;

	
	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT = "hh:mm:ss:SS";
	public static final String 		LOG_DIRECTORY_PATH = "/home/lvusers/logs/";
	public static final String 		LOG_TIME_ZONE = "America/Chicago";
	public static final boolean 	LOG_TO_CONSOLE = true;
	public static final boolean 	LOG_TO_FILE = true;
	public static final Log.Level 	LOG_LEVEL_GLOBAL = Log.Level.TRACE;
	public static final Log.Level 	LOG_LEVEL_ROBOT = Log.Level.TRACE;
	public static final Log.Level	LOG_LEVEL_OI = Log.Level.TRACE;
	//---------- Subsystems ----------
	public static final Log.Level	LOG_LEVEL_PAN_TILT = Log.Level.TRACE;
	public static final Log.Level	LOG_LEVEL_EXAMPLE_SUBSYSTEM = Log.Level.TRACE;
	//---------- Commands ----------
	public static final Log.Level	LOG_LEVEL_EXAMPLE_COMMAND = Log.Level.TRACE;

}

package org.usfirst.frc.team3042.robot;

/** RobotMap ******************************************************************
 * The robot configuration file.
 */
public class RobotMap {
	/** Robot selector ********************************************************/
	public static enum Bot {PBOT, ARTEMIS;}
	// Set the bot to which you intend to push code.
	private static Bot currentBot = Bot.PBOT;

	private static final boolean IS_PBOT 	= (currentBot == Bot.PBOT);
	private static final boolean IS_ARTEMIS 	= (currentBot == Bot.ARTEMIS);
	
	
	/** USB ports *************************************************************/					
	public static final int USB_JOYSTICK_LEFT 	= 0;
	public static final int USB_JOYSTICK_RIGHT 	= 1;
	public static final int USB_GAMEPAD 		= IS_PBOT ? 0 : 2;


	/** PWM ports *************************************************************/
	public static final int PWM_PAN_PORT 	= 0;
	public static final int PWM_TILT_PORT 	= 1;
	
	
	/** CAN ID numbers ********************************************************/
	public static final int CAN_LEFT_MOTOR 	= 		IS_PBOT 	? 0 :
													IS_ARTEMIS 	? 0 : 0;
	
	public static final int CAN_RIGHT_MOTOR = 		IS_PBOT 	? 0 :
													IS_ARTEMIS 	? 0 : 0;
	
	public static final int CAN_LEFT_FOLLOWER = 	IS_ARTEMIS 	? 0 : 0;
	
	public static final int CAN_RIGHT_FOLLOWER = 	IS_ARTEMIS 	? 0 : 0;
	
	public static final int CAN_SPINNER 	= 		IS_PBOT		? 0 :
													IS_ARTEMIS 	? 0 : 0;
	
	
	/** OI Settings ***********************************************************/
	public static final boolean USE_JOYSTICKS = !IS_PBOT;
	public static final double JOYSTICK_DRIVE_SCALE = 1.0;
	public static final double JOYSTICK_DEAD_ZONE = 0.1;


	/** Drivetrain Settings ***************************************************/
	public static final boolean HAS_DRIVETRAIN = false;
	public static final boolean HAS_FOLLOWERS = !IS_PBOT;
	public static final boolean DRIVETRAIN_BRAKE_MODE = true;
	public static final boolean REVERSE_LEFT_MOTOR = false;
	public static final boolean REVERSE_RIGHT_MOTOR = true;
	// Maximum Acceleration given in power per second
	public static final double ACCELERATION_MAX = 3.6;
	
	
	/** Drivetrain Encoder Settings *******************************************/
	public static final boolean HAS_ENCODERS = false;
	//Encoder counts per revolution
	//In quadrature mode, actual counts will be 4x this; e.g., 360 -> 1440
	public static final int COUNTS_PER_REVOLUTION = 360;
	//How often the encoders update on the CAN, in milliseconds
	public static final int ENCODER_FRAME_RATE = 10;
	public static final boolean REVERSE_LEFT_ENCODER = false;
	public static final boolean REVERSE_RIGHT_ENCODER = false;
	
	
	/** PanTilt Settings ******************************************************/
	public static final boolean HAS_PAN_TILT = IS_PBOT;
	//PWM bounds are for the HS-5685MH servo
	public static final double SERVO_PWM_MAX = 2.25;
	public static final double SERVO_PWM_MIN = 0.76;
	//The change in servo position per second when driven with the POV buttons
	public static final double SERVO_SPEED = 0.25;
	//Reverse the direction of the servos if they don't match the controls
	public static final boolean REVERSE_PAN = false;
	public static final boolean REVERSE_TILT = false;

	
	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT = "hh:mm:ss:SSS";
	public static final String 		LOG_DIRECTORY_PATH = "/home/lvuser/logs/";
	public static final String 		LOG_TIME_ZONE = "America/Chicago";
	public static final boolean 	LOG_TO_CONSOLE = true;
	public static final boolean 	LOG_TO_FILE = true;
	public static final Log.Level 	LOG_GLOBAL 					= Log.Level.TRACE;
	public static final Log.Level 	LOG_ROBOT 					= Log.Level.TRACE;
	public static final Log.Level	LOG_OI 						= Log.Level.TRACE;
	public static final Log.Level	LOG_AXIS_TRIGGER 			= Log.Level.TRACE;
	public static final Log.Level	LOG_POV_BUTTON				= Log.Level.TRACE;
	/** Subsystems **/
	public static final Log.Level	LOG_DRIVETRAIN				= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_FOLLOWERS	= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_ENCODERS 	= Log.Level.TRACE;
	public static final Log.Level 	LOG_PAN_TILT 				= Log.Level.TRACE;
	public static final Log.Level	LOG_EXAMPLE_SUBSYSTEM 		= Log.Level.TRACE;
	/** Commands **/
	public static final Log.Level	LOG_DRIVETRAIN_TANKDRIVE 	= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_ENC_DASH 	= Log.Level.TRACE;
	public static final Log.Level	LOG_PAN_TILT_SET 			= Log.Level.TRACE;
	public static final Log.Level	LOG_PAN_TILT_DRIVE 			= Log.Level.TRACE;
	public static final Log.Level	LOG_EXAMPLE_COMMAND 		= Log.Level.TRACE;	
}

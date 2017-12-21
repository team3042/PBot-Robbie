package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;

/** RobotMap ******************************************************************
 * The robot configuration file.
 */
public class RobotMap {
	/** Robot selector ********************************************************/
	public static enum Bot {PBOT, ARTEMIS;}
	// Set the bot to which you intend to push code.
	private static Bot currentBot = Bot.PBOT;

	private static final boolean IS_PBOT 	= (currentBot == Bot.PBOT);
	private static final boolean IS_ARTEMIS = (currentBot == Bot.ARTEMIS);
	
	
	/** Robot Size Parameters *************************************************
	 * The units of the wheel diameter determine the units of the position 
	 * and speed closed-loop commands. For example, if the diamter is given in 
	 * inches, position will be in inches and speed in inches per second.
	 */
	public static final double WHEEL_DIAMETER = 4;
	public static final double WHEEL_BASE = 0.0;
	
	
	/** USB ports *************************************************************/					
	public static final int USB_JOYSTICK_LEFT 	= 0;
	public static final int USB_JOYSTICK_RIGHT 	= 1;
	public static final int USB_GAMEPAD 		= IS_PBOT ? 0 : 2;


	/** PWM ports *************************************************************/
	public static final int PWM_PAN_PORT 	= 0;
	public static final int PWM_TILT_PORT 	= 1;
	
	
	/** CAN ID numbers ********************************************************/
	public static final int CAN_LEFT_MOTOR 	= 		IS_PBOT 	? 3 :
													IS_ARTEMIS 	? 0 : 0;
	public static final int CAN_RIGHT_MOTOR = 		IS_PBOT 	? 9 :
													IS_ARTEMIS 	? 0 : 0;
	public static final int CAN_LEFT_FOLLOWER = 	IS_ARTEMIS 	? 0 : 0;
	public static final int CAN_RIGHT_FOLLOWER = 	IS_ARTEMIS 	? 0 : 0;
	public static final int CAN_SPINNER 	= 		IS_PBOT		? 10 :
													IS_ARTEMIS 	? 0 : 0;
	
	
	/** PCM channels **********************************************************/
	public static final int LIGHT_RING_CHANNEL = 0;
	
	
	/** OI Settings ***********************************************************/
	public static final boolean USE_JOYSTICKS = !IS_PBOT;
	public static final double JOYSTICK_DRIVE_SCALE = 0.5;
	public static final double TRIGGER_SPINNER_SCALE = 0.1;
	public static final double JOYSTICK_DEAD_ZONE = 0.0;


	/** Drivetrain Settings ***************************************************/
	public static final boolean HAS_DRIVETRAIN = true;
	public static final boolean HAS_FOLLOWERS = !IS_PBOT;
	public static final boolean DRIVETRAIN_BRAKE_MODE = true;
	public static final boolean REVERSE_LEFT_MOTOR = 	(IS_PBOT) ? true : false;
	public static final boolean REVERSE_RIGHT_MOTOR = 	(IS_PBOT) ? false: false;
	// Maximum Acceleration given in power per second
	public static final double ACCELERATION_MAX = 1.5;
	public static final double kF_DRIVE_LEFT = 	(IS_PBOT) 		? 0.0 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	public static final double kF_DRIVE_RIGHT = (IS_PBOT) 		? 0.0 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	
	
	/** Drivetrain Encoder Settings *******************************************/
	public static final boolean HAS_ENCODERS = true;
	//Encoder counts per revolution
	//In quadrature mode, actual counts will be 4x this; e.g., 360 -> 1440
	public static final int COUNTS_PER_REVOLUTION = 360;
	//How often the encoders update on the CAN, in milliseconds
	public static final int ENCODER_FRAME_RATE = 10;
	public static final boolean REVERSE_LEFT_ENCODER = true;
	public static final boolean REVERSE_RIGHT_ENCODER = false;
	
	
	/** Drivetrain Autonomous Settings ****************************************/
	public static final boolean HAS_AUTON = false;
	public static final int AUTON_PROFILE = 0;
	public static final double kP_AUTON = 		(IS_PBOT) 		? 0.0 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	public static final double kI_AUTON = 		(IS_PBOT) 		? 0.0 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	public static final double kD_AUTON = 		(IS_PBOT) 		? 0.0 :
												(IS_ARTEMIS) 	? 0.0 : 0.0;
	public static final int I_ZONE_AUTON =		(IS_PBOT)		? 0 :
												(IS_ARTEMIS)	? 0 : 0;
	//The rate of pushing motion profile points to the talon, in ms
	public static final int AUTON_FRAME_RATE = 5;
	
	
	/** Spinner Settings ******************************************************/
	public static final boolean HAS_SPINNER = IS_PBOT;
	public static final boolean SPINNER_BRAKE_MODE = true;
	public static final boolean REVERSE_SPINNER = false;
	
	
	/** Spinner Encoder Settings **********************************************/
	public static final boolean HAS_SPINNER_ENCODER = IS_PBOT;
	public static final int SPINNER_ENCODER_FRAME_RATE = 10;
	public static final int SPINNER_ENCODER_COUNTS_PER_REV = 1024;
	public static final boolean REVERSE_SPINNER_ENCODER = false;
	
	
	/** Spinner Closed Loop Settings ******************************************/
	public static boolean HAS_SPINNER_CLOSED_LOOP = false;
	public static int SPINNER_PROFILE = 0;
	public static final double kP_SPINNER = 0.0;
	public static final double kI_SPINNER = 0.0;
	public static final double kD_SPINNER = 0.0;
	public static final double kF_SPINNER = 0.0;
	
	
	/** PanTilt Settings ******************************************************/
	public static final boolean HAS_PAN_TILT = IS_PBOT;
	//PWM bounds are for the HS-5685MH servo
	public static final double SERVO_PWM_MAX = 2.25;
	public static final double SERVO_PWM_MIN = 0.76;
	public static final double PAN_MIN = 0.0;
	public static final double PAN_CENTER 	= 0.530;
	public static final double PAN_MAX = 1.0;
	public static final double TILT_MIN = 0.0;
	public static final double TILT_CENTER 	= 0.515;
	public static final double TILT_MAX = 1.0;
	//The change in servo position per second when driven with the POV buttons
	public static final double SERVO_SPEED = 0.25;
	//Reverse the direction of the servos if they don't match the controls
	public static final boolean REVERSE_PAN = false;
	public static final boolean REVERSE_TILT = false;
	
	
	/** Gyroscope Settings ****************************************************/
	public static final boolean HAS_GYROSCOPE = true;
	public static final double GYROSCOPE_SCALE = 0.25;
	
	
	/** LEDRing Settings ******************************************************/
	public static final boolean HAS_LIGHT_RING = false;

	
	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT = "hh:mm:ss:SSS";
	public static final String 		LOG_DIRECTORY_PATH = "/home/lvuser/logs/";
	public static final String 		LOG_TIME_ZONE = "America/Chicago";
	public static final boolean 	LOG_TO_CONSOLE 				= true;
	public static final boolean 	LOG_TO_FILE 				= false;
	public static final Log.Level 	LOG_GLOBAL 					= Log.Level.DEBUG;
	public static final Log.Level 	LOG_ROBOT 					= Log.Level.TRACE;
	public static final Log.Level	LOG_OI 						= Log.Level.TRACE;
	public static final Log.Level	LOG_AXIS_TRIGGER 			= Log.Level.TRACE;
	public static final Log.Level	LOG_POV_BUTTON				= Log.Level.TRACE;
	/** Subsystems **/
	public static final Log.Level	LOG_DRIVETRAIN				= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_FOLLOWERS	= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_ENCODERS 	= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_AUTON		= Log.Level.TRACE;
	public static final Log.Level	LOG_SPINNER					= Log.Level.TRACE;
	public static final Log.Level	LOG_SPINNER_ENCODER			= Log.Level.TRACE;
	public static final Log.Level 	LOG_PAN_TILT 				= Log.Level.TRACE;
	public static final Log.Level	LOG_GYROSCOPE				= Log.Level.TRACE;
	public static final Log.Level	LOG_LIGHT_RING				= Log.Level.TRACE;
	public static final Log.Level	LOG_EXAMPLE_SUBSYSTEM 		= Log.Level.TRACE;
	/** Commands **/
	public static final Log.Level	LOG_DRIVETRAIN_TANKDRIVE 	= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_ENC_DASH 	= Log.Level.TRACE;
	public static final Log.Level	LOG_GYROSCOPE_DASHBOARD		= Log.Level.TRACE;
	public static final Log.Level	LOG_PAN_TILT_SET 			= Log.Level.TRACE;
	public static final Log.Level	LOG_PAN_TILT_DRIVE 			= Log.Level.DEBUG;
	public static final Log.Level	LOG_LIGHT_RING_ON			= Log.Level.TRACE;
	public static final Log.Level	LOG_SPINNER_DRIVE			= Log.Level.TRACE;
	public static final Log.Level	LOG_SPINNER_ENCODER_DASH	= Log.Level.TRACE;
	public static final Log.Level	LOG_SPINNER_CLOSED_LOOP		= Log.Level.TRACE;
	public static final Log.Level	LOG_EXAMPLE_COMMAND 		= Log.Level.TRACE;	
}

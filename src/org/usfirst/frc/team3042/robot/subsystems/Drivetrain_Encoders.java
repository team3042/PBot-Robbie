package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.robot.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_Encoders_Dashboard;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Drivetrain_Encoders **********************************************************
 * The encoders for the drivetrain.
 */
public class Drivetrain_Encoders extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_ENCODERS;
	private static final int COUNTS_PER_REVOLUTION = RobotMap.COUNTS_PER_REVOLUTION;
	private static final int FRAME_RATE = RobotMap.ENCODER_FRAME_RATE;
	private static final boolean REVERSE_LEFT = RobotMap.REVERSE_LEFT_ENCODER;
	private static final boolean REVERSE_RIGHT = RobotMap.REVERSE_RIGHT_ENCODER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	CANTalon left, right;
	int leftZero, rightZero;
	
	
	/** Drivetrain_Encoders ***************************************************/
	public Drivetrain_Encoders(CANTalon leftMotor, CANTalon rightMotor) {
		log.add("Constructor", LOG_LEVEL);
		
		left = leftMotor;
		right= rightMotor;
		
		left.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		left.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, FRAME_RATE);
		left.configEncoderCodesPerRev(COUNTS_PER_REVOLUTION);
		left.reverseSensor(REVERSE_LEFT);
		
		right.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		right.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, FRAME_RATE);
		right.configEncoderCodesPerRev(COUNTS_PER_REVOLUTION);
		right.reverseSensor(REVERSE_RIGHT);
		
		reset();
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Drivetrain_Encoders_Dashboard());
	}

	
	/** reset *****************************************************************/
	public void reset() {
		leftZero = left.getEncPosition();
		rightZero = right.getEncPosition();
	}
	
	
	/** Get the encoder position or speed *************************************
	 * Units are in encoder counts for position and encoder counts per 100 ms 
	 * for speed.
	 */
	public int getLeft() {
		return left.getEncPosition() - leftZero;
	}
	public int getRight() {
		return right.getEncPosition() - rightZero;
	}
	public int getLeftSpeed() {
		return left.getEncVelocity();
	}
	public int getRightSpeed() {
		return right.getEncVelocity();
	}
}

package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
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
	CANTalon leftMotor, rightMotor;
	int leftZero, rightZero;
	
	
	/** Drivetrain_Encoders ***************************************************/
	public Drivetrain_Encoders(CANTalon leftMotor, CANTalon rightMotor) {
		log.add("Constructor", LOG_LEVEL);
						
		this.leftMotor = initEncoder(leftMotor, REVERSE_LEFT);
		this.rightMotor = initEncoder(rightMotor, REVERSE_RIGHT);
		
		reset();
	}
	private CANTalon initEncoder(CANTalon motor, boolean reverse) {
		motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motor.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, FRAME_RATE);
		motor.configEncoderCodesPerRev(COUNTS_PER_REVOLUTION);
		motor.reverseSensor(reverse);
		return motor;
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Drivetrain_Encoders_Dashboard());
	}

	
	/** reset *****************************************************************/
	public void reset() {
		leftZero = leftMotor.getEncPosition();
		rightZero = rightMotor.getEncPosition();
	}
	
	
	/** Get the encoder position or speed *************************************
	 * Units are in encoder counts for position and encoder counts per 100 ms 
	 * for speed.
	 */
	public int getLeft() {
		return leftMotor.getEncPosition() - leftZero;
	}
	public int getRight() {
		return rightMotor.getEncPosition() - rightZero;
	}
	public int getLeftSpeed() {
		return leftMotor.getEncVelocity();
	}
	public int getRightSpeed() {
		return rightMotor.getEncVelocity();
	}
}

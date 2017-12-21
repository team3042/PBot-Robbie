package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.DrivetrainEncodersDashboard;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DrivetrainEncoders ***********************************************************
 * The encoders for the drivetrain.
 */
public class DrivetrainEncoders extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_ENCODERS;
	private static final int COUNTS_PER_REVOLUTION = RobotMap.COUNTS_PER_REVOLUTION;
	private static final int FRAME_RATE = RobotMap.ENCODER_FRAME_RATE;
	private static final boolean REVERSE_LEFT = RobotMap.REVERSE_LEFT_ENCODER;
	private static final boolean REVERSE_RIGHT = RobotMap.REVERSE_RIGHT_ENCODER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	CANTalon leftEncoder, rightEncoder;
	double leftPositionZero, rightPositionZero;
	int leftCountsZero, rightCountsZero;
	
	
	/** DrivetrainEncoders ****************************************************/
	public DrivetrainEncoders(CANTalon leftMotor, CANTalon rightMotor) {
		log.add("Constructor", LOG_LEVEL);
		
		leftEncoder = leftMotor;
		rightEncoder = rightMotor;
				
		initEncoder(leftEncoder, REVERSE_LEFT);
		initEncoder(rightEncoder, REVERSE_RIGHT);
													
		reset();
	}
	private void initEncoder(CANTalon encoder, boolean reverse) {
		encoder.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		encoder.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, FRAME_RATE);
		encoder.configEncoderCodesPerRev(COUNTS_PER_REVOLUTION);
		encoder.reverseSensor(reverse);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainEncodersDashboard());
	}

	
	/** reset *****************************************************************/
	public void reset() {
		leftPositionZero = leftEncoder.getPosition();
		rightPositionZero = rightEncoder.getPosition();
		
		leftCountsZero = leftEncoder.getEncPosition();
		rightCountsZero = rightEncoder.getEncPosition();
	}
	
	
	/** Get the encoder position or speed *************************************
	 * Position is given in Revolutions - getPosition()
	 * Counts given in encoder counts - getEncPosition()
	 * Speed returns RPM - getSpeed()
	 * Encoder speed returns counts per 100ms - getEncSpeed()
	 */
	public double getLeftPosition() {
		return leftEncoder.getPosition() - leftPositionZero;
	}
	public double getRightPosition() {
		return rightEncoder.getPosition() - rightPositionZero;
	}
	public int getLeftCounts() {
		return leftEncoder.getEncPosition() - leftCountsZero;
	}
	public int getRightCounts() {
		return rightEncoder.getEncPosition() - leftCountsZero;
	}
	public double getLeftSpeed() {
		return leftEncoder.getSpeed();
	}
	public double getRightSpeed() {
		return rightEncoder.getSpeed();
	}
}

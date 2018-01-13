package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.DrivetrainEncoders_Dashboard;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DrivetrainEncoders ***********************************************************
 * The encoders for the drivetrain.
 */
public class DrivetrainEncoders extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_ENCODERS;
	private static final int COUNTS_PER_REVOLUTION = RobotMap.COUNTS_PER_REVOLUTION;
	private static final int FRAME_RATE = RobotMap.ENCODER_FRAME_RATE;
	private static final int TIMEOUT = RobotMap.AUTON_TIMEOUT;
	private static final int PIDIDX = RobotMap.AUTON_PIDIDX;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	TalonSRX leftEncoder, rightEncoder;
	int leftPositionZero, rightPositionZero;
	
	
	/** DrivetrainEncoders ****************************************************/
	public DrivetrainEncoders(TalonSRX leftMotor, TalonSRX rightMotor) {
		log.add("Constructor", LOG_LEVEL);
		
		leftEncoder = leftMotor;
		rightEncoder = rightMotor;
				
		initEncoder(leftEncoder);
		initEncoder(rightEncoder);
													
		reset();
	}
	private void initEncoder(TalonSRX encoder) {
		encoder.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 
				PIDIDX, TIMEOUT);
		encoder.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 
				FRAME_RATE, TIMEOUT);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainEncoders_Dashboard());
	}

	
	/** reset *****************************************************************/
	public void reset() {
		leftPositionZero = leftEncoder.getSelectedSensorPosition(PIDIDX);
		rightPositionZero = rightEncoder.getSelectedSensorPosition(PIDIDX);
	}
	public void setToZero() {
		leftPositionZero = 0;
		rightPositionZero = 0;
	}
	
	
	/** Get the encoder position or speed *************************************
	 * Position is converted to revolutions
	 * Speed returns counts per 100ms and is converted to RPM
	 */
	public double getLeftPosition() {
		int counts = leftEncoder.getSelectedSensorPosition(PIDIDX);
		counts -= leftPositionZero;
		return countsToRev(counts);
	}
	public double getRightPosition() {
		int counts = rightEncoder.getSelectedSensorPosition(PIDIDX);
		counts -= rightPositionZero;
		return countsToRev(counts);
	}
	private double countsToRev(int counts) {
		return (double)counts / COUNTS_PER_REVOLUTION;
	}
	public double getLeftSpeed() {
		int cp100ms = leftEncoder.getSelectedSensorVelocity(PIDIDX);
		return cp100msToRPM(cp100ms);
	}
	public double getRightSpeed() {
		int cp100ms = rightEncoder.getSelectedSensorVelocity(PIDIDX);
		return cp100msToRPM(cp100ms);
	}
	private double cp100msToRPM(int cp100ms) {
		return (double)cp100ms * 10.0 * 60.0 / COUNTS_PER_REVOLUTION;
	}
	
	
	/** rpmToF ****************************************************************
	 * Convert RPM reading into an F-Gain
	 * Note that 1023 is the native full-forward power of the talons, 
	 * equivalent to setting the power to 1.0.
	 * The speed has to be converted from rpm to encoder counts per 100ms
	 * 
	 * so F = power * 1023 / speed
	 */
	public double rpmToF(double rpm, double power) {
		//Convert to counts per 100 ms
		double speed = rpm * 4.0 * COUNTS_PER_REVOLUTION / 600.0;
		double kF = power * 1023.0 / speed;
		return kF;
	}
	public double rpmToPower(double rpm, double kF) {
		//Convert to counts per 100 ms
		double speed = rpm * 4.0 * COUNTS_PER_REVOLUTION / 600.0;
		double power = kF * speed / 1023.0;
		return power;
	}
}

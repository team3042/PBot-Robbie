package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.SpinnerEncoder_Dashboard;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;


/** SpinnerEncoder ************************************************************/
public class SpinnerEncoder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER_ENCODER;
	private static final int FRAME_RATE = RobotMap.SPINNER_ENCODER_FRAME_RATE;
	private static final int COUNTS_PER_REV = RobotMap.SPINNER_ENCODER_COUNTS_PER_REV;
	private static final boolean REVERSE = RobotMap.REVERSE_SPINNER_ENCODER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	CANTalon encoder;
	double positionZero;
	int countsZero;
	
	
	/** SpinnerEncoder ********************************************************/
	public SpinnerEncoder(CANTalon motor) {
		log.add("Constructor", LOG_LEVEL);

		encoder = motor;
		
		encoder.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, FRAME_RATE);
		encoder.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		encoder.configEncoderCodesPerRev(COUNTS_PER_REV);
		encoder.reverseSensor(REVERSE);
		
		reset();
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new SpinnerEncoder_Dashboard());
	}
	
	
	/** Reset the encoer zero position ****************************************/
	public void reset() {
		positionZero = encoder.getPosition();
		countsZero = encoder.getEncPosition();
	}
	
	
	/** Command Controls ******************************************************
	 * Position is given in Revolutions - getPosition()
	 * Counts given in encoder counts - getEncPosition()
	 * Speed returns RPM - getSpeed()
	 * Encoder speed returns counts per 100ms - getEncSpeed()
	 */
	public double getPosition() {
		return encoder.getPosition() - positionZero;
	}
	public int getCounts() {
		return encoder.getEncPosition() - countsZero;
	}
	public double getSpeed() {
		return encoder.getSpeed();
	}
	public double getPositionZero() {
		return positionZero;
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
		double speed = rpm * 4 * COUNTS_PER_REV / 600;
		double kF = power * 1023 / speed;
		
		return kF;
	}
}

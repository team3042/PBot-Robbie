package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.ADIS16448_IMU;
import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Gyroscope_Dashboard;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Gyroscope *****************************************************************
 * Gyroscope subsystem
 */
public class Gyroscope extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_GYROSCOPE;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
    ADIS16448_IMU gyroscope = new ADIS16448_IMU();

	
	/** ExampleSubsystem ******************************************************/
	public Gyroscope() {
		log.add("Constructor", LOG_LEVEL);
		reset();
		calibrate();
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Gyroscope_Dashboard());
	}
	
	
	/** Command Methods *******************************************************/
	public double getAngle() {
		return gyroscope.getAngle();	
	}
	public void reset() {
		gyroscope.reset();
	}
	public void calibrate() {
		gyroscope.calibrate();
	}
}

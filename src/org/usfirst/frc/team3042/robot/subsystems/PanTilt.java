package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.robot.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.PanTiltDrive;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;


/** PanTilt *******************************************************************
 * A subsystem to run the servos controlling the camera pan-tilt mechanism.
 */
public class PanTilt extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_PAN_TILT;
	private static final int PAN_PORT = RobotMap.PWM_PAN_PORT;
	private static final int TILT_PORT = RobotMap.PWM_TILT_PORT;
	private static final double PWM_MAX = RobotMap.SERVO_PWM_MAX;
	private static final double PWM_MIN = RobotMap.SERVO_PWM_MIN;
	
	
	/** Instance Variables ****************************************************/
	private final Log log = new Log(LOG_LEVEL, getName());
	private final Servo pan = new Servo(PAN_PORT);
	private final Servo tilt = new Servo(TILT_PORT);
	
	
	/** PanTilt ***************************************************************
	 * Constructor for the camera Pan-Tilt subsystem.
	 * Resets the PWM bounds for the particular servos being used.
	 */
	public PanTilt () {
		log.add("Constructor", Log.Level.TRACE);
		
		pan.setBounds(PWM_MAX, 0, 0, 0, PWM_MIN);
		tilt.setBounds(PWM_MAX, 0, 0, 0, PWM_MIN);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new PanTiltDrive());
	}
	
	
	/** Set the Pan and Tilt servo positions **********************************
	 * Input value is a number from 0 to 1. 
	 */
	public void setPan(double position) {
		position = safetyCheck(position);
		pan.set(position);
	}
	public void setTilt(double position) {
		position = safetyCheck(position);
		tilt.set(position);
	}
	private double safetyCheck(double position) {
		position = Math.min(1.0, position);
		position = Math.max(0.0, position);
		return position;
	}
	
	
	/** Get the Pan and Tilt servo positions **********************************/
	public double getPan() {
		return pan.get();
	}
	public double getTilt() {
		return tilt.get();
	}
}

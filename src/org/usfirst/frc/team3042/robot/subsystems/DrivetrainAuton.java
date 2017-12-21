package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.MotionProfileStatus;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;


/** DrivetrainAuton ***********************************************************
 * The methods and information necessary for autonomous motion profile driving.
 */
public class DrivetrainAuton extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_AUTON;
	private static final int PID_PROFILE = RobotMap.AUTON_PROFILE;
	private static final double kP = RobotMap.kP_AUTON;
	private static final double kI = RobotMap.kI_AUTON;
	private static final double kD = RobotMap.kD_AUTON;
	private static final double kF_LEFT = RobotMap.kF_DRIVE_LEFT;
	private static final double kF_RIGHT = RobotMap.kF_DRIVE_RIGHT;
	private static final int I_ZONE = RobotMap.I_ZONE_AUTON;
	//The Frame Rate is given in ms
	private static final int FRAME_RATE = RobotMap.AUTON_FRAME_RATE;

		
	/** Periodic Runnable *****************************************************
	 * Create a separate thread to push motion profile points out to the Talon
	 */
	class PeriodicRunnable implements java.lang.Runnable {
		public void run() { 
			leftMotor.processMotionProfileBuffer();
			rightMotor.processMotionProfileBuffer();
		}
	}
    	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Notifier notifier = new Notifier (new PeriodicRunnable());
	CANTalon leftMotor = Robot.drivetrain.leftMotor;
	CANTalon rightMotor = Robot.drivetrain.rightMotor;
	DrivetrainEncoders encoders = Robot.drivetrain.encoders;
	
	
	/** DrivetrainAuton *******************************************************/
	public DrivetrainAuton() {
		log.add("Constructor", LOG_LEVEL);
		
		initMotor(leftMotor, kF_LEFT);
		initMotor(rightMotor, kF_RIGHT);;

		/** Starting talons processing motion profile **/
		//Convert from ms to sec for the notifier
		double frameRateSec = (double)FRAME_RATE / 1000.0;
		notifier.startPeriodic(frameRateSec);
	}
	private void initMotor(CANTalon motor, double kF) {
		motor.changeMotionControlFramePeriod(FRAME_RATE);
		motor.setProfile(PID_PROFILE);
		motor.setPID(kP, kI, kD);
		motor.setF(kF);
		motor.setIZone(I_ZONE);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
	}
	

	/** prepareMotionProfile *****************************************************
	 * Clears out any old trajectories and prepares to receive new trajectory 
	 * points.
	 */
	public void prepareMotionProfile() {		
		prepareMotor(leftMotor);
		prepareMotor(rightMotor);
		disableMotionProfile();
		removeUnderrun();
		encoders.reset();
	}
	private void prepareMotor(CANTalon motor) {
		motor.clearMotionProfileTrajectories();
		motor.setProfile(PID_PROFILE);
		motor.changeControlMode(TalonControlMode.MotionProfile);
	}
	
	
	/** Motion Profile command methods ****************************************/
	public void pushPoints(	CANTalon.TrajectoryPoint leftPoint, 
							CANTalon.TrajectoryPoint rightPoint) {
		leftMotor.pushMotionProfileTrajectory(leftPoint);
		rightMotor.pushMotionProfileTrajectory(rightPoint);
	}
	public MotionProfileStatus[] getMotionProfileStatus() {
		MotionProfileStatus[] motionProfileStatus = {new MotionProfileStatus(), 
				new MotionProfileStatus()};
		leftMotor.getMotionProfileStatus(motionProfileStatus[0]);
		rightMotor.getMotionProfileStatus(motionProfileStatus[1]);
		return motionProfileStatus;
	}
	public void removeUnderrun() {
		leftMotor.clearMotionProfileHasUnderrun();
		rightMotor.clearMotionProfileHasUnderrun();
	}
	public void enableMotionProfile() {
		leftMotor.set(CANTalon.SetValueMotionProfile.Enable.value);
		rightMotor.set(CANTalon.SetValueMotionProfile.Enable.value);
	}
	public void holdMotionProfile() {
		leftMotor.set(CANTalon.SetValueMotionProfile.Hold.value);
		rightMotor.set(CANTalon.SetValueMotionProfile.Hold.value);
	}
	public void disableMotionProfile() {
		leftMotor.set(CANTalon.SetValueMotionProfile.Disable.value);
		rightMotor.set(CANTalon.SetValueMotionProfile.Disable.value);
	}
}

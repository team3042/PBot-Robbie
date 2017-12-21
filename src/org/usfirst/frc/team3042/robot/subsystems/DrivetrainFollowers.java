package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DrivetrainFollowers **********************************************************
 * Motor controllers for secondary drivetrain motors
 */
public class DrivetrainFollowers extends Subsystem {
	/** Configuration Constants ***********************************************/
	public static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_FOLLOWERS;
	private static final int CAN_LEFT_MOTOR = RobotMap.CAN_LEFT_MOTOR;
	private static final int CAN_RIGHT_MOTOR = RobotMap.CAN_RIGHT_MOTOR;
	private static final int CAN_LEFT_FOLLOWER = RobotMap.CAN_LEFT_FOLLOWER;
	private static final int CAN_RIGHT_FOLLOWER = RobotMap.CAN_RIGHT_FOLLOWER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	CANTalon leftFollower = new CANTalon(CAN_LEFT_FOLLOWER);
	CANTalon rightFollower = new CANTalon(CAN_RIGHT_FOLLOWER);	
	

	/** DrivetrainFollowers **************************************************/
	public DrivetrainFollowers() {
		log.add("Constructor", Log.Level.TRACE);
		
		initMotor(leftFollower, CAN_LEFT_MOTOR);
		initMotor(rightFollower, CAN_RIGHT_MOTOR);
	}
	private void initMotor(CANTalon motor, int leaderCANid) {
		motor.changeControlMode(TalonControlMode.Follower);
		motor.set(leaderCANid);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
	}
}

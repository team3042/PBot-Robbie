package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.robot.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DrivetrainFollowers **********************************************************
 * Motor controllers for secondary drivetrain motors
 */
public class Drivetrain_Followers extends Subsystem {
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
	public Drivetrain_Followers() {
		log.add("Constructor", Log.Level.TRACE);
		
		leftFollower.changeControlMode(TalonControlMode.Follower);
		rightFollower.changeControlMode(TalonControlMode.Follower);

		leftFollower.set(CAN_LEFT_MOTOR);
		rightFollower.set(CAN_RIGHT_MOTOR);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
	}
}

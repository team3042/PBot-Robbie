 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3042.robot.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain_Encoders;


/** Drivetrain_Encoders_Dashboard *********************************************
 * Output encoder values to the SmartDashboard
 */
public class Drivetrain_Encoders_Dashboard extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_ENC_DASH;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Drivetrain_Encoders encoders = Robot.drivetrain.encoders;
	
	
	/** Drivetrain_encoders_Dashboard *****************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Drivetrain_Encoders_Dashboard() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(encoders);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		encoders.reset();
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		SmartDashboard.putNumber("Left Encoder", encoders.getLeft());
		SmartDashboard.putNumber("Right Encoder", encoders.getRight());
		SmartDashboard.putNumber("Left Speed (c/100ms)", encoders.getLeftSpeed());
		SmartDashboard.putNumber("Right Speed (c/100ms)", encoders.getRightSpeed());
	}
	
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return false;
	}

	
	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
	}

	
	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}

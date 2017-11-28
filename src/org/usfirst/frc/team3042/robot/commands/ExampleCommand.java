 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.robot.Logger;
import org.usfirst.frc.team3042.robot.Robot;

import static org.usfirst.frc.team3042.robot.Logger.*;
import static org.usfirst.frc.team3042.robot.RobotMap.*;
import static org.usfirst.frc.team3042.robot.RobotMap.Bot.*;


/** ExampleCommand ************************************************************
 * A template for commands.
 */
public class ExampleCommand extends Command {
	Logger log = new Logger(LOG_LEVEL_EXAMPLE_COMMAND, "ExampleCommand");
	
	
	/** ExampleCommand ********************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public ExampleCommand() {
		log.add("Constructor", TRACE);
		
		requires(Robot.exampleSubsystem);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", TRACE);
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
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
		log.add("End", TRACE);
	}

	
	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", TRACE);
	}
}

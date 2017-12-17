 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;


/** PanTiltSet ************************************************************
 * Set the Pan-Tilt servos to a specific position.
 */
public class PanTiltSet extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_PAN_TILT_SET;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	double panPosition, tiltPosition;
	
	
	/** PanTiltSet ************************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public PanTiltSet(double panPosition, double tiltPosition) {
		log.add("Constructor", Log.Level.TRACE);
		requires(Robot.panTilt);
		
		this.panPosition = panPosition;
		this.tiltPosition = tiltPosition;
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		Robot.panTilt.setPan(panPosition);
		Robot.panTilt.setTilt(tiltPosition);
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
		return true;
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

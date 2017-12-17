 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.triggers.POVButton;


/** PanTiltDrive ************************************************************
 * Drive the Pan-Tilt servos using the POV buttons on the gamepad.
 * 
 * SERVO_SPEED determines how fast the servos move, measured per second.
 */
public class PanTiltDrive extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_PAN_TILT_DRIVE;
	private static final double PAN_CENTER = RobotMap.PAN_CENTER;
	private static final double TILT_CENTER = RobotMap.TILT_CENTER;
	private static final double SERVO_SPEED = RobotMap.SERVO_SPEED;
	private static final boolean REVERSE_PAN = RobotMap.REVERSE_PAN;
	private static final boolean REVERSE_TILT = RobotMap.REVERSE_TILT;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	double panPosition, tiltPosition;
	Timer timer = new Timer();
	
	
	/** PanTiltDrive ************************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public PanTiltDrive() {
		log.add("Constructor", Log.Level.TRACE);
		requires(Robot.panTilt);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);

		timer.start();
		timer.reset();
	
		panPosition = PAN_CENTER;
		tiltPosition = TILT_CENTER;
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		double dt = timer.get();
		timer.reset();
		
		double delta = SERVO_SPEED * dt;
		double panDelta = (REVERSE_PAN) ? -delta : delta;
		double tiltDelta = (REVERSE_TILT) ? -delta : delta;
		
		int pov = Robot.oi.getPOV();
		if ( (pov == POVButton.UP) || (pov == POVButton.UP_LEFT) || 
				(pov == POVButton.UP_RIGHT) ) {
			tiltPosition -= tiltDelta;
		}
		if ( (pov == POVButton.DOWN) || (pov == POVButton.DOWN_LEFT) || 
				(pov == POVButton.DOWN_RIGHT) ) {
			tiltPosition += tiltDelta;
		}
		if ( (pov == POVButton.LEFT) || (pov == POVButton.DOWN_LEFT) ||
				(pov == POVButton.UP_LEFT) ) {
			panPosition -= panDelta;
		}
		if ( (pov == POVButton.RIGHT) || (pov == POVButton.DOWN_RIGHT) ||
				(pov == POVButton.UP_RIGHT) ) {
			panPosition += panDelta;
		}
		
		panPosition = safetyCheck(panPosition);
		tiltPosition = safetyCheck(tiltPosition);
		
		Robot.panTilt.setPan(panPosition);
		Robot.panTilt.setTilt(tiltPosition);
	}
	
	
	/** safetyCheck ***********************************************************
	 * Make sure the pan and tilt position do not exceed bounds.
	 */
	private double safetyCheck(double position) {
		position = Math.min(position,  1.0);
		position = Math.max(position,  0.0);
		return position;
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
		
		timer.stop();
	}

	
	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);

		timer.stop();
	}
}

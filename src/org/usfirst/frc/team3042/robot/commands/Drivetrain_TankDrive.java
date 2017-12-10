 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.robot.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;


/** Drivetrain_TankDrive ************************************************************
 * Using joystick input to drive the robot.
 */
public class Drivetrain_TankDrive extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_TANKDRIVE;
	private static final double ACCELERATION_MAX = RobotMap.ACCELERATION_MAX;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Drivetrain drivetrain = Robot.drivetrain;
	double leftPowerOld, rightPowerOld, timeOld;
	Timer timer = new Timer();
	
	
	/** Drivetrain_TankDrive ********************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Drivetrain_TankDrive() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(drivetrain);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		drivetrain.setModePercentVbus();
		
		drivetrain.stop();
		leftPowerOld = 0.0;
		rightPowerOld = 0.0;
		
		timer.start();
		timeOld = timer.get();
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		double leftPower = Robot.oi.getDriveLeft();
		double rightPower = Robot.oi.getDriveRight();
		
		double time = timer.get();
		double dt = time - timeOld;
		leftPower = restrictAcceleration(leftPower, leftPowerOld, dt);
		rightPower = restrictAcceleration(rightPower, rightPowerOld, dt);	
		
		drivetrain.setMotors(leftPower, rightPower);
		
		leftPowerOld = leftPower;
		rightPowerOld = rightPower;
		timeOld = time;
	}
	
	
	/** restrictAcceleration **************************************************/
	private double restrictAcceleration(double goalPower, 
			double currentPower, double dt) {
		double maxDeltaPower = ACCELERATION_MAX * dt;
		double deltaPower = Math.abs(goalPower - currentPower);
		double deltaSign = (goalPower < currentPower) ? -1.0 : 1.0;
		
		deltaPower = Math.min(maxDeltaPower, deltaPower);
		goalPower = currentPower + deltaSign * deltaPower;

		return goalPower;
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
		drivetrain.stop();
	}

	
	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		drivetrain.stop();
	}
}

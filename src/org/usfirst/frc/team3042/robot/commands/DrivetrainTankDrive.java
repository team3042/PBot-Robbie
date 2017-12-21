 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.OI;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;


/** DrivetrainTankDrive *******************************************************
 * Using joystick input to drive the robot.
 */
public class DrivetrainTankDrive extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN_TANKDRIVE;
	private static final double ACCELERATION_MAX = RobotMap.ACCELERATION_MAX;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Drivetrain drivetrain = Robot.drivetrain;
	OI oi = Robot.oi;
	double leftPowerOld, rightPowerOld;
	Timer timer = new Timer();
	
	
	/** DrivetrainTankDrive ***************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public DrivetrainTankDrive() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(drivetrain);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
				
		drivetrain.setPower(0.0, 0.0);
		leftPowerOld = 0.0;
		rightPowerOld = 0.0;
		
		timer.start();
		timer.reset();
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		double leftPower = oi.getDriveLeft();
		double rightPower = oi.getDriveRight();
		
		double dt = timer.get();
		timer.reset();
		leftPower = restrictAcceleration(leftPower, leftPowerOld, dt);
		rightPower = restrictAcceleration(rightPower, rightPowerOld, dt);	
		
		drivetrain.setPower(leftPower, rightPower);
		
		leftPowerOld = leftPower;
		rightPowerOld = rightPower;
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
		drivetrain.setPower(0.0, 0.0);
	}

	
	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		drivetrain.setPower(0.0, 0.0);
	}
}
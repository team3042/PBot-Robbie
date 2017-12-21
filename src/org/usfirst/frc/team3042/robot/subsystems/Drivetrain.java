package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.DrivetrainTankDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Drivetrain ****************************************************************
 * The drivetrain subsystem for the robot.
 */
public class Drivetrain extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DRIVETRAIN;
	private static final int CAN_LEFT_MOTOR = RobotMap.CAN_LEFT_MOTOR;
	private static final int CAN_RIGHT_MOTOR = RobotMap.CAN_RIGHT_MOTOR;
	private static final boolean HAS_FOLLOWERS = RobotMap.HAS_FOLLOWERS;
	private static final boolean HAS_ENCODERS = RobotMap.HAS_ENCODERS;
	private static final boolean HAS_AUTON = RobotMap.HAS_AUTON;
	private static final boolean BRAKE_MODE = RobotMap.DRIVETRAIN_BRAKE_MODE;
	private static final boolean REVERSE_LEFT_MOTOR = RobotMap.REVERSE_LEFT_MOTOR;
	private static final boolean REVERSE_RIGHT_MOTOR = RobotMap.REVERSE_RIGHT_MOTOR;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	CANTalon leftMotor = new CANTalon(CAN_LEFT_MOTOR);
	CANTalon rightMotor = new CANTalon(CAN_RIGHT_MOTOR);
	DrivetrainFollowers followers;
	DrivetrainEncoders encoders;
	DrivetrainAuton auton;

	
	/** Drivetrain ************************************************************
	 * Set up the talons for desired behavior.
	 */
	public Drivetrain() {
		log.add("Constructor", LOG_LEVEL);
		
		if (HAS_FOLLOWERS) followers = new DrivetrainFollowers();
		if (HAS_ENCODERS) {
			encoders = new DrivetrainEncoders(leftMotor, rightMotor);
			
			if (HAS_AUTON) auton = new DrivetrainAuton();
		}
		
		initMotor(leftMotor, REVERSE_LEFT_MOTOR);
		initMotor(rightMotor, REVERSE_RIGHT_MOTOR);
	}
	private void initMotor(CANTalon motor, boolean reverse) {
		motor.enableBrakeMode(BRAKE_MODE);
		motor.setInverted(reverse); 	// affects percent Vbus mode
		motor.reverseOutput(reverse); 	// affects closed-loop mode
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainTankDrive());
	}
	
	
	/** Methods for setting the motors in Percent Vbus mode ********************/
	public void setPower(double leftPower, double rightPower) {
		leftPower = safetyCheck(leftPower);
		rightPower = safetyCheck(rightPower);
				
		leftMotor.changeControlMode(TalonControlMode.PercentVbus);
		rightMotor.changeControlMode(TalonControlMode.PercentVbus);

		leftMotor.set(leftPower);
		rightMotor.set(rightPower);		
	}
	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}
	
	
	/** Provide commands access to the encoders *******************************/
	public DrivetrainEncoders getEncoders() {
		return encoders;
	}
}

package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_TankDrive;

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
	Drivetrain_Followers followers;
	public Drivetrain_Encoders encoders;
	public Drivetrain_Auton auton;
	{
		if (HAS_FOLLOWERS) followers = new Drivetrain_Followers();
		if (HAS_ENCODERS) encoders = new Drivetrain_Encoders(leftMotor, rightMotor);
		if (HAS_AUTON) auton = new Drivetrain_Auton(leftMotor, rightMotor, encoders);
	}

	
	/** Drivetrain ************************************************************
	 * Set up the talons for desired behavior.
	 */
	public Drivetrain() {
		log.add("Constructor", LOG_LEVEL);	
		
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
		setDefaultCommand(new Drivetrain_TankDrive());
	}
	
	
	/** Methods for setting the motors in Percent Vbus mode ********************/
	public void setModePercentVbus() {
		leftMotor.changeControlMode(TalonControlMode.PercentVbus);
		rightMotor.changeControlMode(TalonControlMode.PercentVbus);
	}
	public void stop() {
		setMotors(0.0, 0.0);
	}
	public void setMotors(double leftPower, double rightPower) {
		leftPower = safetyCheck(leftPower);
		rightPower = safetyCheck(rightPower);
				
		leftMotor.set(leftPower);
		rightMotor.set(rightPower);		
	}
	private double safetyCheck(double motorPower) {
		motorPower = Math.min(1.0, motorPower);
		motorPower = Math.max(-1.0, motorPower);
		return motorPower;
	}
}

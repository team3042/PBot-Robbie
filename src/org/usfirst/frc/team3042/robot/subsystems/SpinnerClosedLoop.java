package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;


/** SpinnerClosedLoop *********************************************************/
public class SpinnerClosedLoop extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER_CLOSED_LOOP;
	private static final int POSITION_PROFILE = RobotMap.SPINNER_POSITION_PROFILE;
	private static final double kP_POSITION = RobotMap.kP_SPINNER_POSITION;
	private static final double kI_POSITION = RobotMap.kI_SPINNER_POSITION;
	private static final double kD_POSITION = RobotMap.kD_SPINNER_POSITION;
	private static final double kF_POSITION = 0.0;
	private static final int SPEED_PROFILE = RobotMap.SPINNER_SPEED_PROFILE;
	private static final double kP_SPEED = RobotMap.kP_SPINNER_SPEED;
	private static final double kI_SPEED = RobotMap.kI_SPINNER_SPEED;
	private static final double kD_SPEED = RobotMap.kD_SPINNER_SPEED;
	private static final double kF_SPEED = RobotMap.kF_SPINNER_SPEED;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	CANTalon motor;
	SpinnerEncoder encoder;
	
	
	/** SpinnerClosedLoop *****************************************************/
	public SpinnerClosedLoop(CANTalon spinnerMotor, SpinnerEncoder spinnerEncoder) {
		log.add("Constructor", LOG_LEVEL);
		
		motor = spinnerMotor;
		encoder = spinnerEncoder;
		
		motor.setProfile(POSITION_PROFILE);
		motor.setPID(kP_POSITION, kI_POSITION, kD_POSITION);
		motor.setF(kF_POSITION);
		
		motor.setProfile(SPEED_PROFILE);
		motor.setPID(kP_SPEED, kI_SPEED, kD_SPEED);
		motor.setF(kF_SPEED);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		//setDefaultCommand(new ExampleCommand());
	}


	/** Spinner Closed-Loop Control *******************************************
	 * Units for speed is RPM
	 * Units for Position is revolutions
	 * These units are leveraged because of a call to 
	 * configEncoderCodesPerRev(COUNTS_PER_REV) in the SpinnerEncoder class.
	 */
	public void setSpeed(double speed){
		log.add("Speed", speed, LOG_LEVEL);
		
		motor.setProfile(SPEED_PROFILE);
		motor.changeControlMode(TalonControlMode.Speed);
		motor.set(speed);
		
	}
	public void setPosition(double position) {
		position += encoder.getPositionZero();

		motor.setProfile(POSITION_PROFILE);
		motor.changeControlMode(TalonControlMode.Position);
		motor.set(position);
	}
}

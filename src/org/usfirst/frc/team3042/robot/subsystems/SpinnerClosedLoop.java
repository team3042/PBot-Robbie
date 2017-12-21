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
	private static final int PROFILE = RobotMap.SPINNER_PROFILE;
	private static final double kP = RobotMap.kP_SPINNER;
	private static final double kI = RobotMap.kI_SPINNER;
	private static final double kD = RobotMap.kD_SPINNER;
	private static final double kF = RobotMap.kF_SPINNER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	CANTalon motor;
	SpinnerEncoder encoder;
	
	
	/** SpinnerClosedLoop *****************************************************/
	public SpinnerClosedLoop(CANTalon spinnerMotor, SpinnerEncoder spinnerEncoder) {
		log.add("Constructor", LOG_LEVEL);
		
		motor = spinnerMotor;
		encoder = spinnerEncoder;
		
		motor.setProfile(PROFILE);
		motor.setPID(kP, kI, kD);
		motor.setF(kF);
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
		motor.setProfile(PROFILE);
		motor.changeControlMode(TalonControlMode.Speed);
		motor.set(speed);
		
	}
	public void setPosition(double position) {
		position += encoder.getPositionZero();
		
		motor.setProfile(PROFILE);
		motor.changeControlMode(TalonControlMode.Position);
		motor.set(position);
	}
}

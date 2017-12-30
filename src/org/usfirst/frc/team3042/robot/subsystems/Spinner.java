package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Spinner_Drive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Spinner *******************************************************************/
public class Spinner extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_SPINNER;
	private static final int CAN_SPINNER = RobotMap.CAN_SPINNER;
	private static final boolean BRAKE_MODE = RobotMap.SPINNER_BRAKE_MODE;
	private static final boolean REVERSE_SPINNER = RobotMap.REVERSE_SPINNER;
	private static final boolean HAS_ENCODER = RobotMap.HAS_SPINNER_ENCODER;
	private static final boolean HAS_CLOSED_LOOP = RobotMap.HAS_SPINNER_CLOSED_LOOP;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	public CANTalon motor = new CANTalon(CAN_SPINNER);
	SpinnerEncoder encoder;
	public SpinnerClosedLoop closedLoop;
	
	
	/** Spinner ***************************************************************/
	public Spinner() {
		log.add("Constructor", LOG_LEVEL);
		
		if (HAS_ENCODER) {
			encoder = new SpinnerEncoder(motor);
			
			if (HAS_CLOSED_LOOP) closedLoop = new SpinnerClosedLoop(motor, encoder);
		}
		
		motor.enableBrakeMode(BRAKE_MODE);
		motor.setInverted(REVERSE_SPINNER); 	// affects percent Vbus mode
		motor.reverseOutput(REVERSE_SPINNER); 	// affects closed-loop mode
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Spinner_Drive());
	}
	
	
	/** Command Control *******************************************************/
	public void setPower(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);

		motor.changeControlMode(TalonControlMode.PercentVbus);
		motor.set(power);
	}
	public void stop() {
		setPower(0.0);
	}
	
	
	/** Provide commands access to the encoder ********************************/
	public SpinnerEncoder getEncoder() {
		return encoder;
	}
}

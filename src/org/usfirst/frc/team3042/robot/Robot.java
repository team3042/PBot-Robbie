package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.I2CRangeSensor;
import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.commands.ExampleCommand;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3042.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3042.robot.subsystems.Gyroscope;
import org.usfirst.frc.team3042.robot.subsystems.LightRing;
import org.usfirst.frc.team3042.robot.subsystems.PanTilt;
import org.usfirst.frc.team3042.robot.subsystems.Spinner;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/** Robot *********************************************************************
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot { 
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ROBOT;
	private static final boolean HAS_DRIVETRAIN = RobotMap.HAS_DRIVETRAIN;
	private static final boolean HAS_PAN_TILT = RobotMap.HAS_PAN_TILT;
	private static final boolean HAS_GYROSCOPE = RobotMap.HAS_GYROSCOPE;
	private static final boolean HAS_LIGHT_RING = RobotMap.HAS_LIGHT_RING;
	private static final boolean HAS_SPINNER = RobotMap.HAS_SPINNER;
	
	
	/** Create Subsystems *****************************************************/
	private Log log = new Log(LOG_LEVEL, "Robot");
	public static final Drivetrain 	drivetrain 	= (HAS_DRIVETRAIN) 	? new Drivetrain() 	: null;
	public static final PanTilt 	panTilt 	= (HAS_PAN_TILT) 	? new PanTilt() 	: null;
	public static final Spinner 	spinner 	= (HAS_SPINNER) 	? new Spinner() 	: null;
	public static final Gyroscope 	gyroscope 	= (HAS_GYROSCOPE) 	? new Gyroscope() 	: null;
	public static final LightRing 	lightRing 	= (HAS_LIGHT_RING) 	? new LightRing() 	: null;
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public I2CRangeSensor range = new I2CRangeSensor(I2C.Port.kOnboard);

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<Command>();

	
	/** robotInit *************************************************************
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		log.add("Robot Init", Log.Level.TRACE);
		
		oi = new OI();
		chooser.addDefault("Default Auto", new ExampleCommand());
		chooser.addObject("My Auto", new ExampleCommand());
		SmartDashboard.putData("Auto Mode", chooser);
		
		SmartDashboard.putNumber("Gyro P", RobotMap.kP_GYRO);
		SmartDashboard.putNumber("Gyro I", RobotMap.kI_GYRO);
		SmartDashboard.putNumber("Gyro D", RobotMap.kD_GYRO);
	}

	
	/** disabledInit **********************************************************
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		log.add("Disabled Init", Log.Level.TRACE);
	}

	
	/** disabledPeriodic ******************************************************
	 * Called repeatedly while the robot is is disabled mode.
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	
	/** autonomousInit ********************************************************
	 * Run once at the start of autonomous mode.
	 */
	public void autonomousInit() {
		log.add("Autonomous Init", Log.Level.TRACE);
		
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	
	/** autonomousPeriodic ****************************************************
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	
	/** teleopInit ************************************************************
	 * This function is called when first entering teleop mode.
	 */
	public void teleopInit() {
		log.add("Teleop Init", Log.Level.TRACE);
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}


	/** teleopPeriodic ********************************************************
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("I2C ValueOptical", range.readOptical());
		SmartDashboard.putNumber("I2C ValueUltralsonic", range.readUltrasonic());
	}

	
	/** testPeriodic **********************************************************
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
	}
}

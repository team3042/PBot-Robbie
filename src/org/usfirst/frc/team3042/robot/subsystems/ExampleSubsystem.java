package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.robot.Logger;
import org.usfirst.frc.team3042.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

import static org.usfirst.frc.team3042.robot.Logger.*;
import static org.usfirst.frc.team3042.robot.RobotMap.*;
import static org.usfirst.frc.team3042.robot.RobotMap.Bot.*;


/** ExampleSubsystem **********************************************************
 * A subsystem template
 */
public class ExampleSubsystem extends Subsystem {
	Logger log = new Logger(LOG_LEVEL_EXAMPLE_SUBSYSTEM, 
			"ExampleSubsystem");
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new ExampleCommand());
	}
}

package org.usfirst.frc.team3042.robot;

import static org.usfirst.frc.team3042.robot.Log.*;
import static org.usfirst.frc.team3042.robot.RobotMap.*;

import org.usfirst.frc.team3042.robot.commands.ExampleCommand;
import org.usfirst.frc.team3042.robot.triggers.AxisTrigger;
import org.usfirst.frc.team3042.robot.triggers.POVButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;


/** OI ************************************************************************
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Log log = new Log(LOG_LEVEL_OI, "OI");
	
	public Joystick gamepad = new Joystick(USB_GAMEPAD);

	Button gamepad_A = new JoystickButton(gamepad, 1);
	Button gamepad_B = new JoystickButton(gamepad, 2);
	Button gamepad_X = new JoystickButton(gamepad, 3);
	Button gamepad_Y = new JoystickButton(gamepad, 4);
	Button gamepad_LB = new JoystickButton(gamepad, 5);
	Button gamepad_RB = new JoystickButton(gamepad, 6);
	Button gamepad_Back = new JoystickButton(gamepad, 7);
	Button gamepad_Start = new JoystickButton(gamepad, 8);
	Button gamepad_LeftJoy = new JoystickButton(gamepad, 9);
	Button gamepad_RightJoy = new JoystickButton(gamepad, 10);

	Trigger gamepad_LeftJoyLeft = new AxisTrigger(gamepad, 0, AxisTrigger.Direction.LEFT);
	Trigger gamepad_LeftJoyRight = new AxisTrigger(gamepad, 0, AxisTrigger.Direction.RIGHT);
	Trigger gamepad_LeftJoyUp = new AxisTrigger(gamepad, 1, AxisTrigger.Direction.UP);
	Trigger gamepad_LeftJoyDown = new AxisTrigger(gamepad, 1, AxisTrigger.Direction.DOWN);
	
	Trigger gamepad_LT = new AxisTrigger(gamepad,2);
	Trigger gamepad_RT = new AxisTrigger(gamepad,3);
	
	Trigger gamepad_RightJoyLeft = new AxisTrigger(gamepad, 4, AxisTrigger.Direction.LEFT);
	Trigger gamepad_RightJoyRight = new AxisTrigger(gamepad, 4, AxisTrigger.Direction.RIGHT);
	Trigger gamepad_RightJoyUp = new AxisTrigger(gamepad, 5, AxisTrigger.Direction.UP);
	Trigger gamepad_RightJoyDown = new AxisTrigger(gamepad, 5, AxisTrigger.Direction.DOWN);
	
	Trigger gamepad_POVUp = new POVButton(gamepad, POVButton.UP);
	Trigger gamepad_POVDown = new POVButton(gamepad, POVButton.DOWN);
	Trigger gamepad_POVLeft = new POVButton(gamepad, POVButton.LEFT);
	Trigger gamepad_POVRight = new POVButton(gamepad, POVButton.RIGHT);

	/** OI ********************************************************************
	 * Assign commands to the buttons and triggers
	 */
	public OI() {
		log.add("OI Constructor", Log.Level.TRACE);
		
		gamepad_A.whenPressed(new ExampleCommand());
		gamepad_B.toggleWhenPressed(new ExampleCommand());
		gamepad_X.whileHeld(new ExampleCommand());
		gamepad_Y.whenReleased(new ExampleCommand());
		gamepad_LT.toggleWhenActive(new ExampleCommand());
		gamepad_RT.whenActive(new ExampleCommand());
		gamepad_POVUp.whileActive(new ExampleCommand());
	}
}

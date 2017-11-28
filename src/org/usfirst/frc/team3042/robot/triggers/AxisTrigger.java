package org.usfirst.frc.team3042.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;


/** GamepadTrigger ************************************************************
 * A object that monitors gamepad triggers
 */
public class AxisTrigger extends Trigger {

	public static enum Direction{UP, DOWN, LEFT, RIGHT;}

	Joystick gamepad;
	int axis;
	Direction direction;

	/** GamepadTrigger ******************************************************** 
	 * Short version assumes positive values, which is equivalent to the DOWN 
	 * direction. Useful for gamepad triggers.
	 * 
	 * Joystick	joystick	The controller to monitor for a trigger
	 * int		axis		The joystick axis to monitor
	 * DIRECTION	
	 */
	public AxisTrigger(Joystick joystick, int axis){
		this(joystick, axis, Direction.DOWN);
	}
	public AxisTrigger(Joystick gamepad, int axis, Direction direction) {
		this.gamepad = gamepad;
		this.axis = axis;
		this.direction = direction;
	}

	/** get *******************************************************************
	 * Return if the axis has been pushed to trigger the command.
	 */
    public boolean get() {
    	boolean triggered;
    	if ( (direction == Direction.UP) || (direction == Direction.LEFT) ){
    		triggered = gamepad.getRawAxis(axis) < -.5;
    	}
    	else {
    		triggered = gamepad.getRawAxis(axis) > .5;
    	}
    	return triggered;
    }
}

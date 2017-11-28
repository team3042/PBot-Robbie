package org.usfirst.frc.team3042.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;


/** POVButton *****************************************************************
 * Creates a trigger from a POV button.
 */
public class POVButton extends Trigger {
	public static final int UP = 0;
	public static final int UP_RIGHT = 45;
	public static final int RIGHT = 90;
	public static final int DOWN_RIGHT = 135;
	public static final int DOWN = 180;
	public static final int DOWN_LEFT = 225;
	public static final int LEFT = 270;
	public static final int UP_LEFT = 315;
	
	int direction;
	Joystick gamepad;
	
	
	/** POVBUtton *************************************************************
	 * Joystick	gamepad		The gamepad to monitor
	 * int 		direction	The angle, in degrees, of the POV hat that 
	 * 						triggers action
	 */
	public POVButton (Joystick gamepad, int direction) {
		this.gamepad = gamepad;
		this.direction = direction;
	}
	
	
	/** get *******************************************************************
	 * Returns true if the POV hat is pressed in the specified direction.
	 */
    public boolean get() {
        return gamepad.getPOV() == direction;
    }
}

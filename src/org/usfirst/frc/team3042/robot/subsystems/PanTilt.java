package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.robot.Log;
import org.usfirst.frc.team3042.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;


/** PanTilt *******************************************************************
 * A subsystem to run the servos controlling the camera pan-tilt mechanism.
 */
public class PanTilt extends Subsystem {
	
	
	/** Instance Variables ****************************************************/
	private final Log log;
	private final Servo pan, tilt;
	
	
	/** PanTilt Builder *******************************************************
	 * Builder class for PanTilt
	 */
	public static class Build {
		private int panPort = -1;
		public Build panPort(int port) {panPort = port; return this;}
		
		private int tiltPort = -1;
		public Build tiltPort(int port) {tiltPort = port; return this;}
		
		private double PWMmax = 0;
		public Build PWMmax(double max) {PWMmax = max; return this;}
		
		private double PWMmin = 0;
		public Build PWMmin(double min) {PWMmin = min; return this;}
				
		private Log.Level logLevel = Log.Level.OFF;
		public Build logLevel(Log.Level level) {logLevel = level; return this;}
		
		public PanTilt build() {
			return new PanTilt(panPort, tiltPort, PWMmax, PWMmin, logLevel);
		}		
	}
	
	
	/** PanTilt ***************************************************************
	 * Constructor for the camera Pan-Tilt subsystem.
	 * Resets the PWM bounds for the particular servos being used.
	 */
	private PanTilt(int panPort, int tiltPort, double PWMmax, double PWMmin, 
			Log.Level logLevel) {
		
		log =  new Log(logLevel, "PanTilt");		
		log.add("Constructor", Log.Level.TRACE);

		if (panPort >= 0) {
			pan = new Servo(panPort);
		}
		else { 
			log.add("Pan servo port not set", Log.Level.ERROR);
			pan = null;
		}
		
		if (tiltPort >= 0) {
			tilt = new Servo(tiltPort);
		}
		else {
			log.add("Tilt servo port not set", Log.Level.ERROR);
			tilt = null;
		}
		
		if ( (PWMmax > 0) && (PWMmin > 0) ) {
			pan.setBounds(PWMmax, 0, 0, 0, PWMmin);
			tilt.setBounds(PWMmax, 0, 0, 0, PWMmin);
		}
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new ExampleCommand());
	}
}

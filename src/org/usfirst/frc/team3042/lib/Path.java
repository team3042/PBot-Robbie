package org.usfirst.frc.team3042.lib;

import java.util.ArrayList;

import org.usfirst.frc.team3042.robot.RobotMap;

/** Path **********************************************************************/
public class Path {
	/** Configurations Constants **********************************************/
	private static final double ROBOT_WIDTH = RobotMap.ROBOT_WIDTH;
	private static final double CIRCUMFRENCE = Math.PI * RobotMap.WHEEL_DIAMETER;

	
	/** Instance Variables ****************************************************/
	ArrayList<Double> leftDistance = new ArrayList<Double>();
	ArrayList<Double> rightDistance = new ArrayList<Double>();
	ArrayList<Double> leftSpeed = new ArrayList<Double>();
	ArrayList<Double> rightSpeed = new ArrayList<Double>();
	

	/** addStraight ***********************************************************
	 * distance: physical unit matching that of the wheel diameter constant
	 * speed: physical unit of distance per second.
	 * 
	 * Converted to revolutions and rps for the motion profile
	 * Direction is determined by the sign of speed.
	 */
	public void addStraight(double distance, double speed) {
		distance = convertDistance(distance);
		speed = convertSpeed(speed);
		
		leftDistance.add(distance);
		rightDistance.add(distance);
		leftSpeed.add(speed);
		rightSpeed.add(speed);
	}
	private double convertDistance(double distance) {
		distance = Math.abs(distance);
		return distance / CIRCUMFRENCE;
	}
	private double convertSpeed(double speed) {
		return speed / CIRCUMFRENCE;
	}
	
	
	/** Add Turn **************************************************************
	 * angle: degrees
	 * radius: physical unit matching that of the wheel diameter constant
	 * speed: physical unit of distance per second
	 * 
	 * Converted to revolutions and rps for the motion profile.
	 * Direction is determined by the sign of speed.
	 */
	public void addLeftTurn(double angle, double radius, double speed) {
		double distance = convertDistance(angle, radius);
		speed = convertSpeed(speed);
		
		double outerScale = outerScale(radius);
		double innerScale = innerScale(radius);
		
		leftDistance.add(distance * Math.abs(innerScale));
		leftSpeed.add(speed * innerScale);
		rightDistance.add(distance * outerScale);
		rightSpeed.add(speed * outerScale);
	}
	public void addRightTurn(double angle, double radius, double speed) {
		double distance = convertDistance(angle, radius);
		speed = convertSpeed(speed);
		
		double outerScale = outerScale(radius);
		double innerScale = innerScale(radius);
		
		leftDistance.add(distance * outerScale);
		leftSpeed.add(speed * outerScale);
		rightDistance.add(distance * Math.abs(innerScale));
		rightSpeed.add(speed * innerScale);
	}
	private double convertDistance(double angle, double radius) {
		angle *= Math.PI / 180.0; //convert to radians
		double distance = radius * angle;
		return convertDistance(distance); // convert to revolutions
	}
	private double outerScale(double radius) {
		radius = Math.abs(radius);
		double outerRadius = radius + 0.5 * ROBOT_WIDTH;
		return outerRadius / radius;
	}
	private double innerScale(double radius) {
		radius = Math.abs(radius);
		double innerRadius = radius - 0.5 * ROBOT_WIDTH;
		return innerRadius / radius;
	}
	
	
	/** Generate Motion Profile Paths *****************************************/
	public MotionProfile generateLeftPath() {
		return new MotionProfile(convert(leftDistance), convert(leftSpeed));
	}
	public MotionProfile generateRightPath() {
		return new MotionProfile(convert(rightDistance), convert(rightSpeed));
	}
	private double[] convert(ArrayList<Double> input) {
		int length = input.size();
		double[] output = new double[length];
		for (int n=0; n<length; n++) {
			output[n] = input.get(n);
		}
		return output;
	}
}

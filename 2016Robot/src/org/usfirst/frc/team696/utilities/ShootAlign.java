package org.usfirst.frc.team696.utilities;

import org.usfirst.frc.team696.robot.Robot;

public class ShootAlign {

	/*
	 * highest and lowest points of view on the camera
	 * in pixels
	 */
	double highestPoint = 100.0;
	double lowestPoint = 0.0;

	/*
	 * furthest left and furthest right points on the 
	 * camera in pixels
	 */
	double farLeftPoint = 0.0;
	double farRightPoint = 100.0;
	
	/*
	 * the current views of the camera in the vertical
	 * and horizontal directions in pixels
	 */
	double currentViewVertical = 0;
	double currentViewHorizontal = 0;
	
	public void run(){
		//set the current views in the horizontal and 
		//vertical directions
		currentViewVertical = new GetCamVals().getCenterY();
		currentViewHorizontal = new GetCamVals().getCenterX();
		
		//checks if the robot is set to autoAlign
		if(Robot.autoAlign){
			calculateRPM();
			calculateSpeeds();
			calculateAngle();
		}
	}
	
	/*
	 * Shooter Wheel Speed
	 */
	public void calculateRPM() {
		double error = currentViewVertical - lowestPoint;
		Robot.isRPM = true;
		Robot.botShooterRPM = error * 45;
		Robot.topShooterRPM = error * 45;
	}
	
	/*
	 * calculate the left and right speeds with 
	 * PWM outputs
	 */
	public void calculateSpeeds() {
		double targetPoint = (farRightPoint - farLeftPoint)/2;
		double error = currentViewHorizontal - targetPoint;
		error = Util.deadZone(error, -1, 1, 0);
		Robot.leftSpeed-=(error*0.1);
		Robot.rightSpeed+=(error*0.1);
	}
	
	/*
	 * calculate the angle for the pivot
	 * in degrees
	 */
	public void calculateAngle() {
		double targetPoint = (highestPoint - lowestPoint)/2;
		double error = currentViewVertical - targetPoint;
		error = Util.deadZone(error, -1, 1, 0);
		Robot.targetAngle = error * 0.9;
	}
	
}

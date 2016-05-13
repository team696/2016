package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.PIDControl;
import org.usfirst.frc.team696.utilities.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	double kP = 0.0175;
	double kI = 0.0002;
	double kD = 0.0003;
	double alpha = 0.95;
	
	double direction = 0;
	double distance = 0;
	double speed = 0;
	double leftSpeed = 0;
	double rightSpeed = 0;
		
	double currentDirection = 0;
	double currentDistance = 0;
	
	double distanceError = 0;
	double directionError = 0;
	double oldDirectionError = 0;
	
	PIDControl directionPID = new PIDControl(kP, kI, kD, alpha);
	
	boolean isFinished = false;
	boolean firstRun = true;
	
    public Drive(double direction, double distance, double speed) {
    	requires(Robot.chassis);
    	this.direction = direction;
    	this.distance = distance;
    	this.speed = speed * -Util.signOf(distance);;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(firstRun){
    		direction = Robot.navX.getYaw() + direction;
    		Robot.leftEncoder.reset();
    		Robot.rightEncoder.reset();
    		firstRun = false;
    	}
    	
    	currentDistance = (Robot.leftEncoder.getDistance() + Robot.rightEncoder.getDistance()) / 2;
    	currentDirection = Robot.navX.getYaw();

    	distanceError = distance - currentDistance;
    	directionError = direction - currentDirection;
    	
    	if(directionError > 180)directionError = directionError - 360;
    	if(directionError < -180)directionError = directionError + 360;
    	
    	directionPID.setError(directionError);
    	
    	distanceError = Util.deadZone(distanceError, -0.5, 0.5, 0);
    	if(distanceError == 0)speed = 0;
    	
    	leftSpeed = speed;
    	rightSpeed = speed;
    	
    	leftSpeed-=directionPID.getValue();
    	rightSpeed+=directionPID.getValue();
    	
    	leftSpeed = Util.constrain(leftSpeed, -1, 1);
    	rightSpeed = Util.constrain(rightSpeed, -1, 1);
    	
    	Robot.chassis.setSpeeds(leftSpeed, rightSpeed);
    	
    	if(Math.abs(directionError) < 2 && Math.abs(oldDirectionError) < 2 && Math.abs(distanceError) == 0 && Math.abs(leftSpeed) < 0.25 && Math.abs(rightSpeed) < 0.25)isFinished = true;
    	
    	oldDirectionError = directionError;
    	
    	System.out.print("target distance: " + distance + "    currentDistance: " + currentDistance + "     target Angle: " + direction + "     cureent Angle: " + Robot.navX.getYaw());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.setSpeeds(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

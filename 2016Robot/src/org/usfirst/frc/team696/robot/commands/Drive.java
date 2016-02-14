package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	
	double leftSpeed;
	double rightSpeed;
	double goalDirection;
	double currentDirection;
	double goalDistance;
	double error;
	double kP;
	double speed;
	

    public Drive(double goalDirection, double speed, double goalDistance) {
    	requires(Robot.chassis);
    	this.goalDirection = goalDirection;
    	this.speed = speed;
    	this.goalDistance = goalDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentDirection = Robot.navX.getYaw();
    	error = (goalDirection - currentDirection) * kP;
    	leftSpeed = speed;
    	rightSpeed = speed;
    	leftSpeed+=error;
    	rightSpeed-=error;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if((Robot.leftEncoder.getDistance() + Robot.rightEncoder.getDistance())/2 > goalDistance)return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

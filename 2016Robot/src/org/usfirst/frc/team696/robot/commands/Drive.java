package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.PIDControl;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	double goalDirection = 0;
	double currentDirection = 0;
	double currentDistance = 0;
	double goalDistance = 0;
	double error = 0;
	double kP = 0.0105;
	double kI = 0.0002;
	double kD = 0.0003;
	double alpha = 0.95;
	double speed = 0;
	
	PIDControl PID = new PIDControl(kP, kI, kD, alpha);

    public Drive(double goalDirection, double speed, double goalDistance) {
    	requires(Robot.chassis);
    	this.goalDirection = goalDirection;
    	this.speed = speed;
    	this.goalDistance = goalDistance;
    }

    protected void initialize() {
    }

    protected void execute() {
    	currentDirection = Robot.navX.getYaw();
    	
    	currentDistance = (Robot.leftEncoder.getDistance() + Robot.rightEncoder.getDistance())*0.5;
    	
    	error = (goalDirection - currentDirection);
    	PID.setError(error);
    	speed = PID.getValue();
    	leftSpeed = speed;
    	rightSpeed = speed;

    }

    protected boolean isFinished() {
        if(Math.abs(goalDirection - currentDirection) < 5 || Math.abs(goalDistance - currentDistance) < 4)return true;
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

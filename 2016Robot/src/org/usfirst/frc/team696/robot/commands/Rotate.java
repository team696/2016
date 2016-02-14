package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate extends Command {

	double goalDirection;
	double currentDirection;
	double error;
	double k;
	double leftSpeed;
	double rightSpeed; 
	
    public Rotate(double goalDirection) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        this.goalDirection = goalDirection;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.currentDirection = Robot.navX.getYaw();
    	error = Util.deadZone((goalDirection - currentDirection), -5.0, 5.0, 0.0);
    	
    	leftSpeed = error*k;
    	rightSpeed = -error*k;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(error == 0) return true;
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

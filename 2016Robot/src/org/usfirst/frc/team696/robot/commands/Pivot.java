package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pivot extends Command {
	
	boolean isAngle = false;
	double value = 0;
	
    public Pivot(boolean isAngle, double value) {
    	requires(Robot.pivotArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pivotArm.usePID = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if(!isAngle)Robot.pivotArm.setSpeed(value);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (isAngle) return true;
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

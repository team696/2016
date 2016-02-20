package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pivot extends Command {
	boolean isAngle = false;
	boolean autoUnderLift = false;
	double value = 0;
	
    public Pivot(boolean isAngle, double value) {
    	requires(Robot.pivotArm);
    	this.isAngle = isAngle;
    	this.value = value;
    }

    public Pivot(boolean autoUnderLift){
    	requires(Robot.pivotArm);
    	this.autoUnderLift = autoUnderLift;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if(isAngle)Robot.pivotArm.togglePID(true);
    	else Robot.pivotArm.togglePID(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!isAngle)Robot.pivotArm.setSpeed(value);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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

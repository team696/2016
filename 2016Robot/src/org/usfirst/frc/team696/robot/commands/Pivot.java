package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pivot extends Command {
	boolean incremental = true;
	boolean autoUnderLift = false;
	double incrementValue = 0;
	double targetAngle = 0;
	
    public Pivot(boolean incremental, double value) {
    	if(!incremental)this.targetAngle = value;
    	else incrementValue = value;
    	this.incremental = incremental;
//    	System.out.println(this.targetAngle);
    } 

    public Pivot(boolean autoUnderLift){
    	this.autoUnderLift = autoUnderLift;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(incremental)Robot.targetAngle+=incrementValue;
    	if(!incremental)Robot.targetAngle = targetAngle;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

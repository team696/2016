package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pivot extends Command {
	boolean autoUnderLift = false;
	double targetAngle = 0;
	
    public Pivot(double value) {
    	requires(Robot.pivotArm);
    	this.targetAngle = value;
    }

    public Pivot(boolean autoUnderLift){
    	requires(Robot.pivotArm);
    	this.autoUnderLift = autoUnderLift;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pivotArm.togglePID(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	targetAngle+=2.5;
    	Robot.pivotArm.setTargetAngle(targetAngle);
    	System.out.println(targetAngle);
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

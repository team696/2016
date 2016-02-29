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
    } 

    public Pivot(boolean autoUnderLift){
    	this.autoUnderLift = autoUnderLift;
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	if(incremental)Robot.targetAngle+=incrementValue;
    	if(!incremental)Robot.targetAngle = targetAngle;
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

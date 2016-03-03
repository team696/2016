package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetPivot extends Command {
	boolean incremental = true;
	boolean autoUnderLift = false;
	double incrementValue = 0;
	double targetAngle = 0;
	double zeroPivotPos = 0;
	double climbingAngle = 0;
	double shootAtBatterPos = 0;
	
    public SetPivot(boolean incremental, double value) {
    	if(!incremental)this.targetAngle = value;
    	else incrementValue = value;
    	this.incremental = incremental;
    } 

    public SetPivot(boolean autoUnderLift){
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

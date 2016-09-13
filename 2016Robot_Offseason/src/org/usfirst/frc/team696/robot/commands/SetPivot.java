package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetPivot extends Command {
	boolean incremental = true;
	double incrementValue = 0;
	double targetAngle = 0;
	
	double constrainSpeed = 1;
	
	public SetPivot(){
		incremental = true;
		incrementValue = 0;
	}
	
	public SetPivot(boolean keepPosition){
		if(!keepPosition){
			incremental = true;
			incrementValue = 0;
		} else {
			targetAngle = Robot.pivotEncoder.getDistance();
		}
	}
	
    public SetPivot(double value) {
    	this.incremental = true;
    	incrementValue = value;
    }

    public SetPivot(double value, double constrainSpeed) {
    	incremental = false;
    	targetAngle = value;
    	this.constrainSpeed = constrainSpeed;
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	Robot.pivotConstrainSpeed = constrainSpeed;
    	
    	if(incremental)Robot.targetAngle+=incrementValue;
    	else Robot.targetAngle = targetAngle;
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

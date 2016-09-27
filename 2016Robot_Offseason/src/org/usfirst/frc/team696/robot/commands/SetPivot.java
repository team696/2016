package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.subsystems.PivotArmSystem;

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
			targetAngle = Robot.pivotEncoder.getDistance() + Robot.pivotK;
		}
	}
	
	public SetPivot(boolean incremental, double value){
		this.incremental = incremental;
		incrementValue = value;
	}
	
    public SetPivot(double value) {
    	this.incremental = false;
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
    	
    	if(Robot.safeMode){
    		if(incremental)Robot.targetAngle+=incrementValue;
    		else Robot.targetAngle = targetAngle;
    	} else {
    		if(incrementValue > 0)Robot.pivotArm.speed = 0.25;
    		if(incrementValue < 0)Robot.pivotArm.speed = -0.25;
    		if(incrementValue == 0)Robot.pivotArm.speed = 0;
    	}
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

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
	
	double zeroPivotPos = 5;
	double climbingAngle = 0;
	double shootAtBatterPos = 195;
	double twoWheelsAgainstBatter = 174;
	double distanceShot = 169;
	double partWay = 0;
	double speed = 0;
	double axisPos = 0;
	
    public SetPivot(boolean incremental, double value) {
    	if(!incremental && !Robot.useEncoder)axisPos = value;
    	else incrementValue = value;
    	this.incremental = incremental;
    } 

//    public SetPivot(boolean autoUnderLift){
//    	this.autoUnderLift = autoUnderLift;
//    }
    
//    public SetPivot(double speed){
//    	this.speed = speed;
//    }
    
    protected void initialize() {
    }

    protected void execute() {
    	if(axisPos < -0.8){
			targetAngle = shootAtBatterPos;
		} else if(axisPos < -0.4){
			targetAngle = twoWheelsAgainstBatter;
		} else if(axisPos < 0){
			targetAngle = distanceShot;
		}
//    	System.out.println("execute of SetPivot");
    	if(incremental)Robot.targetAngle+=incrementValue;
    	if(!incremental)Robot.targetAngle = targetAngle;
    	if(!Robot.useEncoder){
    		if(incrementValue > 0)Robot.pivotArm.setSpeed(0.1);
    		else if(incrementValue < 0)Robot.pivotArm.setSpeed(-0.1);
    		else Robot.pivotArm.setSpeed(0);
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

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
	double climbingAngle = 200;
	double generalPosition = 50;
	double shootAtBatterPos = 195;
//	double twoWheelsAgainstBatter = 174;
	double twoWheelsAgainstBatter = 185;
	double distanceShot = 169;
	double partWay = 0;
	double speed = 0;
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
	
    public SetPivot(boolean incremental, double value) {
    	System.out.println("SetPivot Constructor");
    	this.incremental = incremental;
    	if(!incremental){
    		switch((int)value){
    		case 0:
    			targetAngle = zeroPivotPos;
    			break;
    		case 1:
    			targetAngle = partWay;
    			break;
    		case 2:
    			targetAngle = distanceShot;
    			break;
    		case 3:
    			targetAngle = shootAtBatterPos;
    			break;
    		case 4:
    			targetAngle = climbingAngle;
    			break;
			default:
				targetAngle = zeroPivotPos;
				break;
    		}
    	}
    	else incrementValue = value;
    } 
    
    public SetPivot(double value) {
    	this.incremental = true;
    	incrementValue = value;
    }

    public SetPivot(double value, double constrainSpeed) {
    	switch((int)value){
		case 0:
			targetAngle = zeroPivotPos;
			break;
		case 1:
			targetAngle = partWay;
			break;
		case 2:
			targetAngle = distanceShot;
			break;
		case 3:
			targetAngle = shootAtBatterPos;
			break;
		case 4:
			targetAngle = climbingAngle;
			break;
		default:
			targetAngle = zeroPivotPos;
			break;
		}
    	incremental = false;
    	this.constrainSpeed = constrainSpeed;
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	Robot.pivotConstrainSpeed = constrainSpeed;
    	if(incremental)Robot.targetAngle+=incrementValue;
    	if(!incremental){
    		if(Robot.oi.arduino.getRawAxis(0) < -0.9 
    				&& Robot.oi.arduino.getRawAxis(0) > -1.1) Robot.targetAngle = distanceShot;
    		else if(Robot.oi.arduino.getRawAxis(0) < -0.5 
    				&& Robot.oi.arduino.getRawAxis(0) > -0.6) Robot.targetAngle = distanceShot;
    		else if(Robot.oi.arduino.getRawAxis(0) < 0
    				&& Robot.oi.arduino.getRawAxis(0) > -0.3) Robot.targetAngle = twoWheelsAgainstBatter;
    		else if(Robot.oi.arduino.getRawAxis(0) < 0.19
    				&& Robot.oi.arduino.getRawAxis(0) > 0.17) Robot.targetAngle = shootAtBatterPos;
    		else if(Robot.oi.arduino.getRawAxis(0) < 0.6
    				&& Robot.oi.arduino.getRawAxis(0) > 0.58) Robot.targetAngle = generalPosition;
    		else if(Robot.oi.arduino.getRawAxis(0) < 1.1
    				&& Robot.oi.arduino.getRawAxis(0) > 0.9) Robot.targetAngle = zeroPivotPos;
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

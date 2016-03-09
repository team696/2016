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
	double shootAtBatterPos = 195;
	double twoWheelsAgainstBatter = 174;
	double distanceShot = 169;
	double partWay = 0;
	double speed = 0;
	
	public SetPivot(){
		incremental = false;
		incrementValue = 0;
	}
	
    public SetPivot(double value) {
//    		switch((int)value){
//    		case 0:
//    			targetAngle = zeroPivotPos;
//    			break;
//    		case 1:
//    			targetAngle = partWay;
//    			break;
//    		case 2:
//    			targetAngle = distanceShot;
//    			break;
//    		case 3:
//    			targetAngle = shootAtBatterPos;
//    			break;
//    		case 4:
//    			targetAngle = climbingAngle;
//    			break;
//			default:
//				targetAngle = zeroPivotPos;
//				break;
//    		}
//    	}
    	incrementValue = value;
    	this.incremental = true;
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
    	System.out.println("execute of SetPivot");
    	if(incremental)Robot.targetAngle+=incrementValue;
    	if(!incremental){
    		if(Robot.oi.arduino.getRawAxis(0) < -0.9 
    				&& Robot.oi.arduino.getRawAxis(0) > -1.1) Robot.targetAngle = 3;
    		else if(Robot.oi.arduino.getRawAxis(0) < -0.5 
    				&& Robot.oi.arduino.getRawAxis(0) > -0.6) Robot.targetAngle = shootAtBatterPos;
    		else if(Robot.oi.arduino.getRawAxis(0) < 0
    				&& Robot.oi.arduino.getRawAxis(0) > -0.3) Robot.targetAngle = twoWheelsAgainstBatter;
    		else if(Robot.oi.arduino.getRawAxis(0) < 0.19
    				&& Robot.oi.arduino.getRawAxis(0) > 0.17) Robot.targetAngle = distanceShot;
    		else if(Robot.oi.arduino.getRawAxis(0) < 0.6
    				&& Robot.oi.arduino.getRawAxis(0) > 0.58) Robot.targetAngle = distanceShot;
    		else if(Robot.oi.arduino.getRawAxis(0) < 1.1
    				&& Robot.oi.arduino.getRawAxis(0) > 0.9) Robot.targetAngle = distanceShot;
    		
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

package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterSpeed extends Command {

	double topRPM = 0;
	double botRPM= 0;
	double topSpeed = 0;
	double botSpeed = 0;
	boolean isRPM = true;
	
    public SetShooterSpeed(boolean isRPM, double topVal, double botVal) {
    	if(isRPM){
	        this.topRPM = topVal;
	        this.botRPM = botVal;
    	} else {
    		this.topSpeed = topVal;
    		this.botSpeed = botVal;
    	}
        this.isRPM = isRPM;
    }
    
    public SetShooterSpeed(double RPM){
    	topRPM = RPM; 
    	botRPM = RPM;
    }
    
    public SetShooterSpeed(boolean isAxis, boolean isRPM, double value){
    	if(!isRPM){
    		if(isAxis){
	    		if(value > 0.7){
	    			topSpeed = 1;
	    			botSpeed = 1;
	    		} else if(value < -0.7){
	    			topSpeed = 0.8;
	    			botSpeed = 0.8;
	    		} else {
	    			topSpeed = 0.9;
	    			botSpeed = 0.9;
	    		}
    		} else {
    			topSpeed = value;
    			botSpeed = value;
    		}
    	} else {
    		if(isAxis){
    			if(value > 0.7){
	    			topRPM = 4500;
	    			botRPM = 4500;
	    		} else if(value < -0.7){
	    			topRPM = 4000;
	    			botRPM = 4000;
	    		} else {
	    			topRPM = 4250;
	    			botRPM = 4250;
	    		}
    		} else {
    			topRPM = value;
    			botRPM = value;
    		}
    	}
    	
//    	if(!isRPM){
//    		if(isAxis){
//	    		if(Robot.oi.arduino.getRawAxis(3)> 0.7){
//	    			topSpeed = 1;
//	    			botSpeed = 1;
//	    		} else if(Robot.oi.arduino.getRawAxis(3) < -0.7){
//	    			topSpeed = 0.8;
//	    			botSpeed = 0.8;
//	    		} else {
//	    			topSpeed = 0.9;
//	    			botSpeed = 0.9;
//	    		}
//    		} else {
//    			topSpeed = value;
//    			botSpeed = value;
//    		}
//    	} else {
//    		if(isAxis){
//    			if(Robot.oi.arduino.getRawAxis(3) > 0.7){
//	    			topRPM = 4500;
//	    			botRPM = 4500;
//	    		} else if(Robot.oi.arduino.getRawAxis(3) < -0.7){
//	    			topRPM = 4000;
//	    			botRPM = 4000;
//	    		} else {
//	    			topRPM = 4250;
//	    			botRPM = 4250;
//	    		}
//    		} else {
//    			topRPM = value;
//    			botRPM = value;
//    		}
//    	}
    	
    	
    	this.isRPM = isRPM;
    }
    

    protected void initialize() {
    }

    protected void execute() {
    	Robot.topShooterRPM = topRPM;
    	Robot.botShooterRPM = botRPM;
    	Robot.topShooterSpeed = topSpeed;
    	Robot.botShooterSpeed = botSpeed;
    	Robot.isRPM = isRPM;
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

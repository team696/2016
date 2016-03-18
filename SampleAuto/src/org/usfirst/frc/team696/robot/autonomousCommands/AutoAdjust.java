package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.utilities.GetCamVals;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAdjust extends Command {
	
	double area;
	double centerX;
	double centerY;
	double height;
	double width;
    
	double minAcceptableArea = 0;
	double maxAcceptableArea = 0;
	double minLeftRange = 0;
	double minRightRange = 0;
	double acceptableRange = 0;
	
	boolean inRange = false;
	boolean XinRange = false;
	
	boolean fire = false;
	
	GetCamVals camCaller = new GetCamVals();
	
    public AutoAdjust() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	area = camCaller.getArea();
        centerX = camCaller.getCenterX();
        centerY = camCaller.getCenterY();
        height = camCaller.getHeight();
        width = camCaller.getWidthVals();
        
    	if (Robot.leftEncoder.get() < acceptableRange) {
    		new Drive(Robot.navX.getYaw(), 1, 0.5);
    	} else inRange = true;
    	
    	if (inRange) {
    		if (area < minAcceptableArea && centerX < minLeftRange) {
    			new Drive(Robot.navX.getYaw()+5, 1, 0.25);
    		} else if (area < maxAcceptableArea && area > minAcceptableArea) {
    			fire = true;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return fire;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

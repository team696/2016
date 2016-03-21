package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.GetCamVals;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoAlign extends Command {

	GetCamVals camera = new GetCamVals();
	double degreesPerPixel = 0.1046875;
	int position = 0;
	double targetCenter = 320;
	double centerX = targetCenter;
	double leftSpeed = 0, rightSpeed = 0;
	double targetAngle = targetCenter * degreesPerPixel;
	
	boolean isFinished = true;
	boolean checkCameraPos = true;
	
    public AutoAlign() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	findGreatest(camera.getArea());
    	
    	if(checkCameraPos){
    		centerX = camera.getCenterX()[position];
    		checkCameraPos = false;
    		Robot.incrementTargetDirection = (targetCenter - centerX) * degreesPerPixel;
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void findGreatest(double[] array){
    	double largestVal = array[0];
    	for(int i = 1; i < array.length; i++) {
    		if(array[i] > largestVal){
    			largestVal = array[i];
    			position = i;
    		}
    	}
    }
}

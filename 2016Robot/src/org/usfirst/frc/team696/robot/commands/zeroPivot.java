package org.usfirst.frc.team696.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;


import org.usfirst.frc.team696.robot.subsystems.PivotArm;

/**
 *
**/

public class zeroPivot extends Command {

	boolean limitTrigger = false;
	boolean zeroing = false;
	
	double zeroingSpeed = 0.0;
	// Set this to a safe negative speed to reverse the direction of the motor

    public zeroPivot() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pivotArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	limitTrigger = Robot.pivotSwitch.get();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	limitTrigger = Robot.pivotSwitch.get();
    	if (limitTrigger == true) {
    		Robot.pivotArm.setSpeed(0.0);
    		isFinished();
    	} else {
    		Robot.pivotArm.setSpeed(zeroingSpeed);
    		zeroing = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (limitTrigger == true) {
        	zeroing = false;
        	return true;
        } else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
  
}

package org.usfirst.frc.team696.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team696.robot.subsystems.*;

/**
 *
**/

public class zeroPivot extends Command {
	
	DigitalInput zeroingLimitSwitch = new DigitalInput(RobotMap.pivotArmLimitSwitchChannel);
	PivotingArm zeroPivot = new PivotingArm();
	Timer time = new Timer();
	
	boolean limitTrigger = false;
	boolean zeroing = false;
	
	double zeroingSpeed = 0.0;
	// Set this to a safe negative speed to reverse the direction of the motor

    public zeroPivot() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pivot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	limitTrigger = zeroingLimitSwitch.get();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	limitTrigger = zeroingLimitSwitch.get();
    	if (limitTrigger == true) {
    		zeroPivot.setSpeed(0.0);
    		isFinished();
    	} else {
    		zeroPivot.setSpeed(zeroingSpeed);
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

package org.usfirst.frc.team696.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;


import org.usfirst.frc.team696.robot.subsystems.PivotArmSystem;

/**
 *
**/

public class zeroPivot extends Command {

	boolean limitTrigger = false;
	boolean zeroing = true;
	
	double zeroingSpeed = 0.0;

    public zeroPivot() {
        requires(Robot.pivotArm);
    }

    protected void initialize() {
    }

    protected void execute() {
    	zeroing = true;
    	if (!limitTrigger) Robot.pivotArm.setSpeed(zeroingSpeed);
    	else {
    		Robot.pivotArm.setSpeed(0);
    		zeroing = false;
    	}
    }

    protected boolean isFinished() {
       return !zeroing;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
  
}

package org.usfirst.frc.team696.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;


import org.usfirst.frc.team696.robot.subsystems.PivotArmSystem;

/**
 *
**/

public class ZeroPivot extends Command {

	boolean limitTrigger = false;
	
	double zeroingSpeed = -0.75;
	
	boolean isFinished = false;

    public ZeroPivot() {
        requires(Robot.pivotArm);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.pivotArm.ratchet(false);
    	
    	Robot.pivotArm.setSpeed(-0.75);
    	
    	if(Robot.PDP.getCurrent(7) > 50){
    		isFinished = true;
    		Robot.pivotArm.setSpeed(0);
    		System.out.println("HEHE");
    	}
    	System.out.println("I AM IN ZERO PIVOT CODE!@!!");
    }

    protected boolean isFinished() {
    	return isFinished;
    }

    protected void end() {
    	Robot.pivotArm.setSpeed(0);
    }

    protected void interrupted() {
    }
  
}

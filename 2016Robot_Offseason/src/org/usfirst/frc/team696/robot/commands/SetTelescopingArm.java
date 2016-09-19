package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetTelescopingArm extends Command {

	double target = 0;
	double fullyExtended = 720;
//	double fullyExtended = 500;
//	double halfExtended = 5;
	double halfExtended = 5;
	double fullyRetracted = 0;
	int whatState = 0;
	
    public SetTelescopingArm(int whatState) {
        System.out.println("SetTelescopingArm constructor");
        this.whatState = whatState;
    }
    
    public SetTelescopingArm(boolean increment, int val) {
    	if(increment){
    		target+=val;
    		if(val > 0)Robot.state = 2;
    		else Robot.state = 1;
    	}
    	else this.whatState = val;
    }
    	
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(whatState){
    	case 1:
    		target = halfExtended;
    		Robot.state = 3;
    		System.out.println("halfExtended");
    		break;
    	case 2:
    		target = fullyExtended;
    		Robot.startReleaseRatchetTimer = true;
    		Robot.state = 1;
    		System.out.println("fully extend");
    		break;
    	case 0:
		default:
			target = fullyRetracted;
			Robot.state = 3;
			System.out.println("full contract");
			break;
    	}
    	Robot.telescopingTargetDistance = target;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

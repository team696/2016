package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetTelescopingArm extends Command {

	double target = 0;
	double fullyExtended = 720;
	double halfExtended = 0;
	double fullyRetracted = -100;
	int whatState = 0;
	
    public SetTelescopingArm(int whatState) {
        this.whatState = whatState;
    }
    
    public SetTelescopingArm(boolean increment, int val) {
    	if(increment){
    		 target = Robot.telescopingEncoder.get() + val;
    		if(val < 0){
    			whatState = 3;
    		}
    	}
    	else this.whatState = val;
    }
    	
    protected void initialize() {
    }

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
    	case 3:
    		Robot.state = 3;
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

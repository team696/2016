package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetTelescopingArm extends Command {

	double target = 0;
	double fullyExtended = 1000;
	double halfExtended = 300;
	double fullyContracted = 0;
	
    public SetTelescopingArm(int whatTarget) {
        
    	switch(whatTarget){
    	case 1:
    		target = halfExtended;
    		break;
    	case 2:
    		target = fullyExtended;
    		break;
		default:
			target = fullyContracted;
			break;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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

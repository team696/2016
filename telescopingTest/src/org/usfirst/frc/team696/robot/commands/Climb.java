package org.usfirst.frc.team696.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team696.robot.Robot;

/**
 *
 */
public class Climb extends Command {
	
    public Climb() {
        requires(Robot.telescope);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if ((Robot.telescopingEncoder.getDistance() < Robot.telescope.maxExtension) && !Robot.telescope.pulling) {
    		Robot.telescope.deploy();
    	} else {
    		Robot.telescope.pulling = true;
    		Robot.telescope.stop();
    	}
    	
    	if ((Robot.telescopingEncoder.getDistance() > Robot.telescope.brakingDist) && Robot.telescope.pulling) {
    		Robot.telescope.pullUp();
    	} else if (Robot.telescopingEncoder.getDistance() <= Robot.telescope.brakingDist) {
    		Robot.telescope.stop();
    		Robot.telescope.finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.telescope.finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

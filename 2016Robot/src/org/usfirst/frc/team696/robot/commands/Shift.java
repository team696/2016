package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shift extends Command {

	boolean shiftHigh = true;
	
    public Shift(boolean shiftHigh) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shifter);
        this.shiftHigh = shiftHigh;
    }
    
    

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(shiftHigh)Robot.shifter.shiftHigh();
    	else Robot.shifter.shiftLow();
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

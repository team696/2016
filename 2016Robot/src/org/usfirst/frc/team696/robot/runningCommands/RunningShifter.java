package org.usfirst.frc.team696.robot.runningCommands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunningShifter extends Command {

    public RunningShifter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(Robot.shifterState){
    	case 0:
    		autoShifting();
    		break;
    	case 1:
    		Robot.shifter.shiftLow();
    		break;
    	case 2:
    	default:
    		Robot.shifter.shiftHigh();
    	}
    }
    
    public void autoShifting(){
    	Robot.shifterState = 2;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shift extends Command {

	boolean shiftHigh = true;
	
    public Shift(boolean shiftHigh) {
        requires(Robot.shifter);
        this.shiftHigh = shiftHigh;
    }
    
    

    protected void initialize() {}

    protected void execute() {
    	if(shiftHigh)Robot.shifter.shiftHigh();
    	else Robot.shifter.shiftLow();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

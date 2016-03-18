package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.Util;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutomaticShifting extends Command {

	Timer timer = new Timer();
	double time = timer.get();
	double oldTime = time;
	double leftDistance = 0;
	double oldLeftDistance = 0;
	double rightDistance = 0;
	double oldRightDistance = 0;
	double leftRPM = 0;
	double rightRPM = 0;
	double shiftHighRPM = 0;
	double shiftLowRPM = 0;
	double distancePerPulse = 0;
	double circumferenceOfWheel = 0;
	boolean stop = false;
	
    public AutomaticShifting() {
        requires(Robot.shifter);
    }

    protected void initialize() {
    	timer.start();
    	Robot.leftEncoder.setDistancePerPulse(distancePerPulse);
    	Robot.rightEncoder.setDistancePerPulse(distancePerPulse);
    	stop = false;
    }

    protected void execute() {
    	leftDistance = Robot.leftEncoder.getDistance();
    	rightDistance = Robot.rightEncoder.getDistance();
    	time = timer.get();
    	
    	oldLeftDistance = leftDistance;
    	oldRightDistance = rightDistance;
    	oldTime = time;
    	
    	if(leftRPM < shiftLowRPM || rightRPM < shiftLowRPM)Robot.shifter.shiftLow();
    	if(leftRPM > shiftHighRPM && rightRPM > shiftHighRPM)Robot.shifter.shiftHigh();
    }

    protected boolean isFinished() {
        return stop;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

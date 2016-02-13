package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.Util;

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
        requires(Robot.chassis);
    }

    protected void initialize() {
    	timer.start();
    	Robot.chassis.setDistancePerPulse(distancePerPulse);
    	stop = false;
    	Robot.chassis.setAutomatic();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	stop = Robot.chassis.isManual();
    	
    	leftDistance = Robot.chassis.getLeftEncoderDistance();
    	rightDistance = Robot.chassis.getRightEncoderDistance();
    	time = timer.get();
    	
    	leftRPM = Util.calculateRPM(leftDistance, oldLeftDistance, circumferenceOfWheel, time, oldTime);
    	rightRPM = Util.calculateRPM(rightDistance, oldRightDistance, circumferenceOfWheel, time, oldTime);
    	
    	oldLeftDistance = leftDistance;
    	oldRightDistance = rightDistance;
    	oldTime = time;
    	
    	if(leftRPM < shiftLowRPM || rightRPM < shiftLowRPM)Robot.chassis.shiftLow();
    	if(leftRPM > shiftHighRPM && rightRPM > shiftHighRPM)Robot.chassis.shiftHigh();
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return stop;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

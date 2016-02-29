package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate extends Command {

	double goalDirection;
	double currentDirection;
	double error;
	double k;
	double leftSpeed;
	double rightSpeed; 
	
    public Rotate(double goalDirection) {
        requires(Robot.chassis);
        this.goalDirection = goalDirection;
    }

    protected void initialize() {
    }

    protected void execute() {
    	this.currentDirection = Robot.navX.getYaw();
    	error = Util.deadZone((goalDirection - currentDirection), -5.0, 5.0, 0.0);
    	
    	leftSpeed = error*k;
    	rightSpeed = -error*k;
    }

    protected boolean isFinished() {
        if(error == 0) return true;
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

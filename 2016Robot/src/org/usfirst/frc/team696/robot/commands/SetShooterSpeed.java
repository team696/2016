package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterSpeed extends Command {

	double topRPM = 0;
	double botRPM= 0;
	double topSpeed = 0;
	double botSpeed = 0;
	boolean isRPM = true;
	
    public SetShooterSpeed(double topRPM, double bottomRPM) {
        this.topRPM = topRPM;
        this.botRPM = bottomRPM;
    }
    
    public SetShooterSpeed(double RPM){
    	topRPM = RPM; 
    	botRPM = RPM;
    }
    
    public SetShooterSpeed(boolean isRPM, double value){
		if(isRPM){	
    		topRPM = value; 
			botRPM = value;
		} else {
			topSpeed = value;
			botSpeed = value;
		}
    	this.isRPM = isRPM;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.topShooterRPM = topRPM;
    	Robot.botShooterRPM = botRPM;
    	Robot.topShooterSpeed = topSpeed;
    	Robot.botShooterSpeed = botSpeed;
    	Robot.isRPM = isRPM;
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

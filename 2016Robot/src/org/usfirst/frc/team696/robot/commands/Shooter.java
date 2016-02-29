package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shooter extends Command {

	double topRPM = 0;
	double bottomRPM= 0;
	
    public Shooter(double topRPM, double bottomRPM) {
        this.topRPM = topRPM;
        this.bottomRPM = bottomRPM;
    }
    
    public Shooter(double RPM){
    	topRPM = RPM; 
    	bottomRPM = RPM;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.topShooterRPM = topRPM;
    	Robot.botShooterRPM = bottomRPM;
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

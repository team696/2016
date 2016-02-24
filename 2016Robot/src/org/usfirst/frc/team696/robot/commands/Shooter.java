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
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
        this.topRPM = topRPM;
        this.bottomRPM = bottomRPM;
    }
    
    public Shooter(double RPM){
    	requires(Robot.shooter);
    	topRPM = RPM; 
    	bottomRPM = RPM;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.topShooterRPM = topRPM;
    	Robot.botShooterRPM = bottomRPM;
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

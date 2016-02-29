package org.usfirst.frc.team696.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team696.robot.Robot;

/**
 *
 */
public class Shoot extends Command {

	boolean finished = false;
	double time = 0;
	double startTime = 0;
	
    public Shoot() {
    	requires(Robot.shoot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Robot.shootTimer.get();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	time = Robot.shootTimer.get();
    	Robot.shoot.setShoot(true);
    	if(time - startTime > 1)finished = true;
    	else finished = false;
    	System.out.println(time + "   " + startTime);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shoot.setShoot(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

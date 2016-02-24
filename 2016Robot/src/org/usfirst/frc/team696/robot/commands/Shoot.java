package org.usfirst.frc.team696.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team696.robot.Robot;

/**
 *
 */
public class Shoot extends Command {

    public Shoot() {
    	requires(Robot.shoot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shoot.setShoot(true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shoot.get();
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

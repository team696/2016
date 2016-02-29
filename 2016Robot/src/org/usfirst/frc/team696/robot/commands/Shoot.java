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

    protected void initialize() {
    	startTime = Robot.shootTimer.get();
    }

    protected void execute() {
    	time = Robot.shootTimer.get();
    	Robot.shoot.setShoot(true);
    	if(time - startTime > 1)finished = true;
    	else finished = false;
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    	Robot.shoot.setShoot(false);
    }

    protected void interrupted() {
    }
}

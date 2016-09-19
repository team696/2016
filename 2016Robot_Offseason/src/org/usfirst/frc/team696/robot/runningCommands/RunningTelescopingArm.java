package org.usfirst.frc.team696.robot.runningCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.Util;

/**
 *
 */
public class RunningTelescopingArm extends Command {
	
	double maxDistance = 1000;
	double currentDistance = 0;
	double target = 0;
	double speed = 0;
	double oldSpeed = 0;
	double error = 0;
	double oldError = 0;
	Timer timer = new Timer();
	
    public RunningTelescopingArm() {
    	requires(Robot.telescopingArmSystem);
    	Robot.telescopingArmSystem.ratchet(false);
    }

    protected void initialize() {
    }

    protected void execute() {
    	target = Robot.telescopingTargetDistance;
    	currentDistance = Robot.telescopingEncoder.get();
    	error = target - currentDistance;
//    	error = Util.deadZone(error, -10, 10, 0);
    	
    	switch(Robot.state){
    	case 0:
    		speed = 0;
    		Robot.telescopingArmSystem.ratchet(true);
//    		Robot.pivotArm.ratchet(false);
    		break;
    	case 1:
    		if(Robot.startReleaseRatchetTimer){
    			timer.start();
    			Robot.startReleaseRatchetTimer = false;
    		}
    		speed = -0.1;
    		if(timer.get() > 0.2){
    			Robot.state = 2;
    			timer.stop();
    			timer.reset();
    		}
    		Robot.telescopingArmSystem.ratchet(false);
//    		Robot.pivotArm.ratchet(false);
    		break;
    	case 2:
    		speed = 1;
    		speed = Util.constrain(speed, 0, 0.85);
    		if(Math.abs(error) < 300)speed = Util.constrain(speed, 0, 0.6);
    		if(Math.abs(error) < 80)speed = Util.constrain(speed, 0, 0.4);
    		if(error <= 0 || maxDistance < currentDistance && speed > 0)speed = 0;
    		Robot.endOfMatch = true;
    		Robot.telescopingArmSystem.ratchet(false);
//    		Robot.pivotArm.ratchet(false);
    		break;
    	case 3:
    		speed = -1;
    		speed = Util.constrain(speed, -0.95, 0);
    		if(Math.abs(error) < 300)speed = Util.constrain(speed, -0.85, 0);
    		if(Math.abs(error) < 80)speed = Util.constrain(speed, -0.70, 0);
    		if(error >= 0)speed = 0;
    		Robot.endOfMatch = true;
    		Robot.telescopingArmSystem.ratchet(true);
//    		Robot.pivotArm.ratchet(true);
    	}
    	
    	System.out.print("   Telescoping Target: " + target + "     state: " + Robot.state + "       position: " + currentDistance + "     ");
    	Robot.telescopingArmSystem.set(speed);
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
   
    }
    
}
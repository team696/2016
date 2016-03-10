package org.usfirst.frc.team696.robot.runningCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import java.lang.management.ClassLoadingMXBean;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.PIDControl;
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
    	error = Util.deadZone(error, -30, 30, 0);
    	
    	switch(Robot.state){
    	case 0:
    		speed = 0;
    		Robot.telescopingArmSystem.ratchet(true);
    		Robot.pivotArm.ratchet(false);
    		System.out.println("in state 0");
    		break;
    	case 1:
    		System.out.println("In state 1");
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
    		Robot.pivotArm.ratchet(false);
    		break;
    	case 2:
    		speed = 1;
    		speed = Util.constrain(speed, 0, 0.7);
    		if(Math.abs(error) < 300)speed = Util.constrain(speed, 0, 0.5);
    		if(Math.abs(error) < 80)speed = Util.constrain(speed, 0, 0.3);
    		if(error <= 0 || maxDistance < currentDistance && speed > 0)speed = 0;
    		Robot.telescopingArmSystem.ratchet(false);
    		Robot.pivotArm.ratchet(false);
    		break;
    	case 3:
    		speed = -1;
    		speed = Util.constrain(speed, -0.7, 0);
    		if(Math.abs(error) < 300)speed = Util.constrain(speed, -0.5, 0);
    		if(Math.abs(error) < 80)speed = Util.constrain(speed, -0.5, 0);
    		if(error >= 0)speed = 0;
    		Robot.telescopingArmSystem.ratchet(true);
    		Robot.pivotArm.ratchet(true);
    	}
    	
    	System.out.println(Robot.state);
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
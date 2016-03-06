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
	double current = 0;
	double target = 0;
	double speed = 0;
	double oldSpeed = 0;
	double error = 0;
	double oldError = 0;
	Timer timer = new Timer();
	boolean hanging = false;
	
    public RunningTelescopingArm() {
    	requires(Robot.telescopingArmSystem);
    	Robot.telescopingArmSystem.ratchet(false);
    }

    protected void initialize() {
    }

    protected void execute() {
    	target = Robot.telescopingTargetDistance;
    	
    	current = Robot.telescopingEncoder.get();
    	
    	
    	
    	error = target - current;
    	if(!hanging)error = Util.deadZone(error, -30, 30, 0);
    	else error = Util.deadZone(0, -40, 40, 0);
    	
    	speed = 1 * Util.signOf(error);

    	speed = Util.constrain(speed, -0.7, 0.7);
    	
    	if(Math.abs(error) < 300)speed = Util.constrain(speed, -0.5, 0.5);
    	
    	if(!hanging)if(Math.abs(error) < 80)speed = Util.constrain(speed, -0.3, 0.3);
    	speed = Util.constrain(speed, -0.5, 0.3);
//    	speed = Util.constrain(speed, -0.5, 0.5);
//    	
//    	if(Math.abs(error) < 300)speed = Util.constrain(speed, -0.35, 0.35);
//    	
//    	if(Math.abs(error) < 80)speed = Util.constrain(speed, -0.2, 0.2);
    	
    	if(maxDistance < current && speed > 0 || current < 10 && speed < 0)speed = 0;
    	
    	if(error == 0)speed = 0;
    	
    	if(speed < 0)hanging = true;
    	
    	if(speed < 0){
    		Robot.telescopingArmSystem.ratchet(true);
    		Robot.pivotArm.ratchet(true);
    		System.out.println("Going in");
    	}else if(speed > 0 && !hanging){
    		Robot.telescopingArmSystem.ratchet(false);
    		Robot.pivotArm.ratchet(false);
    		System.out.println("goin out");
    	}else{
    		Robot.telescopingArmSystem.ratchet(true);
    		System.out.println("Nothing is happening");
    	}
    	
    	
    	
    	if(!Robot.telescopingArmSystem.getRatchet() && speed > 0)speed = 0;
    	
    	Robot.telescopingArmSystem.set(speed);
    	
    	oldSpeed = speed;
    	oldError = error;
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
   
    }
    
}
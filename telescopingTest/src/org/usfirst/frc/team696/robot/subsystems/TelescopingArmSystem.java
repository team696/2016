package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class TelescopingArmSystem extends Subsystem {
	
	protected double telescopeSpeed = 0.75;
	protected double pullUpSpeed = -0.50;
	
	public double brakingDist = 0;
	public double maxExtension = 0;
	public boolean pulling = false;
	public boolean finished = false;
	
    Victor telLeft = new Victor(RobotMap.telescopingMotorTop);
    Victor telRight = new Victor(RobotMap.telescopingMotorBot);
    Solenoid telescopingRatchet = new Solenoid(RobotMap.winchSolenoid);
    
    public void deploy() {
    	telescopingRatchet.set(true);
    	telLeft.set(telescopeSpeed);
    	telRight.set(telescopeSpeed);
    }
    
    public void stop() {
    	telLeft.set(0);
    	telRight.set(0);
    }
    
    public void pullUp() {
    	telescopingRatchet.set(false);
    	telLeft.set(pullUpSpeed);
    	telRight.set(pullUpSpeed);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


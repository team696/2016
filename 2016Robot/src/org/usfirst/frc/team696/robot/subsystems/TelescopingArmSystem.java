package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.PID;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class TelescopingArmSystem extends Subsystem {
	
	protected double kP = 0.0;
	protected double kI = 0.0;
	protected double kD = 0.0;
	protected double alpha = 0.0;
	
    Victor telLeft = new Victor(RobotMap.telescopingMotorLeft);
    Victor telRight = new Victor(RobotMap.telescopingMotorRight);
    Solenoid telescopingRatchet = new Solenoid(RobotMap.telescopingSolenoid);
    
    PID telescopingSpeed = new PID(kP, kI, kD, alpha);
    
    public void deploy() {
    	if (telescopingSpeed.getValue() > 0) telescopingRatchet.set(true);
    	else telescopingRatchet.set(false);
    	telLeft.set(telescopingSpeed.getValue());
    	telRight.set(telescopingSpeed.getValue());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


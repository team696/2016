package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class TelescopingArmSystem extends Subsystem {
	
    Victor telLeft = new Victor(RobotMap.telescopingMotorLeft);
    Victor telRight = new Victor(RobotMap.telescopingMotorRight);
    Solenoid telescopingRatchet = new Solenoid(RobotMap.telescopingSolenoid);
    
    PID telescopingSpeed = new PID(0,0,0,0);
    
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


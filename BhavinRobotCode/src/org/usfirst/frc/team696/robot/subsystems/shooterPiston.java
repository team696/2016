package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;

/**
 *
 */
public class shooterPiston extends Subsystem {
    
    Solenoid shooterPiston = new Solenoid(RobotMap.fireSolenoid);

    public void initDefaultCommand() {
    	
    }
    
    public void trigger() {
    	shooterPiston.set(true);
    }
    
    public boolean get() {
    	return shooterPiston.get();
    }

}


package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shoot extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Solenoid shooterPiston = new Solenoid(RobotMap.shootSolenoid);

    public void initDefaultCommand(){
    	
    }
   
    public void trigger() { 
    	shooterPiston.set(true);
    }
    
    public boolean get() {
    	return shooterPiston.get();
    }
	
}


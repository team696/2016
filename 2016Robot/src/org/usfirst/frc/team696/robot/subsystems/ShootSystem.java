package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShootSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Solenoid shooterPiston = new Solenoid(RobotMap.shootSolenoidChannel);

    public void initDefaultCommand(){
    	
    }
   
    public void setShoot(boolean shoot) { 
    	shooterPiston.set(shoot);
    }
    
    public boolean get() {
    	return shooterPiston.get();
    }
	
}


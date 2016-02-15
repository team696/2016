package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;

/**
 *
 */
public class shooterWheels extends Subsystem {
    
    Victor topMotor = new Victor(RobotMap.topMotor);
    Victor bottomMotor = new Victor(RobotMap.bottomMotor);
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setTopRPM(double topRPM) {
    	topMotor.set(topRPM);
    }
    
    public void setBottomRPM(double bottomRPM) {
    	bottomMotor.set(bottomRPM);
    }
}


package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class telescopingArmSystem extends Subsystem {
  
	static double climbSpeed = 0.0;
    Victor telLeft = new Victor(RobotMap.telescopingMotorLeft);
    Victor telRight = new Victor(RobotMap.telescopingMotorRight);
    Solenoid telescopingRatchet = new Solenoid(RobotMap.telescopingSolenoid);
    
    public void climb(boolean forward) {
    	if (forward) {
    		telLeft.set(climbSpeed);
    		telRight.set(climbSpeed);
    	}
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


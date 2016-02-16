package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PivotArm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Victor topMotor = new Victor(RobotMap.topPivotMotor);
	Victor bottomMotor = new Victor(RobotMap.bottomPivotMotor);
	
	double speed = 0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed){
    	this.speed = speed;
    	run();
    }
    
    private void run() {
    	topMotor.set(speed);
//    	bottomMotor.set(speed);
    }
}


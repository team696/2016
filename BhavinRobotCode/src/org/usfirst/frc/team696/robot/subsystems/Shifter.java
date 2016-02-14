package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shifter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Solenoid shifter = new Solenoid(RobotMap.shifterSolenoid);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void shiftHigh() {
		shifter.set(true);
	}
	
	public void shiftLow() {
		shifter.set(false);
	}
}


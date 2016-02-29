package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShifterSystem extends Subsystem {
    

	Solenoid shifter = new Solenoid(RobotMap.shifterSolenoidChannel);
	
    public void initDefaultCommand() {
    }
    
    public void shiftHigh() {
		shifter.set(true);
	}
	
	public void shiftLow() {
		shifter.set(false);
	}
}


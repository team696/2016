package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
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
		shifter.set(false);
		Robot.shiftedHigh = true;
	}
	
	public void shiftLow() {
		shifter.set(true);
		Robot.shiftedHigh = false;
	}
}


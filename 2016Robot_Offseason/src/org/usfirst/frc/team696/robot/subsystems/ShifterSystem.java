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
    
    double circumferenceOfWheel = 2.028945255443408133173790518368;
    double lowGearRatio = 1/26.67;
    double highGearRatio = 1/11.73;
    double pulsesPerRevolution = 64;
    
    public void shiftHigh() {
		shifter.set(false);
		Robot.shiftedHigh = true;
		Robot.leftEncoder.setDistancePerPulse(highGearRatio * circumferenceOfWheel / pulsesPerRevolution);
		Robot.rightEncoder.setDistancePerPulse(highGearRatio * circumferenceOfWheel / pulsesPerRevolution);
	}
	
	public void shiftLow() {
		shifter.set(true);
		Robot.shiftedHigh = false;
		Robot.leftEncoder.setDistancePerPulse(lowGearRatio * circumferenceOfWheel / pulsesPerRevolution);
		Robot.rightEncoder.setDistancePerPulse(lowGearRatio * circumferenceOfWheel / pulsesPerRevolution);
	}
}


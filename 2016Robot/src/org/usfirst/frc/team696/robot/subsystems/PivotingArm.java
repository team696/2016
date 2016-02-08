package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PivotingArm extends PIDSubsystem {
	
	Encoder encoder = new Encoder(RobotMap.pivotArmEncoderA, RobotMap.pivotArmEncoderB);
	DigitalInput zeroingLimitSwitch = new DigitalInput(RobotMap.pivotArmLimitSwitchChannel);
	
    // Initialize your subsystem here
	public PivotingArm(double p, double i, double d) {
		super(p, i, d);
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}

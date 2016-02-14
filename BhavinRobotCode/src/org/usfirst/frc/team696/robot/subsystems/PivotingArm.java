package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Victor;
/**
 *
 */
public class PivotingArm extends PIDSubsystem {
	
	double speed = 0.0;
	private static final double vp = 0.0;
	private static final double vi = 0.0;
	private static final double vk = 0.0;
	Encoder encoder = new Encoder(RobotMap.pivotArmEncoderA, RobotMap.pivotArmEncoderB);
	Victor pivotMotor = new Victor(RobotMap.pivotMotor);
	
    // Initialize your subsystem here
	public PivotingArm() {
		super("PivotingArm", vp, vi, vk);
	}

	protected void initDefaultCommand() {
		
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        pivotMotor.set(output);
    }
    
	public void setSpeed(double setVal) {
		pivotMotor.set(setVal);
	}
	
	public double getSpeed() {
		return speed;
	}
									//find distancePerPulse
	public void setDistancePerPulse(double distancePerPulse) {
		encoder.setDistancePerPulse(distancePerPulse);
		//distance per pulse
	}
	
	public double getEncoderDistance() {
		return encoder.getDistance();
		// total distance
	}

}

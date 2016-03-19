package org.usfirst.frc.team696.utilities;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;

public class DoubleMotor implements PIDOutput {
	Victor motorA;
	Victor motorB;
	
	public DoubleMotor(int motorAChannel, int motorBChannel){
		motorA = new Victor(motorAChannel);
		motorB = new Victor(motorBChannel);
	}
	
	public void set(double speed){
		motorA.set(speed);
		motorB.set(speed);
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		set(output);
	}
	
}

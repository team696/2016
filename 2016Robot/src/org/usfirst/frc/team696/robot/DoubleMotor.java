package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;

public class DoubleMotor implements PIDOutput {
	Victor motorA;
	Victor motorB;
	
	public DoubleMotor(int motorAChannel, int motorBChannel){
		motorA = new Victor(motorAChannel);
		System.out.println("assigne motor a");
		motorB = new Victor(motorBChannel);
		System.out.println("assigned motor b");
	}
	
	public void set(double speed){
		System.out.println("Setting Speed");
		motorA.set(speed);
		motorB.set(speed);
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		System.out.println("Setting output");
		set(output);
	}
	
}

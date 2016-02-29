package org.usfirst.frc.team696.utilities;

import org.usfirst.frc.team696.robot.Robot;

public class StallPrevention {
	double maxAmps = 10;
	double currentAmps = 0;
	double currentOutput = 0;
	double output = 0;
	
	public StallPrevention(double maxAmps){
		this.maxAmps = maxAmps;
	}
	
	public void setCurrentAmps(double currentAmps, double currentOutput){
		this.currentAmps = currentAmps;
		this.currentOutput = currentOutput;
		run();
	}
	
	public void run(){
		if(currentAmps > maxAmps){
			output = 0;
		} else {
			output = currentOutput;
		}
		if(Robot.PDP.getTotalCurrent() > 120)output = 0;
	}
	
	public double getOutput(){
		return output;
	}
	
}

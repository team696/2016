package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class StallPreventor {
	private	double	
			maxAmp 		= 0,
			currentAmp	= 0;
	
	private boolean overAmped = false;
	PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	public StallPreventor(double maxAmp){
		this.maxAmp = maxAmp;
	}
	
	public void setVals(double currentAmp){
		this.currentAmp = currentAmp;
	}
	
	public void run(){
		overAmped = false;
		if(currentAmp > maxAmp)overAmped = true;
	}
	
	public boolean isOverAmped(){
		return overAmped;
	}
}

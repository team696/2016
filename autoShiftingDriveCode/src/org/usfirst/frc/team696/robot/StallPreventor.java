package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class StallPreventor {
	/*
	 * Initiated Private Doubles
	 */
	private	double	
			maxAmp 		= 0,
			currentAmp	= 0;
	
	/*
	 * Initiated Private Boolean
	 */
	private boolean 
			overAmped = false;
	
	/*
	 * Initiated Power Distribution Panel
	 */
	PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	/*
	 * Set the contructor
	 */
	public StallPreventor(double maxAmp){
		this.maxAmp = maxAmp;
	}
	
	/*
	 * Set the current amp draw
	 */
	public void setVals(double currentAmp){
		this.currentAmp = currentAmp;
	}
	
	/*
	 * Run command
	 * Must be called periodically
	 */
	public void run(){
		overAmped = false;
		if(currentAmp > maxAmp)overAmped = true;
	}
	
	/*
	 * Returns whether or not the channel is stalling
	 */
	public boolean isOverAmped(){
		return overAmped;
	}
}

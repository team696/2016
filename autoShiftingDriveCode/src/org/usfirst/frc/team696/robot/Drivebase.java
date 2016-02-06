package org.usfirst.frc.team696.robot;

public class Drivebase {
	/*
	 * Initiated Private Doubles
	 */
	private	double	
			leftVal 	= 0,
			rightVal 	= 0,
			lHighAmp	= 0,
			hLowAmp		= 0,
			leftAmp		= 0,
			rightAmp 	= 0;
	
	/*
	 * Initiated Private Boolean
	 */
	private boolean
			inHigh		= true,
			forceShift	= false,
			shiftHigh	= false,
			shiftLow	= false;
	
	/*
	 * Constructor for Class
	 */
	public Drivebase(double lHighAmp, double hLowAmp, boolean isHigh){
		this.hLowAmp = hLowAmp;
		this.lHighAmp = lHighAmp;
		this.inHigh = isHigh;
	}
	
	/*
	 * Update the amps
	 */
	public void updateAmp(double leftAmp, double rightAmp){
		this.leftAmp = leftAmp;
		this.rightAmp = rightAmp;
	}
	
	/*
	 * Set the desired speeds and enabling/disabling of Fast Turn
	 */
	public void setSpeeds(double Yaxis, double Xaxis, boolean fastTurn){
		if(fastTurn)Xaxis=Xaxis*2;
			leftVal = Yaxis + Xaxis;
			rightVal = Yaxis - Xaxis;
	}
	
	/*
	 * Run command
	 * Must be called periodically
	 */
	public void run(){
		forceShift = false;
		if(shiftLow || shiftHigh)forceShift = true;
		if(setHigh(leftAmp) || setHigh(rightAmp))inHigh = true;
		else inHigh = false;
	}
	
	/*
	 * Decides whether the drivetrain should shift to high or low
	 */
	private boolean setHigh(double currentAmp){
		if(!forceShift){
			if(!inHigh){
				if(currentAmp >= lHighAmp)return true;
				return false;
			} else {
				if(currentAmp <= hLowAmp)return false;
				return true;
			}
		} else {
			if(shiftLow && !shiftHigh) return false;
			else return true;
		}
	}
	
	/*
	 * Returns the left speed value
	 */
	public double getLeft(){
		return leftVal;
	}
	
	/*
	 * Return the right speed value
	 */
	public double getRight(){
		return rightVal;
	}
	
	/*
	 * returns the value of high or low
	 */
	public boolean getHigh(){
		return inHigh;
	}
}

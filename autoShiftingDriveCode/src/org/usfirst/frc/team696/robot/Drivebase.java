package org.usfirst.frc.team696.robot;

public class Drivebase {
	
	private	double	
			leftVal 	= 0,
			rightVal 	= 0,
			lHighAmp	= 0,
			hLowAmp		= 0,
			leftAmp		= 0,
			rightAmp 	= 0;
	
	private boolean
			inHigh		= true,
			forceShift	= false,
			shiftHigh	= false,
			shiftLow	= false;
	
	public Drivebase(double lHighAmp, double hLowAmp, boolean isHigh){
		this.hLowAmp = hLowAmp;
		this.lHighAmp = lHighAmp;
		this.inHigh = isHigh;
	}
	
	public void updateCurrentAmp(double leftAmp, double rightAmp){
		this.leftAmp = leftAmp;
		this.rightAmp = rightAmp;
	}
	
	public void setSpeeds(double Yaxis, double Xaxis, boolean fastTurn){
		if(fastTurn)Xaxis=Xaxis*2;
			leftVal = Yaxis + Xaxis;
			rightVal = Yaxis - Xaxis;
	}
	
	public void run(){
		forceShift = false;
		if(shiftLow || shiftHigh)forceShift = true;
		if(setHigh(leftAmp) || setHigh(rightAmp))inHigh = true;
		else inHigh = false;
	}
	
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
	
	public double getLeft(){
		return leftVal;
	}
	
	public double getRight(){
		return rightVal;
	}
	
	public boolean getHigh(){
		return inHigh;
	}
}

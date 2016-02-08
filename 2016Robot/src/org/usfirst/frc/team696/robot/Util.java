package org.usfirst.frc.team696.robot;

public class Util {
	public double constrain(double input, double minimum, double maximum){
		if(input > maximum)	return maximum;
		if(input < minimum) return minimum;
		return input;
	}
	
	public static double deadZone(double input, double minimum, double maximum, double returnVal) {
		if(input > minimum && input < maximum) return returnVal;
		return input;
	}
}

package org.usfirst.frc.team696.robot;

public class Util {
	public static double constrain(double input, double minimum, double maximum){
		if(input > maximum)	return maximum;
		if(input < minimum) return minimum;
		return input;
	}
	
	public static double deadZone(double input, double minimum, double maximum, double returnVal) {
		if(input > minimum && input < maximum) return returnVal;
		return input;
	}
	
	public static double calculateRPM(double distance, double oldDistance, double circumferenceOfWheel, double time, double oldTime) {
		return ((distance - oldDistance)/circumferenceOfWheel)/(time - oldTime)/60;
	}
}

package org.usfirst.frc.team696.robot.utilities;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class GetCamVals {
	
	public GetCamVals() {
	}
	
	NetworkTable gripVals = NetworkTable.getTable("GRIP/myCountoursReport");
	
	public double getArea() {
		double[] defaultValue = new double[0];
		double[] areas = gripVals.getNumberArray("area", defaultValue);
		return areas[(areas.length-1)];
	}
	
	public double getCenterX() {
		double[] defaultValue = new double[0];
		double[] centerXVals = gripVals.getNumberArray("area", defaultValue);
		return centerXVals[(centerXVals.length-1)];
	}
	
	public double getCenterY() {
		double[] defaultValue = new double[0];
		double[] centerYVals = gripVals.getNumberArray("centerX", defaultValue);
		return centerYVals[(centerYVals.length-1)];
	}
	
	public double getHeight() {
		double[] defaultValue = new double[0];
		double[] heightVals = gripVals.getNumberArray("area", defaultValue);
		return heightVals[(heightVals.length-1)];
	}
	
	public double getWidthVals() {
		double[] defaultValue = new double[0];
		double[] widthVals = gripVals.getNumberArray("area", defaultValue);
		return widthVals[(widthVals.length-1)];
	}

}

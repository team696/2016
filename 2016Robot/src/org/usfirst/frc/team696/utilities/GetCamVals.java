package org.usfirst.frc.team696.utilities;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class GetCamVals {
	
	public GetCamVals() {
	}
	
	NetworkTable gripVals = NetworkTable.getTable("GRIP/myCountoursReport");
	double[] defaultValue = new double[0];
	
	public double[] getArea() {
		double[] areas = gripVals.getNumberArray("area", defaultValue);
		return areas;
	}
	
	public double[] getCenterX() {
		double[] centerXVals = gripVals.getNumberArray("centerX", defaultValue);
		return centerXVals;
	}
	
	public double[] getCenterY() {
		double[] centerYVals = gripVals.getNumberArray("centerY", defaultValue);
		return centerYVals;
	}
	
	public double[] getHeight() {
		double[] heightVals = gripVals.getNumberArray("height", defaultValue);
		return heightVals;
	}
	
	public double[] getWidthVals() {
		double[] widthVals = gripVals.getNumberArray("width", defaultValue);
		return widthVals;
	}

}

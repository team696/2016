package org.usfirst.frc.team696.utilities;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class GetCamVals {
	
	double[] defaultValue = new double[0];
	
	public GetCamVals() {
	}
	
	NetworkTable values = NetworkTable.getTable("GRIP/myLinesReport");
	
	public double[] getX1() {
		return values.getNumberArray("x1", defaultValue);
	}
	
	public double[] getY1() {
		return values.getNumberArray("x2", defaultValue);
		
	}
	
	public double[] getX2() {
		return values.getNumberArray("y2", defaultValue);
	}
	
	public double[] getY2() {
		return values.getNumberArray("x2", defaultValue);
	}
	
	public double[] getLength() {
		return values.getNumberArray("length", defaultValue);
	}
	
	public double[] getAngle() {
		return values.getNumberArray("angle", defaultValue);
	}
	
//	public double getArea() {
//		double[] defaultValue = new double[0];
//		double[] areas = gripVals.getNumberArray("area", defaultValue);
//		return areas[(areas.length-1)];
//	}
//	
//	public double getCenterX() {
//		double[] defaultValue = new double[0];
//		double[] centerXVals = gripVals.getNumberArray("area", defaultValue);
//		return centerXVals[(centerXVals.length-1)];
//	}
//	
//	public double getCenterY() {
//		double[] defaultValue = new double[0];
//		double[] centerYVals = gripVals.getNumberArray("centerX", defaultValue);
//		return centerYVals[(centerYVals.length-1)];
//	}
//	
//	public double getHeight() {
//		double[] defaultValue = new double[0];
//		double[] heightVals = gripVals.getNumberArray("area", defaultValue);
//		return heightVals[(heightVals.length-1)];
//	}
//	
//	public double getWidthVals() {
//		double[] defaultValue = new double[0];
//		double[] widthVals = gripVals.getNumberArray("area", defaultValue);
//		return widthVals[(widthVals.length-1)];
//	}

}

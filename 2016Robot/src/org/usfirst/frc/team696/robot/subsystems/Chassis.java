package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Chassis extends Subsystem {
	
    RobotDrive drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.rearRightMotor);
	Encoder leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB);
	Encoder rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB);
	
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	protected void initDefaultCommand() {}
	
	public void resetGyro() {
	}
	
	public double getAngle() {
		return 0;
	}
	
	public void setLeftSpeed(double leftSpeed) {
		this.leftSpeed = leftSpeed;
	}
	
	public void setRightSpeed(double rightSpeed) {
		this.rightSpeed = rightSpeed;
	}
	
	public double getLeftSpeed() {
		return leftSpeed;
	}
	
	public double getRightSpeed() {
		return rightSpeed;
	}
	
	public void setDistancePerPulse(double distancePerPulse) {
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
	}
	
	public double getLeftEncoderDistance() {
		return leftEncoder.getDistance();
	}
	
	public double getRightEncoderDistance() {
		return rightEncoder.getDistance();
	}
	
	public double getAverageDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance())/2;
	}
	
    public void drive() {
    	drive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void stop() {
    	drive.tankDrive(0, 0);
    }

}


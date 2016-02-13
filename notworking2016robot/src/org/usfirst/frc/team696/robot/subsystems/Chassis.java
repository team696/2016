package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Chassis extends Subsystem {
	
    RobotDrive drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.rearRightMotor);
	Encoder leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB);
	Encoder rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB);
	
	Solenoid shifter = new Solenoid(RobotMap.shifterSolenoid);
	
	IMU navX;
	SerialPort port;
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	boolean isManual = false;
	
	public Chassis() {
		try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
	}
	
	protected void initDefaultCommand() {}
	
	public void setManual(){
		isManual = true;
	}
	
	public void setAutomatic() {
		isManual = false;
	}
	
	public boolean isManual() {
		return isManual;
	}
	
	public void shiftHigh() {
		shifter.set(true);
	}
	
	public void shiftLow() {
		shifter.set(false);
	}
	
	public void resetNavX() {
		navX.zeroYaw();
	}
	
	public double getAngle() {
		return navX.getYaw();
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


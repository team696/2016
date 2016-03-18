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
public class ChassisSystem extends Subsystem {
    
	RobotDrive drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.rearRightMotor);
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	public ChassisSystem(){
	}
	
    public void initDefaultCommand() {
    }
    
    public void setSpeeds(double leftSpeed, double rightSpeed){
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    	drive();
    }
    
    public void drive() {
    	drive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void stop() {
    	drive.tankDrive(0, 0);
    }
}


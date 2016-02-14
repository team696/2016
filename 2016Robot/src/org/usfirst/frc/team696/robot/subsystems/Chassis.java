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
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	RobotDrive drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.rearRightMotor);
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	boolean isManual = false;
	boolean fastTurn = false;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setFastTurn(boolean fastTurn) {
		this.fastTurn = fastTurn;
	}
    
    public boolean getFastTurn() {
		// TODO Auto-generated method stub
    	return fastTurn;
	}
    
    public void setSpeeds(double leftSpeed, double rightSpeed){
    	this.leftSpeed = leftSpeed;
    	this.rightSpeed = rightSpeed;
    	drive();
    }
    
    public void setManual(){
		isManual = true;
	}
	
	public void setAutomatic() {
		isManual = false;
	}
	
	public boolean isManual() {
		return isManual;
	}
    
	public double getLeftSpeed() {
		return leftSpeed;
	}
	
	public double getRightSpeed() {
		return rightSpeed;
	}
	
    public void drive() {
    	drive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void stop() {
    	drive.tankDrive(0, 0);
    }
}


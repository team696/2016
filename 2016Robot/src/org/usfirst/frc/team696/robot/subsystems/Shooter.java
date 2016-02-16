package org.usfirst.frc.team696.robot.subsystems;

import java.awt.Robot;

import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.robot.TakeBackHalf;
import org.usfirst.frc.team696.robot.Util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	TakeBackHalf topTBH = new TakeBackHalf(5000);
	TakeBackHalf bottomTBH = new TakeBackHalf(5000);
	
	Victor topShooterWheel = new Victor(RobotMap.topShooterWheel);
	Victor bottomShooterWheel = new Victor(RobotMap.bottomShooterWheel);
	Encoder topShooterWheelEnchoder = new Encoder(RobotMap.topShooterWheelEnchoderA, RobotMap.topShooterWheelEnchoderB);
	Encoder bottomShooterWheelEncoder = new Encoder(RobotMap.bottomShooterWheelEncoderA, RobotMap.bottomShooterWheelEncoderB);
	
	double speedTop = 0;
	double speedBottom = 0;
	double currentTopRPM = 0;
	double currentBottomRPM = 0;
	double topTargetRPM = 0;
	double bottomTargetRPM = 0;
	double time = 0;
	double oldTime = 0;
	double topDistance = 0;
	double oldTopDistance = 0;
	double bottomDistance = 0;
	double oldBottomDistance = 0;
	
	Timer timer = new Timer();
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void setTargetRPM(double topTargetRPM, double bottomTargetRPM) {
    	this.topTargetRPM = topTargetRPM;
    	this.bottomTargetRPM = bottomTargetRPM;
    	topShooterWheelEnchoder.setDistancePerPulse(1);
    	bottomShooterWheelEncoder.setDistancePerPulse(1);
	}
    
    public void run(){
    	time = timer.get();
    	
    	topDistance = topShooterWheelEnchoder.getDistance();
    	currentTopRPM = Util.findVelocity(time, oldTime, topDistance, oldTopDistance);
    	oldTopDistance = topDistance;
    	
    	bottomDistance = bottomShooterWheelEncoder.getDistance();
    	currentBottomRPM = Util.findVelocity(time, oldTime, bottomDistance, oldBottomDistance);
    	oldBottomDistance = bottomDistance;
    	
    	oldTime = time;
    }
}


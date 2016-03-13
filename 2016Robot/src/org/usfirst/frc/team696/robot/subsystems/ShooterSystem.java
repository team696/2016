package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.utilities.StallPrevention;
import org.usfirst.frc.team696.utilities.TakeBackHalfControl;
import org.usfirst.frc.team696.utilities.Util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSystem extends Subsystem {
    
	TakeBackHalfControl topTBH = new TakeBackHalfControl(4986);
	TakeBackHalfControl botTBH = new TakeBackHalfControl(4986);
	
	Victor topShooterWheel = new Victor(RobotMap.topShooterMotor);
	Victor bottomShooterWheel = new Victor(RobotMap.bottomShooterMotor);
	
	StallPrevention topSP = new StallPrevention(50);
	StallPrevention botSP = new StallPrevention(50);
	
	double speedTop = 0;
	double speedBottom = 0;
	double topCurrentRPM = 0;
	double botCurrentRPM = 0;
	double topTargetRPM = 0;
	double botTargetRPM = 0;
	double time = 0;
	double oldTime = 0;
	double topDistance = 0;
	double oldTopDistance = 0;
	double bottomDistance = 0;
	double oldBottomDistance = 0;
	double topMotorPower = 0;
	double botMotorPower = 0;
	double direction = 0;
	
	Timer timer = new Timer();
	
	public ShooterSystem(){
		topShooterWheel.setInverted(true);
		timer.start();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void run(){
    	topTargetRPM = Robot.topShooterRPM;
    	botTargetRPM = Robot.botShooterRPM;

    	direction = Util.signOf(topTargetRPM);
    	
    	time = timer.get();
    	
    	if(direction > 0){
	    	topDistance = Robot.topShooterWheelEncoder.get();
	    	topCurrentRPM = Util.findVelocity(time, oldTime, Math.abs(topDistance), Math.abs(oldTopDistance));
	    	topTBH.setTargetRPM(Math.abs(topTargetRPM));
	    	topTBH.setCurrentRPM(Math.abs(topCurrentRPM));
	    	topTBH.run();
	    	oldTopDistance = topDistance;
	    	
	    	bottomDistance = Robot.bottomShooterWheelEncoder.get();
	    	botCurrentRPM = Util.findVelocity(time, oldTime, Math.abs(bottomDistance), Math.abs(oldBottomDistance));
	    	botTBH.setTargetRPM(Math.abs(botTargetRPM));
	    	botTBH.setCurrentRPM(Math.abs(botCurrentRPM));
	    	botTBH.run();
	    	oldBottomDistance = bottomDistance;
	    	
	    	topMotorPower = topTBH.getMotorPower();
	    	botMotorPower = botTBH.getMotorPower();
    	} else {
    		topMotorPower = topTargetRPM;
    		botMotorPower = botTargetRPM;
    	}

    	oldTime = time;
    	
    	if(!Robot.isRPM){
    		topMotorPower = Robot.topShooterSpeed;
    		botMotorPower = Robot.botShooterSpeed;
    		Robot.isAtSpeed = true;
    	} else {
    		if(Math.abs(topTargetRPM - topCurrentRPM) < 400 /*&& Math.abs(botTargetRPM - botCurrentRPM) < 150*/)Robot.isAtSpeed = true;
        	else Robot.isAtSpeed = false;
    		
//    		Robot.isAtSpeed = true;
    	}
    	
    	if(Math.abs(topMotorPower) < 0.05)topMotorPower = 0;

    	if(Robot.isRPM){
    		topSP.setCurrentAmps(Robot.PDP.getCurrent(12), topMotorPower*direction);
    		botSP.setCurrentAmps(Robot.PDP.getCurrent(3), topMotorPower*direction);
    	} else {
    		topSP.setCurrentAmps(Robot.PDP.getCurrent(12), topMotorPower*direction);
    		botSP.setCurrentAmps(Robot.PDP.getCurrent(3), botMotorPower*direction);
    	}
    	
//    	System.out.println("SP: " + topSP.getOutput());
    	
    	topShooterWheel.set(topSP.getOutput());
    	bottomShooterWheel.set(botSP.getOutput());
    }
}


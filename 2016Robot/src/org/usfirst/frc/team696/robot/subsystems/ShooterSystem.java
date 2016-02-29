package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.robot.StallPrevention;
import org.usfirst.frc.team696.robot.TakeBackHalf;
import org.usfirst.frc.team696.robot.Util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	TakeBackHalf topTBH = new TakeBackHalf(4500);
	TakeBackHalf bottomTBH = new TakeBackHalf(4500);
	
	Victor topShooterWheel = new Victor(RobotMap.topShooterMotor);
	Victor bottomShooterWheel = new Victor(RobotMap.bottomShooterMotor);
	
	StallPrevention topSP = new StallPrevention(40);
	StallPrevention botSP = new StallPrevention(40);
	
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
	double topMotorPower = 0;
	double bottomMotorPower = 0;
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
//    	System.out.print("RUN     ");
    	topTargetRPM = Robot.topShooterRPM;
    	bottomTargetRPM = Robot.botShooterRPM;

    	direction = Util.signOf(topTargetRPM);
    	
    	time = timer.get();
    	
    	topDistance = Robot.topShooterWheelEncoder.get();
    	currentTopRPM = Util.findVelocity(time, oldTime, Math.abs(topDistance), Math.abs(oldTopDistance));
//    	if(Util.signOf(topTargetRPM) != Util.signOf(currentTopRPM) && currentTopRPM != 0){
//    		topTargetRPM = 0;
//    		System.out.println("topTargetRPM = 0");
//    	}
//    	if(topTargetRPM == 0 && currentTopRPM != 0)topTargetRPM = (0.0001) * Util.signOf(currentTopRPM);
    	topTBH.setTargetRPM(Math.abs(topTargetRPM));
    	topTBH.setCurrentRPM(Math.abs(currentTopRPM));
    	topTBH.run();
    	oldTopDistance = topDistance;
    	
    	bottomDistance = Robot.bottomShooterWheelEncoder.get();
    	currentBottomRPM = Util.findVelocity(time, oldTime, Math.abs(bottomDistance), Math.abs(oldBottomDistance));
//    	if(Util.signOf(bottomTargetRPM) != Util.signOf(currentBottomRPM) && currentBottomRPM != 0){
//    		bottomTargetRPM = 0;
//    		System.out.println("bottomtargetRPM = 0");
//    	}
    	bottomTBH.setTargetRPM(Math.abs(bottomTargetRPM));
    	bottomTBH.setCurrentRPM(Math.abs(currentBottomRPM));
    	bottomTBH.run();
    	oldBottomDistance = bottomDistance;

    	oldTime = time;
    	
    	topMotorPower = topTBH.getMotorPower();
    	if(Math.abs(topMotorPower) < 0.05)topMotorPower = 0;
    	
//    	bottomMotorPower = bottomTBH.getMotorPower();
//    	if(Math.abs(bottomMotorPower) < 0.05)bottomMotorPower = 0;
    	
    	topSP.setCurrentAmps(Robot.PDP.getCurrent(12), topMotorPower*direction);
    	botSP.setCurrentAmps(Robot.PDP.getCurrent(3), topMotorPower*direction);
    	
    	topShooterWheel.set(topSP.getOutput());
    	bottomShooterWheel.set(botSP.getOutput());
    	
//    	System.out.print("\ttopCurrent: " + currentTopRPM + "\ttopTarget: " + topTargetRPM + "\ttopEncoder: " + Robot.topShooterWheelEncoder.get() + "\ttopMP: " + topMotorPower);
//    	System.out.print("\tbottomCurrent: " + currentBottomRPM + "\tbottomTarget: " + bottomTargetRPM + "\tbottomEncoder: " + Robot.bottomShooterWheelEncoder.get() + "\tbottomMP: " + bottomMotorPower);
    	
//    	System.out.print("\t" + Robot.topShooterWheelEncoder.get() + "\t" + Robot.bottomShooterWheelEncoder.get());
    	
//    	System.out.println();
    }
}


package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.robot.TakeBackHalf;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 */
public class rampShooterAuto extends Command {
	
    Encoder topMotorEncoder = new Encoder(RobotMap.topMotorEncoderA, RobotMap.topMotorEncoderB);
    Encoder bottomMotorEncoder = new Encoder(RobotMap.bottomMotorEncoderA, RobotMap.bottomMotorEncoderB);

	public static double maxTopRPM = 4320;
	public static double maxBottomRPM = 3927;
	
	TakeBackHalf topShooter = new TakeBackHalf(maxTopRPM);
	TakeBackHalf bottomShooter = new TakeBackHalf(maxBottomRPM);

	Timer time = new Timer();
    
    double  goalPos = 0,
            currentRPM1 = 0,
            ticPerInch1 = 0.03528,
            oldTime1 = time.get(),
            oldEncoderVal1 = topMotorEncoder.getDistance(),
            diameter1 = 2+7/8,
            speed1 = 0,
            
            currentRPM2 = 0,
            ticPerInch2 = 0.07526,
            oldTime2 = time.get(),
            oldEncoderVal2 = bottomMotorEncoder.getDistance(),
            diameter2 = 2+7/8,
            speed2 = 0;
       
    protected void initialize(){
    
    }

    protected void execute() {
        
        currentRPM1 = getVelocity(oldTime1, ticPerInch1, topMotorEncoder.getDistance(), oldEncoderVal1)/getCirc(diameter1);
        oldEncoderVal1 = topMotorEncoder.getDistance();
        topShooter.setCurrentRPM(currentRPM1);
        topShooter.setTargetRPM(goalPos);
        
        
        currentRPM2 = getVelocity(oldTime2, ticPerInch2, bottomMotorEncoder.getDistance(), oldEncoderVal2)/getCirc(diameter2);
        oldEncoderVal2 = bottomMotorEncoder.getDistance();
        bottomShooter.setCurrentRPM(currentRPM2);
        bottomShooter.setTargetRPM(goalPos);
        
        speed1 = topShooter.getMotorPower();
        speed2 = bottomShooter.getMotorPower();
        
        System.out.println("speed1:" + speed1 + "   speed2:" + speed2 + "   goalPos:" + goalPos + " curRPM1,2:" + currentRPM1 + "   " + currentRPM2);
        
        topShooter.setTopMotor(speed1);
        bottomShooter.setBottomMotor(speed2);
        
        isFinished();
    }
    
    /*
     * returns in inches per minute
     */
    private double getVelocity(double oldTime, double ticPerInch, double curVal , double oldVal){
        double t = time.get() - oldTime;
        double dX = ticPerInch * (curVal - oldVal);
        double v;
        t = t * (60/t);
        v = dX/t;
        return v;
    }
    
    private double getCirc(double diameter){
        return diameter*Math.PI;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (speed1 == topShooter.getTopMotor() && speed2 == bottomShooter.getBottomMotor()) {
        	return true;
        } else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

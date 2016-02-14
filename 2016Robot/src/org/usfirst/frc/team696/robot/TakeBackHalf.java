/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Daniel
 */
public class TakeBackHalf {
	    Victor topMotor = new Victor(RobotMap.topMotor);
	    Victor bottomMotor = new Victor(RobotMap.bottomMotor);
	    Encoder topEncoder = new Encoder(RobotMap.topMotorEncoderA, RobotMap.topMotorEncoderB);
	    Encoder bottomEncoder = new Encoder(RobotMap.bottomMotorEncoderA, RobotMap.bottomMotorEncoderB);
	    Solenoid fire = new Solenoid(RobotMap.fireSolenoid);
	    Timer time = new Timer();
	
//    private double                  error;
    private double
            error = 0,
            oldError = error,
            currentRPM = 0,
            targetRPM = currentRPM,
            motorPower = 0,
            tbh = 0,
            maxRPM,
            gain = 1E-5;
    
    public TakeBackHalf(double maxRPM){
        this.maxRPM = maxRPM;
    }
    
    private double constrain(double val, double max, double min){
        if(val > max)return max;
        if(val < min)return min;
        return val;
    }
    
    private boolean isPositive(double val){
        return val > 0;
    }
    
    public void setTargetRPM(double newRPM){
        if(targetRPM < newRPM) oldError = 1;
        if(targetRPM > newRPM) oldError = -1;
        tbh = (2* (targetRPM/maxRPM)) - 1;
        targetRPM = newRPM;
    }
    
    public void setTopMotor(double speed) {
    	topMotor.set(speed);
    }
    
    public void setBottomMotor(double speed) {
    	bottomMotor.set(speed);
    }
    
    public double getTopMotor() {
    	return topMotor.get();
    }
    
    public double getBottomMotor() {
    	return bottomMotor.get();
    }
    public void setCurrentRPM(double currentRPM){
        this.currentRPM = currentRPM;
    }
        
    public void run(){
        error = targetRPM - currentRPM;
        motorPower+=error;
        motorPower = constrain(motorPower, 1, -1);
        if(isPositive(oldError) != isPositive(error)){
            motorPower = 0.5* (motorPower + tbh);
            
            oldError =  error;
        }
    }
    
    public double getMotorPower() { 
        return motorPower;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team696.utilities;

/**
 *
 * @author Daniel
 */
public class TakeBackHalfControl {
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
    
    public TakeBackHalfControl(double maxRPM){
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
        if(targetRPM != newRPM)tbh = (2* (targetRPM/maxRPM)) - 1;
        targetRPM = newRPM;
    }
    
    public void setCurrentRPM(double currentRPM){
        this.currentRPM = currentRPM;
    }
        
    public void run(){
        error = targetRPM - currentRPM;
        motorPower+=gain * error;
        motorPower = constrain(motorPower, 1, -1);
        if(isPositive(oldError) != isPositive(error)){
            motorPower = tbh = 0.5* (motorPower + tbh);
            oldError =  error;
        }
    }
    
    public double getMotorPower() { 
    	return motorPower;
    }
}

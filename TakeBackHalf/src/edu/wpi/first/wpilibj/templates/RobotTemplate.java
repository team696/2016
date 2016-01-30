/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import TakeBackHalfControl.TakeBackHalf;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class RobotTemplate extends IterativeRobot {
    
    Joystick control = new Joystick(1);
    
    Victor  vic1 = new Victor(10),
            vic2 = new Victor(9);
    
    AnalogChannel   encoder1 = new AnalogChannel(1),
                    encoder2 = new AnalogChannel(2);
    
    TakeBackHalfControl.TakeBackHalf    tbh1 = new TakeBackHalf(4320),
                                        tbh2 = new TakeBackHalf(3927);
    
    Timer time = new Timer();
    
    boolean[] oldButton = new boolean[3];
    
    double  goalPos = 0,
            currentRPM1 = 0,
            ticPerInch1 = 0.03528,
            oldTime1 = time.get(),
            oldEncoderVal1 = encoder1.getValue(),
            diameter1 = 2+7/8,
            speed1 = 0,
            
            currentRPM2 = 0,
            ticPerInch2 = 0.07526,
            oldTime2 = time.get(),
            oldEncoderVal2 = encoder2.getValue(),
            diameter2 = 2+7/8,
            speed2 = 0;
    
    
    public void robotInit() {
        oldButton[0] = false;
        oldButton[1] = control.getRawButton(1);
        oldButton[2] = control.getRawButton(2);
    }

    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
        if(control.getRawButton(2) != oldButton[1])goalPos+=100;
        if(control.getRawButton(1) != oldButton[0])goalPos-=100;
        oldButton[1] = control.getRawButton(1);
        oldButton[2] = control.getRawButton(2);
        
        currentRPM1 = getVelocity(oldTime1, ticPerInch1, encoder1.getValue(), oldEncoderVal1)/getCirc(diameter1);
        oldEncoderVal1 = encoder1.getValue();
        tbh1.setCurrentRPM(currentRPM1);
        tbh1.setTargetRPM(goalPos);
        
        
        currentRPM2 = getVelocity(oldTime2, ticPerInch2, encoder2.getValue(), oldEncoderVal2)/getCirc(diameter2);
        oldEncoderVal2 = encoder2.getValue();
        tbh2.setCurrentRPM(currentRPM2);
        tbh2.setTargetRPM(goalPos);
        
        speed1 = tbh1.getMotorPower();
        speed2 = tbh2.getMotorPower();
        
        System.out.println("speed1:" + speed1 + "   speed2:" + speed2 + "   goalPos:" + goalPos + " curRPM1,2:" + currentRPM1 + "   " + currentRPM2);
        
        vic1.set(speed1);
        vic2.set(speed2);
    }

    public void testPeriodic() {
    
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
}

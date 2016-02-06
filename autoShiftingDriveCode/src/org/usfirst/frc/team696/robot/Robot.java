
package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	
	Timer timer = new Timer();
	/*
	 * Set Motor Values
	 */
	int frontLeftMotor = 0;
	int rearLeftMotor = 5;
	int frontRightMotor = 4;
	int rearRightMotor = 18;
	
	/*
	 * Initiated DriveBase
	 */
	Drivebase driveControl = new Drivebase(30, 10, false);
	
	/*
	 * Initiated PowerDistributionPanel
	 */
	PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	/*
	 * Initiate Encoders
	 */
	Encoder leftEnc = new Encoder(0, 1);
	Encoder rightEnc = new Encoder(2,3);
	
	/*
	 * Initiated Joysticks
	 */
	Joystick controller = new Joystick(0);
	
	/*
	 * Initiated Doubles
	 */
	double Yaxis;
	double Xaxis;
	double leftValue;
	double rightValue;
	double leftIn = 0;
	double rightIn = 0;
	double leftEncDis = 0;
	double rightEncDis = 0;
	double oldLeftEncDis = 0;
	double oldRightEncDis = 0;
	double time = 0;
	double oldTime = 0;
	
	/*
	 * Initiated booleans
	 */
	boolean fastTurn;
	boolean oldButton6 = controller.getRawButton(6);
	boolean frontLeftOverAmped = false;
	boolean backLeftOverAmped = false;
	boolean frontRightOverAmped = false;
	boolean backRightOverAmped = false;
	
	/*
	 * Initiated RobotDrive with Motor values from above
	 */
	RobotDrive drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

	public void robotInit() {}

    public void autonomousPeriodic() {}

    public void teleopPeriodic() {
    	/*
    	 * Code that sets driving values
    	 */
    	Yaxis = controller.getRawAxis(1);
    	Xaxis = controller.getRawAxis(4);
    	fastTurn = controller.getRawButton(6);
        driveControl.setSpeeds(Yaxis, Xaxis, fastTurn);
        leftValue = driveControl.getLeft();
        rightValue = driveControl.getRight();
        
        /*
         * Supply current for shifting high and low
         */
        leftIn = (PDP.getCurrent(frontLeftMotor) + PDP.getCurrent(rearLeftMotor))/2;
        rightIn = (PDP.getCurrent(frontRightMotor) + PDP.getCurrent(rearRightMotor))/2;
        
        time = timer.get();
        leftEncDis = leftEnc.getDistance();
        rightEncDis	= rightEnc.getDistance();
//        leftIn = (leftEncDis - oldLeftEncDis)/(time - oldTime);
//        rightIn = (rightEncDis - oldRightEncDis)/(time - oldTime);
        oldLeftEncDis = leftEncDis;
        oldRightEncDis = rightEncDis;
        oldTime = time;
        
        driveControl.updateAmp(leftIn, rightIn);
        
        
        
        System.out.println("Current: " + PDP.getCurrent(frontRightMotor) + "    " + PDP.getCurrent(frontLeftMotor) + "    " + PDP.getCurrent(rearLeftMotor) + "   " + PDP.getCurrent(rearRightMotor) + "   Encoders: " + leftEnc + "   " + rightEnc);
        
        /*
         * Apply drive code
         */
//        drive.tankDrive(leftValue, rightValue);
    }

    public void testPeriodic() {}

}

package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;

public class Robot extends IterativeRobot {
	int frontLeftMotor = 0;
	int rearLeftMotor = 5;
	int frontRightMotor = 4;
	int rearRightMotor = 18;
	Drivebase driveControl = new Drivebase(30, 10, false);
	StallPreventor frontLeftStallPrevention = new StallPreventor(37);
	StallPreventor backLeftStallPrevention = new StallPreventor(37);
	StallPreventor frontRightStallPrevention = new StallPreventor(37);
	StallPreventor backRightStallPrevention = new StallPreventor(37);
	Joystick controller = new Joystick(0);
	PowerDistributionPanel PDP = new PowerDistributionPanel();
	double Yaxis;
	double Xaxis;
	double leftValue;
	double rightValue;
	boolean fastTurn;
	boolean oldButton6 = controller.getRawButton(6);
	boolean frontLeftOverAmped = false;
	boolean backLeftOverAmped = false;
	boolean frontRightOverAmped = false;
	boolean backRightOverAmped = false;
	RobotDrive drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

	public void robotInit() {

    }

    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
    	Yaxis = controller.getRawAxis(1);
    	Xaxis = controller.getRawAxis(4);
    	fastTurn = controller.getRawButton(6);
        driveControl.setSpeeds(Yaxis, Xaxis, fastTurn);
        leftValue = driveControl.getLeft();
        rightValue = driveControl.getRight();
        
        frontLeftOverAmped = frontLeftStallPrevention.isOverAmped();
        backLeftOverAmped = backLeftStallPrevention.isOverAmped();
        frontRightOverAmped = frontRightStallPrevention.isOverAmped();
        backRightOverAmped = backRightStallPrevention.isOverAmped();
        
        if(frontLeftOverAmped || backLeftOverAmped)leftValue-=0.1;
        if(frontRightOverAmped || backRightOverAmped)rightValue-=0.1;
        
        System.out.println("   overAmped: " + frontLeftOverAmped + "   " + backLeftOverAmped + "   " + frontRightOverAmped + "   " + backRightOverAmped + "   speeds: " + leftValue + "   " + rightValue);
        
        drive.tankDrive(leftValue, rightValue);
    }
    
    public void testPeriodic() {
    
    }
    
}
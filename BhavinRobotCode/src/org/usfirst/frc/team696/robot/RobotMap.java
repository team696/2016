package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static int frontLeftMotor = 1;
	public static int rearLeftMotor = 0;
	public static int frontRightMotor = 8;
	public static int rearRightMotor = 9;
	
	public static int encoderLeftA = 0;
	public static int encoderLeftB = 1;
	public static int encoderRightA = 2;
	public static int encoderRightB = 3;
	
	public static int shifterSolenoid = 7;
	
	public static int pivotMotor = 0;
	
	public static int topMotor = 0;
	public static int bottomMotor = 0;
	public static int topMotorEncoderA = 0;
	public static int bottomMotorEncoderA = 0;
	public static int topMotorEncoderB = 0;
	public static int bottomMotorEncoderB = 0;
	
	public static int gyroChannel = 0;
	
	public static int pivotArmEncoderA = 0;
	public static int pivotArmEncoderB = 0;
	
	public static int pivotArmLimitSwitchChannel = 0;
	
	public static int fireSolenoid = 0;
	
	public static int joystick = 0;
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

package org.usfirst.frc.team696.robot;

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
	
	public static int topPivotMotor = 5;
	public static int bottomPivotMotor = 6;
	
	public static int topShooterMotor = 2;
	public static int bottomShooterMotor = 3; //7 in real robot
	
	public static int telescopingMotorLeft = 10;
	public static int telescopingMotorRight = 11;
	
	public static int encoderLeftA = 0;
	public static int encoderLeftB = 1;
	public static int encoderRightA = 2;
	public static int encoderRightB = 3;

	public static int topShooterWheelEncoderA = 6;
	public static int topShooterWheelEncoderB = 7;
	public static int bottomShooterWheelEncoderA = 8;
	public static int bottomShooterWheelEncoderB = 9;
	
	public static int pivotEncoderA = 5;
	public static int pivotEncoderB = 4;
	
	public static int telescopingArmEncoderA = 10;
	public static int telescopingArmEncoderB = 11;
	
	public static int pivotRatchetSolChannel = 0;
	public static int pivotSwitchChannel = 6;
	
	public static int shootSolenoidChannel = 6;
	public static int shifterSolenoidChannel = 7;
	
	public static int telescopingSolenoid = 8;
	
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

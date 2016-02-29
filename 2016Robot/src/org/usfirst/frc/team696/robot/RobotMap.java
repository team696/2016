package org.usfirst.frc.team696.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static int frontLeftMotor = 0;
	public static int rearLeftMotor = 1;
	public static int frontRightMotor = 9;
	public static int rearRightMotor = 8;
	
	public static int topPivotMotor = 5;
	public static int bottomPivotMotor = 6;
	
	public static int topShooterMotor = 2;
	public static int bottomShooterMotor = 7;
	
	public static int telescopingMotorTop = 4;
	public static int telescopingMotorBot = 3;
	
	public static int encoderLeftA = 2;
	public static int encoderLeftB = 3;
	public static int encoderRightA = 1;
	public static int encoderRightB = 0;

	public static int topShooterWheelEncoderA = 8;
	public static int topShooterWheelEncoderB = 9;
	public static int bottomShooterWheelEncoderA = 6;
	public static int bottomShooterWheelEncoderB = 7;
	
	public static int pivotEncoderA = 5;
	public static int pivotEncoderB = 4;
	
	public static int telescopingArmEncoderA = 10;
	public static int telescopingArmEncoderB = 11;
	
	public static int pivotRatchetSolChannel = 4;
	public static int winchSolenoid = 5;

	public static int shootSolenoidChannel = 6;
	public static int shifterSolenoidChannel = 7;

}

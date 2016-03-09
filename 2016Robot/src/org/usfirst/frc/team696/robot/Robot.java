
package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.autonomousCommands.DoNothing;
import org.usfirst.frc.team696.robot.autonomousCommands.LowBarAutoPickUp;
import org.usfirst.frc.team696.robot.runningCommands.RunningPivot;
import org.usfirst.frc.team696.robot.runningCommands.RunningShooter;
import org.usfirst.frc.team696.robot.runningCommands.RunningTelescopingArm;
import org.usfirst.frc.team696.robot.runningCommands.TeleopDrive;
import org.usfirst.frc.team696.robot.subsystems.ChassisSystem;
import org.usfirst.frc.team696.robot.subsystems.PivotArmSystem;
import org.usfirst.frc.team696.robot.subsystems.ShifterSystem;
import org.usfirst.frc.team696.robot.subsystems.ShootSystem;
import org.usfirst.frc.team696.robot.subsystems.ShooterSystem;
// import org.usfirst.frc.team696.robot.subsystems.TelescopingArmSystem;
import org.usfirst.frc.team696.robot.subsystems.TelescopingArmSystem;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static ChassisSystem chassis= new ChassisSystem();
	public static ShifterSystem shifter = new ShifterSystem();
	public static PivotArmSystem pivotArm = new PivotArmSystem();
	public static ShootSystem shoot = new ShootSystem();
	public static ShooterSystem shooter = new ShooterSystem();
	public static TelescopingArmSystem telescopingArmSystem = new TelescopingArmSystem();
	
	public static Encoder leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB);
	public static Encoder rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB);
	
	public static boolean shiftedHigh = true;
    Command autonomousCommand;
    SendableChooser chooser;
    public static IMU navX;
	SerialPort port;
	public static boolean fastTurn = false;
	
	public static Encoder topShooterWheelEncoder = new Encoder(RobotMap.topShooterWheelEncoderA, RobotMap.topShooterWheelEncoderB, false,EncodingType.k1X);
	public static Encoder botShooterWheelEncoder = new Encoder(RobotMap.bottomShooterWheelEncoderA, RobotMap.bottomShooterWheelEncoderB, false, EncodingType.k1X);
	
	public static Encoder pivotEncoder = new Encoder(RobotMap.pivotEncoderA,RobotMap.pivotEncoderB);
	public static Encoder telescopingEncoder = new Encoder(RobotMap.telescopingArmEncoderA, RobotMap.telescopingArmEncoderB);
	
	public static double telescopingTargetDistance = 0;
	
	public static double targetAngle = 0;
	public static double topShooterRPM = 0;
	public static double botShooterRPM = 0;
	public static double topShooterSpeed = 0;
	public static double botShooterSpeed = 0;
	public static boolean isRPM = true;
	
	public static Timer shootTimer = new Timer();
	Timer matchTimer = new Timer();
	
	public static PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	public static boolean isAtSpeed = false;
	
	public static int state = 0;
	public static boolean startReleaseRatchetTimer = false;
	
	public static boolean useEncoder = true;
	
	public static int shifterState = 0;
//	public static boolean manualPivotControl = false;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new DoNothing());
        chooser.addObject("Low Bar", new LowBarAutoPickUp());
        chooser.addObject("Do Nothing", new DoNothing());
        SmartDashboard.putData("Auto mode", chooser);
        try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
		shootTimer.start();
		leftEncoder.setDistancePerPulse(0.009765625);
		rightEncoder.setDistancePerPulse(0.009765625);
//		navX.zeroYaw();
		
		SmartDashboard.putNumber("Right Drive Encoder", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Drive Encoder", leftEncoder.getDistance());
		SmartDashboard.putNumber("Pivot Encoder", pivotEncoder.getDistance());
		SmartDashboard.putNumber("Bottom Shooter Encoder", botShooterWheelEncoder.getDistance());
		SmartDashboard.putNumber("Top Shooter Encoder", topShooterWheelEncoder.getDistance());
		SmartDashboard.putNumber("Telescoping Encoder", telescopingEncoder.getDistance());
		SmartDashboard.putNumber("Yaw", navX.getYaw());
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	matchTimer.start();
        autonomousCommand = (Command) chooser.getSelected();
//        String autoSelected = SmartDashboard.getString("Auto Selector", "Default Auto");
//		switch(autoSelected) {
//		case "Low Bar":
//			autonomousCommand = new LowBarAutoPickUp();
//			break;
//		case "Default Auto":
//		default:
//			autonomousCommand = new DoNothing();
//			break;
//		} 
    	
        Scheduler.getInstance().add(new RunningPivot());
        Scheduler.getInstance().add(new RunningShooter());
        
        if (autonomousCommand != null) autonomousCommand.start();
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	SmartDashboard.putNumber("Right Drive Encoder", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Drive Encoder", leftEncoder.getDistance());
		SmartDashboard.putNumber("Pivot Encoder", pivotEncoder.getDistance());
		SmartDashboard.putNumber("Bottom Shooter Encoder", botShooterWheelEncoder.getDistance());
		SmartDashboard.putNumber("Top Shooter Encoder", topShooterWheelEncoder.getDistance());
		SmartDashboard.putNumber("Telescoping Encoder", telescopingEncoder.getDistance());
		SmartDashboard.putNumber("Yaw", navX.getYaw());
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	state = 0;
    	Scheduler.getInstance().add(new TeleopDrive());
    	Scheduler.getInstance().add(new RunningPivot());
    	Scheduler.getInstance().add(new RunningShooter());
    	Scheduler.getInstance().add(new RunningTelescopingArm());
    	
    	System.out.println("Teleop Init");
    	if (autonomousCommand != null) autonomousCommand.cancel();
    	matchTimer.stop();
    	matchTimer.reset();
    	matchTimer.start();
    }

    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putNumber("Right Drive Encoder", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Drive Encoder", leftEncoder.getDistance());
		SmartDashboard.putNumber("Pivot Encoder", pivotEncoder.getDistance());
		SmartDashboard.putNumber("Bottom Shooter Encoder", botShooterWheelEncoder.getDistance());
		SmartDashboard.putNumber("Top Shooter Encoder", topShooterWheelEncoder.getDistance());
		SmartDashboard.putNumber("Telescoping Encoder", telescopingEncoder.getDistance());
		SmartDashboard.putNumber("Yaw", navX.getYaw());
    	Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

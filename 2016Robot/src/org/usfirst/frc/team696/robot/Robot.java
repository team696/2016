
package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.Pivot;
import org.usfirst.frc.team696.robot.commands.TeleopDrive;
import org.usfirst.frc.team696.robot.subsystems.ChassisSystem;
import org.usfirst.frc.team696.robot.subsystems.PivotArmSystem;
import org.usfirst.frc.team696.robot.subsystems.ShifterSystem;
import org.usfirst.frc.team696.robot.subsystems.ShootSystem;
import org.usfirst.frc.team696.robot.subsystems.ShooterSystem;
// import org.usfirst.frc.team696.robot.subsystems.TelescopingArmSystem;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
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
import or.usfirst.frc.team696.robot.runningCommands.RunningPivot;
import or.usfirst.frc.team696.robot.runningCommands.ShooterRunning;

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
	
	public static Encoder leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB);
	public static Encoder rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB);
	
	public boolean shiftedHigh = false;
    Command autonomousCommand;
    SendableChooser chooser;
    public static IMU navX;
	SerialPort port;
	public static boolean fastTurn = false;
	
	public static Encoder topShooterWheelEncoder = new Encoder(RobotMap.topShooterWheelEncoderA, RobotMap.topShooterWheelEncoderB, false,EncodingType.k1X);
	public static Encoder bottomShooterWheelEncoder = new Encoder(RobotMap.bottomShooterWheelEncoderA, RobotMap.bottomShooterWheelEncoderB, false, EncodingType.k1X);
	
	public static Encoder pivotEncoder = new Encoder(RobotMap.pivotEncoderA,RobotMap.pivotEncoderB);
	public static Encoder telescopingEncoder = new Encoder(RobotMap.telescopingArmEncoderA, RobotMap.telescopingArmEncoderB);
	
	public static double targetAngle = 0;
	public static double topShooterRPM = 0;
	public static double botShooterRPM = 0;
	
	public static Timer shootTimer = new Timer();
	
	public static PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
//        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
		shootTimer.start();
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
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	Scheduler.getInstance().add(new TeleopDrive());
    	Scheduler.getInstance().add(new RunningPivot());
    	Scheduler.getInstance().add(new ShooterRunning());
    	System.out.println("Teleop Init");
    	if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

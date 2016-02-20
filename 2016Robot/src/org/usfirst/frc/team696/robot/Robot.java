
package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.TeleopDrive;
import org.usfirst.frc.team696.robot.subsystems.Chassis;
import org.usfirst.frc.team696.robot.subsystems.PivotArm;
import org.usfirst.frc.team696.robot.subsystems.Shifter;
import org.usfirst.frc.team696.robot.subsystems.Shoot;
import org.usfirst.frc.team696.robot.subsystems.Shooter;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
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
	public static Chassis chassis= new Chassis();
	public static Shifter shifter = new Shifter();
	public static PivotArm pivotArm = new PivotArm();
	public static Shoot shoot = new Shoot();
	public static Shooter shooter = new Shooter();
	
	public static Encoder leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB);
	public static Encoder rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB);
	
	public boolean shiftedHigh = false;
    Command autonomousCommand;
    SendableChooser chooser;
    public static IMU navX;
	SerialPort port;
	public static boolean fastTurn = false;
	
	public static Encoder topShooterWheelEncoder = new Encoder(RobotMap.topShooterWheelEncoderA, RobotMap.topShooterWheelEncoderB);
	public static Encoder bottomShooterWheelEncoder = new Encoder(RobotMap.bottomShooterWheelEncoderA, RobotMap.bottomShooterWheelEncoderB);
	
	public static Encoder topPivotEncoder = new Encoder(RobotMap.topPivotEncoderA,RobotMap.topPivotEncoderB);
    
	
	public static DigitalInput zeroingPivotSwitch = new DigitalInput(RobotMap.pivotSwitchChannel);

	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	topShooterWheelEncoder.setDistancePerPulse(1);
    	bottomShooterWheelEncoder.setDistancePerPulse(1);
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

//		navX.zeroYaw();
//		navX.getYaw();
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
    	
    	// schedule the autonomous command (example)
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


package org.usfirst.frc.team696.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.nav6.frc.IMU;
import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    boolean oldButton = false;
    boolean button = false;
	
    public static IMU navX;
	SerialPort port;
    
    double p1;
    double p2;
    double p3;
    double p4;
    double p5;
    double p6;
    double p7;
    double p8;
    double p9;
    double p10;
    
    Joystick arduino = new Joystick(1);
    
    Encoder enc1 = new Encoder(0,1);
    Encoder enc2 = new Encoder(2,3);
    Encoder enc3 = new Encoder(4,5);
    Encoder enc4 = new Encoder(6,7);
    Encoder enc5 = new Encoder(8,9);
    Encoder enc6 = new Encoder(10,11);
    
    Solenoid sol1 = new Solenoid(4);
    Solenoid sol2 = new Solenoid(5);
    Solenoid sol3 = new Solenoid(6);
    Solenoid sol4 = new Solenoid(7);
    
    Victor vic0 = new Victor(0);
    Victor vic1 = new Victor(1);
    Victor vic2 = new Victor(2);
    Victor vic3 = new Victor(3);
    Victor vic4 = new Victor(4);
    Victor vic5 = new Victor(5);
    Victor vic6 = new Victor(6);
    Victor vic7 = new Victor(7);
    Victor vic8 = new Victor(8);
    Victor vic9 = new Victor(9);
    
    int state = 0;
    
    PowerDistributionPanel PDP = new PowerDistributionPanel();
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        try {
			byte UpdateRateHz = 50;
			port = new SerialPort(57600, SerialPort.Port.kMXP);
			navX = new IMUAdvanced(port, UpdateRateHz);
		} catch(Exception ex){System.out.println("NavX not working");};
    }
    
    
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    double speed = 0;
    
    public void teleopPeriodic() {
//        sol1.set(control.getRawButton(1));
//        sol2.set(control.getRawButton(2));
//        sol3.set(control.getRawButton(3));
//        sol4.set(control.getRawButton(4));
        
//        speed = control.getRawAxis(1);
        if(Math.abs(speed)<0.1)speed = 0;
        
//        if(control.getRawButton(11))speed = .25;
//        if(control.getRawButton(12))speed = -.25;
//        if(control.getRawButton(13))speed = 0;
        
        if(arduino.getRawAxis(1) > 0.25) speed = -0.25;
        else if(arduino.getRawAxis(1) < -0.25) speed = 0.25;
        else speed = 0;
        
//        System.out.print("   speed:" + speed);
        System.out.println("Motor " + state + "   encoder: one:" + enc1.get() + "   two:" + enc2.get() + "   three:" + enc3.get() + "   four:" + enc4.get() + "   five:" + enc5.get() + "   six:" + enc6.get());

        sol1.set(arduino.getRawButton(7));
        sol2.set(arduino.getRawButton(8));
        sol3.set(arduino.getRawButton(9));
        sol4.set(arduino.getRawButton(10));
        
        button = arduino.getRawButton(3);
        
        if(button && !oldButton)state++;
        if(state > 9)state = 0;
        
        oldButton = button;
        
        switch(state){
        case 0:
        	vic0.set(speed);//inverted?
        	break;
        case 1:
        	vic1.set(speed);//inverted?
        	break;
        case 2:
		    vic2.set(speed);//inverted?
		    break;
        case 3:
		    vic3.set(speed);//inverted?
		    break;
        case 4:
		    vic4.set(speed);//inverted?
		    break;
        case 5:
		    vic5.set(speed);//inverted?
		    break;
        case 6:
		    vic6.set(speed);//inverted?
		    break;
        case 7:
		    vic7.set(speed);//inverted?
		    break;
        case 8:
		    vic8.set(speed);//inverted?
		    break;
        case 9:
		    vic9.set(speed);//inverted?
		    break;
        }
        
        
//        p1 = PDP.getCurrent(14);
//        p2 = PDP.getCurrent(13);
//        p3 = PDP.getCurrent(12);
//        p4 = PDP.getCurrent(9);
//        p5 = PDP.getCurrent(8);
//        p6 = PDP.getCurrent(7);
//        p7 = PDP.getCurrent(6);
//        p8 = PDP.getCurrent(3);
//        p9 = PDP.getCurrent(2);
//        p10 = PDP.getCurrent(1);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PivotArm extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Victor topMotor = new Victor(RobotMap.topPivotMotor);
	Victor bottomMotor = new Victor(RobotMap.bottomPivotMotor);
	
	double speed = 0.0;
	public boolean usePID;
	
	private static final double vp = 0.0;
	private static final double vi = 0.0;
	private static final double vk = 0.0;
	
	public PivotArm() {
		super("PivotArm", vp, vi, vk);
	}
	
	protected double returnPIDInput() {
	        // Return your input value for the PID loop
	        // e.g. a sensor, like a potentiometer:
	        // yourPot.getAverageVoltage() / kYourMaxVoltage;
	    	return 0.0;
	}
	    
	protected void usePIDOutput(double output) {
		if (usePID) {
	        topMotor.set(output);
	        bottomMotor.set(output);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed){
    	this.speed = speed;
    	run();
    }
    
    public void togglePID(boolean toggle){
    	this.usePID = toggle;
    }
    
    private void run() {
    	topMotor.set(speed);
    	bottomMotor.set(-speed);
    }
    
    public double getSpeed() {
		return speed;
	}
									
    //find distancePerPulse
	public void setDistancePerPulse(double distancePerPulse) {
	}
	
	public double getTopEncoderDistance() {
		return Robot.topPivotEncoder.getDistance();
		// total distance
	}
}





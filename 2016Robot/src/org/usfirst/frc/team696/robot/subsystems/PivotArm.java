package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.robot.Util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PivotArm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Victor topMotor = new Victor(RobotMap.topPivotMotor);
	Victor bottomMotor = new Victor(RobotMap.bottomPivotMotor);
	
	double speed = 0.0;
	double targetAngle = 0;
	double output = 0;
	double error = 0;
	boolean usePID;
	
	private static final double kpUp = 0.0;
	private static final double kiUp = 0.0;
	private static final double kdUp = 0.0;
	
	private static final double kpDown = 0.0;
	private static final double kiDown = 0.0;
	private static final double kdDown = 0.0;
	
	PIDController PIDTop = new PIDController(0, 0, 0, Robot.topPivotEncoder, topMotor);
	PIDController PIDBot = new PIDController(0, 0, 0, Robot.topPivotEncoder, bottomMotor);
	
	public PivotArm() {
		PIDTop.enable();
		PIDBot.enable();
		PIDTop.setPID(kpDown, kiDown, kdDown);
		PIDBot.setPID(kpDown, kiDown, kdDown);
	}
	
//	protected double returnPIDInput() {
//	        // Return your input value for the PID loop
//	        // e.g. a sensor, like a potentiometer:
//	        // yourPot.getAverageVoltage() / kYourMaxVoltage;
//	    	return 0.0;
//	}
//	    
//	protected void usePIDOutput(double output) {
//		if (usePID) {
//			Util.constrain(output, -1, 1);
//	        setMotorSpeed(output);
//		}
//	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private void setMotorSpeed(double speed){
    	topMotor.set(speed);
        bottomMotor.set(speed);
    }
    
    public void setSpeed(double speed){
    	setMotorSpeed(speed);
    	this.speed = speed;
    }
    
    public void togglePID(boolean usePID){
    	if(usePID){
    		PIDTop.enable();
    		PIDBot.enable();
    	} else {
    		PIDTop.disable();
    		PIDTop.disable();
    	}
    }
    
    /**
     * 
     * @param targetAngle must be in degrees
     */
    public void setTargetAngle(double targetAngle){
    	this.targetAngle = targetAngle;
    	error = this.targetAngle - Robot.topPivotEncoder.getDistance();
    	if(error < 0){
    		PIDTop.setPID(kpUp, kiUp, kdUp);
    		PIDBot.setPID(kpUp, kiUp, kdUp);
    	}else {
    		PIDTop.setPID(kpDown, kiDown, kdDown);
    		PIDBot.setPID(kpDown, kiDown, kdDown);
    	}
    	
    }
    
    public double getSpeed() {
		return speed;
	}
									
    //find distancePerPulse
	public void setDistancePerPulse(double distancePerPulse) {
		Robot.topPivotEncoder.setDistancePerPulse(distancePerPulse);
	}
	
	public double getTopEncoderDistance() {
		return Robot.topPivotEncoder.getDistance();
		// total distance
	}
}





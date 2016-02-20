package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.DoubleMotor;
import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.robot.Util;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PivotArm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DoubleMotor pivotMotors= new DoubleMotor(RobotMap.topPivotMotor, RobotMap.bottomPivotMotor);;
	
	double speed = 0.0;
	double targetAngle = 0;
	double error = 0;
	double PIDSum = 0;
	double cumulativeError = 0;
	double alpha = 0;
	
	private static final double kpUp = 0.01;
	private static final double kiUp = 0.0;
	private static final double kdUp = 0.0;
	
	private static final double kpDown = 0.0;
	private static final double kiDown = 0.0;
	private static final double kdDown = 0.0;
	
	private static double kp = 0.0;
	private static double ki = 0.0;
	private static double kd = 0.0;
	
	public PivotArm() {
//		PID.enable();
//		PID.setPID(kp, ki, kd);
	}
	
	private void setDown(){
		kp = kpDown;
		ki = kiDown;
		kd = kdDown;
	}
	
	private void setUp(){
		kp = kpUp;
		ki = kiUp;
		kd = kdUp;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed){
    	this.speed = speed;
    }
    
    public void togglePID(boolean usePID){
    	if(usePID){
//    		PID.enable();
    	} else {
//    		PID.disable();
    	}
    }
    
    /**
     * 
     * @param targetAngle must be in degrees
     */
    public void setTargetAngle(double targetAngle){
    	this.targetAngle = targetAngle;
    	error = this.targetAngle - 0/*Robot.pivotEncoder.getDistance()*/;
    	if(error < 0)setUp();
    	else setDown();
    	cumulativeError *= alpha;
    	cumulativeError += error;
    	PIDSum = (error * kp) + (cumulativeError * ki);
    	PIDSum = Util.constrain(PIDSum, -1, 1);
    	speed = PIDSum;
    }

    public void run(){
    	pivotMotors.set(speed);
    }
    
    public double getSpeed() {
		return speed;
	}
									
    //find distancePerPulse
	public void setDistancePerPulse(double distancePerPulse) {
//		Robot.topPivotEncoder.setDistancePerPulse(distancePerPulse);
	}
	
	public double getTopEncoderDistance() {
//		return Robot.topPivotEncoder.getDistance();
		return 0;
		// total distance
	}
}

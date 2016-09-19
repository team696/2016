package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.utilities.DoubleMotor;
import org.usfirst.frc.team696.utilities.PIDControl;
import org.usfirst.frc.team696.utilities.StallPrevention;
import org.usfirst.frc.team696.utilities.Util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PivotArmSystem extends Subsystem {
    
//	DoubleMotor pivotMotors= new DoubleMotor(RobotMap.topPivotMotor, RobotMap.bottomPivotMotor);;
	Victor topPivotMotor = new Victor(RobotMap.topPivotMotor);
	Victor botPivotMotor = new Victor(RobotMap.bottomPivotMotor);
	Solenoid pivotRatchetSol = new Solenoid(RobotMap.pivotRatchetSolChannel);
	StallPrevention topSP = new StallPrevention(30);
	StallPrevention botSP = new StallPrevention(30);
	Timer discBreakTimer = new Timer();
	DigitalInput topLimit = new DigitalInput(19);
	DigitalInput bottomLimit = new DigitalInput(20);
	
	boolean ratchet = false;
	
	double speed = 0.0;
	double targetAngle = 0;
	double error = 0;
	double PIDSum = 0;
	double cumulativeError = 0;
	double alpha = 0;
	
	private static double kP = 0.03;
	
	PIDControl PID = new PIDControl(kP);
	
	double oldPivotEncoder = 0;
	boolean broken = false;
	double pivotError = 0;
	
	public PivotArmSystem() {
	}

	public void initDefaultCommand() {
    }
    
    public void setSpeed(double speed){
    	topSP.setCurrentAmps(Robot.PDP.getCurrent(7), speed);
    	botSP.setCurrentAmps(Robot.PDP.getCurrent(6), speed);
    	if(topSP.getOutput() == 0 || botSP.getOutput() == 0)this.speed = 0;
    	else this.speed = speed;
    	run();
    	System.out.println(speed + "     " + Robot.PDP.getCurrent(7));
    }
    
    public void togglePID(boolean usePID){}
    
    /**
     * 
     * @param targetAngle must be in degrees
     */
    public void setTargetAngle(double targetAngle){
    	if(targetAngle <= 10/*test*/)targetAngle = 11;
    	this.targetAngle = targetAngle;
    	
    	/*
    	 * decide speed value for pivot
    	 */
    	error = this.targetAngle - Robot.pivotEncoder.get();
    	error = Util.deadZone(error, -3, 3, 0);
    	PID.setError(error);
    	speed = Util.constrain(PID.getValue(), -Robot.pivotConstrainSpeed, Robot.pivotConstrainSpeed);
    	if(!Robot.endOfMatch)speed = Util.constrain(PID.getValue(), -1, 1);
    	
    	/*
    	 * decide whether encoder is broken on pivot
    	 * TO BE TESZTED
    	 */
    	pivotError = Robot.pivotEncoder.get() - oldPivotEncoder;
    	if((speed == 0 && pivotError == 0) || (speed > 0 && pivotError > 0) || (speed < 0 && pivotError < 0))broken = false;
    	else broken = true; 
    	oldPivotEncoder = Robot.pivotEncoder.get();
    	
    	/*
    	 * what to do if broken 
    	 * TO BE TESTED
    	 */
    	if(broken){
    		if(this.targetAngle > 50){
    			if(topLimit.get())speed = 0;
    			else speed = 0.25;
    		}
    		if(this.targetAngle < 50){
    			if(bottomLimit.get())speed = 0;
    			else speed = -0.25;
    		}
    	}
    	
    	/*
    	 * constrain speed to pivotConstrainSpeed constant
    	 */
    	speed = Util.constrain(speed, -Robot.pivotConstrainSpeed, Robot.pivotConstrainSpeed);

    	/*
    	 * Disc Break Code
    	 * TO BE TESTED
    	 */
//    	if(speed != 0 && discBreakTimer.get() == 0){
//    		discBreakTimer.start();
//    		brake(false);
//    	}
//    	if(speed == 0){
//    		discBreakTimer.stop();
//    		discBreakTimer.reset();
//    		brake(true);
//    	}
//    	if(discBreakTimer.get() < 0.1)speed = 0;//jack
    	
    	//manual control with straight out voltage
//    	if(error > 0)speed = 0.7;
//    	if(error < 0)speed = -0.7;
    	
    	run();
    }

    public void brake(boolean ratcheted){
    	pivotRatchetSol.set(!ratcheted);
    }
    
    public void run(){
		topPivotMotor.set(-speed);
		botPivotMotor.set(-speed);
    }
    
    public double getSpeed() {
		return speed;
	}
									
}

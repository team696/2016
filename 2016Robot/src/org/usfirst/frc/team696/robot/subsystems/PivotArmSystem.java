package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.utilities.DoubleMotor;
import org.usfirst.frc.team696.utilities.PIDControl;
import org.usfirst.frc.team696.utilities.StallPrevention;
import org.usfirst.frc.team696.utilities.Util;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PivotArmSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	DoubleMotor pivotMotors= new DoubleMotor(RobotMap.topPivotMotor, RobotMap.bottomPivotMotor);;
	Solenoid pivotRatchetSol = new Solenoid(RobotMap.pivotRatchetSolChannel);
	StallPrevention topSP = new StallPrevention(30);
	StallPrevention botSP = new StallPrevention(30);
	
	
	boolean notRatchet = true;
	
	double speed = 0.0;
	double targetAngle = 0;
	double error = 0;
	double PIDSum = 0;
	double cumulativeError = 0;
	double alpha = 0;
	
	private static double kP = 0.05;
	
	PIDControl PID = new PIDControl(kP);
	
	public PivotArmSystem() {
	}

	public void initDefaultCommand() {
    }
    
    public void setSpeed(double speed){
    	topSP.setCurrentAmps(Robot.PDP.getCurrent(7), speed);
    	botSP.setCurrentAmps(Robot.PDP.getCurrent(6), speed);
    	if(topSP.getOutput() == 0 || botSP.getOutput() == 0)this.speed = 0;
    	else this.speed = speed;
    }
    
    public void togglePID(boolean usePID){}
    
    /**
     * 
     * @param targetAngle must be in degrees
     */
    public void setTargetAngle(double targetAngle){
    	this.targetAngle = targetAngle;
    	error = this.targetAngle - Robot.pivotEncoder.get();
    	PID.setError(error);
    	speed = Util.constrain(PID.getValue(), -1, 1);
    	pivotRatchetSol.set(notRatchet);
    	run();
    }

    public void run(){
    	pivotMotors.set(speed);
    }
    
    public double getSpeed() {
		return speed;
	}
									
}

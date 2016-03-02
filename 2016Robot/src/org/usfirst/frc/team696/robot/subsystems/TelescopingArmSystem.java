package org.usfirst.frc.team696.robot.subsystems;

import org.usfirst.frc.team696.robot.RobotMap;
import org.usfirst.frc.team696.utilities.DoubleMotor;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class TelescopingArmSystem extends Subsystem {
	
	double speed = 0;
	double targetDis = 0;
	double currentDis = 0;
	
	public boolean pulling = false;
	public boolean finished = false;
	
    DoubleMotor telescopingArms = new DoubleMotor(RobotMap.telescopingMotorTop, RobotMap.telescopingMotorBot);
    Solenoid telescopingRatchet = new Solenoid(RobotMap.telescopingSolenoid);
    
    public void ratchet(boolean ratcheted) {
    	telescopingRatchet.set(!ratcheted);
    }
    
    public void set(double speed){
    	telescopingArms.set(speed);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

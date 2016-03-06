package org.usfirst.frc.team696.robot.runningCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.PIDControl;
import org.usfirst.frc.team696.utilities.Util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopDrive extends Command {

	double speed = 0;
	double turnValue = 0;
	double leftSpeed = 0;
	double rightSpeed = 0;
	double goalAngle;
	double currentAngle;
	double kPHigh = 0.0105;
	double kIHigh = 0.0002;
	double kDHigh = 0.0003;
	double kPLow  = 0.009;
	double kILow  = 0.00015;
	double kDLow  = 0.00025;
	double alpha = 0.95;
	double cumulativeError = 0;
	double derivativeError = 0;
	double delta = 0;
	double oldDelta = 0;
	double PIDSum = 0;
	double output = 0;
	double wheelNonLinearity = 0;
	boolean fastTurn = false;
	boolean regularDrive = false;
	boolean arcingDrive = true;
	
	PIDControl PID = new PIDControl(kPHigh, kIHigh, kDHigh, alpha);
	
    public TeleopDrive() {
    	requires(Robot.chassis);
    }

    protected void initialize() {
    	Robot.navX.zeroYaw();
    	goalAngle = Robot.navX.getYaw();
    	currentAngle = Robot.navX.getYaw();
    }

    protected void execute() {
//    	if(Robot.shiftedHigh)PID.setPID(kPHigh, kIHigh, kDHigh, alpha);
//    	else PID.setPID(kPLow, kILow, kDLow, alpha);
    	
    	speed = Robot.oi.arduino.getRawAxis(4);
    	speed = Util.deadZone(speed, -0.05, 0.05, 0);
    	
    	turnValue = Robot.oi.wheel.getAxis(Joystick.AxisType.kX);
    	turnValue = Util.smoothDeadZone(turnValue, -0.2, 0.2, -1, 1, 0);
    	
    	fastTurn = Robot.fastTurn;
    	if(fastTurn)turnValue*=2;
    	
    	leftSpeed = speed;
    	rightSpeed = speed;

    	if(regularDrive)regularArcade();
    	else if(arcingDrive)arcingDrive();
    	else driveStraight();
    	
    	
    	Robot.chassis.setSpeeds(leftSpeed, rightSpeed);
    	
    }
    
    public void arcingDrive(){
    	if(!fastTurn){
	    	if (Robot.shiftedHigh) {
	            wheelNonLinearity = 0.6;
	            // Apply a sin function that's scaled to make it feel better.
	            turnValue = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turnValue)
	                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
	            turnValue = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turnValue)
	                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
	        } else {
	            wheelNonLinearity = 0.5;
	            // Apply a sin function that's scaled to make it feel better.
	            turnValue = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turnValue)
	                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
	            turnValue = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turnValue)
	                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
	            turnValue = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turnValue)
	                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
	        }
    	}
    	
    	leftSpeed-=turnValue;
    	rightSpeed+= turnValue;
    	
    }
    
    public void regularArcade(){
    	if(Robot.fastTurn)turnValue*=2;
    	leftSpeed-=turnValue;
    	rightSpeed+=turnValue;
    }
    
    public void driveStraight(){
    	goalAngle+=(turnValue*1.5);
    	currentAngle = Robot.navX.getYaw();
    	
    	if(goalAngle > 180)goalAngle-=360;
    	if(goalAngle < -180)goalAngle+=360;
    	
    	if(turnValue == 0 && speed == 0)goalAngle = currentAngle;
    	
    	delta = currentAngle - goalAngle;
    	if(delta > 180)delta = delta - 360;
    	if(delta < -180)delta = 360 + delta;
    	delta = Util.deadZone(delta, -1, 1, 0);
    	
    	if(Util.signOf(delta) != Util.signOf(oldDelta))cumulativeError = 0;
        
        PID.setError(delta);
        output = PID.getValue();
        
    	leftSpeed+=output;
    	rightSpeed-=output;
    	
    	if(Math.abs(leftSpeed)>1)rightSpeed-=(Math.abs(leftSpeed)-1) * Util.signOf(leftSpeed);
    	if(Math.abs(rightSpeed)>1)leftSpeed-=(Math.abs(rightSpeed)-1) * Util.signOf(rightSpeed);

    	leftSpeed = Util.constrain(leftSpeed, -1, 1);
    	rightSpeed = Util.constrain(rightSpeed, -1, 1);
    	
    	oldDelta = delta;
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

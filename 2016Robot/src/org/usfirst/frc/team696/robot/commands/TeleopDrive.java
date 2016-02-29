package org.usfirst.frc.team696.robot.commands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.Util;

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
	double kP = 0.0105;
	double kI = 0.0002;
	double kD = 0.0003;
	double cumulativeError = 0;
	double derivativeError = 0;
	double alpha = 0.95;
	double delta = 0;
	double oldDelta = 0;
	double PIDSum = 0;
	boolean fastTurn = false;
	
    public TeleopDrive() {
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.navX.zeroYaw();
    	goalAngle = Robot.navX.getYaw();
    	currentAngle = Robot.navX.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	fastTurn = Robot.fastTurn;
    	speed = Robot.oi.controlBoard.getAxis(Joystick.AxisType.kY);
    	speed = Util.deadZone(speed, -0.025, 0.025, 0);
    	turnValue = Robot.oi.wheel.getAxis(Joystick.AxisType.kX);
    	turnValue = Util.smoothDeadZone(turnValue, -0.03, 0.03, -1, 1, 0);
    	if(fastTurn)turnValue*=2;
    	
    	leftSpeed = speed;
    	rightSpeed = speed;

    	goalAngle+=(turnValue*1.5);
    	currentAngle = Robot.navX.getYaw();
    	
    	if(goalAngle > 180)goalAngle-=360;
    	if(goalAngle < -180)goalAngle+=360;
    	
    	if(turnValue == 0)goalAngle = currentAngle;
    	
    	delta = currentAngle - goalAngle;
    	if(delta > 180)delta = delta - 360;
    	if(delta < -180)delta = 360 + delta;
    	delta = Util.deadZone(delta, -1, 1, 0);
    	
//    	if(speed == 0 && delta != 0){
////    		delta = Util.deadZone(delta, -45, 45, 0);
//    		speed = 0.02;
//    	}
    	
    	System.out.println("turnValue: " + turnValue + "delta:   " + delta);
    	
    	if(Util.signOf(delta) != Util.signOf(oldDelta))cumulativeError = 0;
    	
    	cumulativeError *= alpha;
    	cumulativeError += delta;
    	
    	derivativeError = delta - oldDelta;
    	oldDelta = delta;
        PIDSum = delta * kP + cumulativeError * kI + derivativeError * kD;
        
    	leftSpeed+=PIDSum;
    	rightSpeed-=PIDSum;
    	
    	if(Math.abs(leftSpeed)>1)rightSpeed-=(Math.abs(leftSpeed)-1) * Util.signOf(leftSpeed);
    	if(Math.abs(rightSpeed)>1)leftSpeed-=(Math.abs(rightSpeed)-1) * Util.signOf(rightSpeed);

    	leftSpeed = Util.constrain(leftSpeed, -1, 1);
    	rightSpeed = Util.constrain(rightSpeed, -1, 1);
    	
    	oldDelta = delta;
    	
    	
    	Robot.chassis.setSpeeds(leftSpeed, rightSpeed);
//    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

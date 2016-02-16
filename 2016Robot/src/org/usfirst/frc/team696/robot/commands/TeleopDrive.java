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
	double kP = 0.0115;
	double delta = 0;
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
    	turnValue = Robot.oi.controlBoard.getAxis(Joystick.AxisType.kX);
    	turnValue = Util.map(turnValue, -0.75, 0.63, -1, 1);
    	turnValue = Util.smoothDeadZone(turnValue, -0.125, 0.125, -1, 1, 0);
    	if(fastTurn)turnValue*=2;
    	
    	leftSpeed = speed;
    	rightSpeed = speed;

    	goalAngle+=(turnValue*1.5);
    	currentAngle = Robot.navX.getYaw();
    	
    	if(goalAngle > 180)goalAngle-=360;
    	if(goalAngle < -180)goalAngle+=360;
    	
    	delta = currentAngle - goalAngle;
    	if(delta > 180)delta = delta - 360;
    	if(delta < -180)delta = 360 + delta;
    	
//    	goalAngle+=(turnValue*1.5);
//    	currentAngle = Robot.navX.getYaw();

    	leftSpeed+=(delta) * kP;
    	rightSpeed-=(delta) * kP;
    	
    	if(Math.abs(leftSpeed)>1)rightSpeed-=(Math.abs(leftSpeed)-1) * Util.signOf(leftSpeed);
    	if(Math.abs(rightSpeed)>1)leftSpeed-=(Math.abs(rightSpeed)-1) * Util.signOf(rightSpeed);

    	leftSpeed = Util.constrain(leftSpeed, -1, 1);
    	rightSpeed = Util.constrain(rightSpeed, -1, 1);
    	
    	System.out.println("delta:" + delta + "  speed:" + speed + "   tv:" + turnValue + "   l:" + leftSpeed + "   r:" + rightSpeed + "   ga:" + goalAngle + "   a:" + Robot.navX.getYaw());
    	
    	//    	turnValue = Util.map(turnValue, -0.75, 0.63, -1, 1);
////    	System.out.println(speed + "   " + turnValue + "   " + fastTurn);
//    	if(fastTurn)turnValue*=2;
//    	turnValue = Util.signOf(turnValue) * Math.pow(Math.abs(Util.smoothDeadZone(turnValue, -0.125, 0.125, -1, 1, 0)),1.2);
//    	System.out.println(turnValue + "   " + leftSpeed + "   " + rightSpeed);
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

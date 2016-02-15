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
	boolean fastTurn = false;
	
    public TeleopDrive() {
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	fastTurn = Robot.fastTurn;
    	speed = Robot.oi.Joystick.getAxis(Joystick.AxisType.kY);
    	turnValue = Robot.oi.controlBoard.getAxis(Joystick.AxisType.kZ);
    	turnValue = Util.map(turnValue, -0.75, 0.63, -1, 1);
//    	System.out.println(speed + "   " + turnValue + "   " + fastTurn);
    	if(fastTurn)turnValue*=2;
    	turnValue = Util.signOf(turnValue) * Math.pow(Math.abs(Util.smoothDeadZone(turnValue, -0.125, 0.125, -1, 1, 0)),1.2);
    	leftSpeed = speed - turnValue;
    	rightSpeed = speed + turnValue;
    	System.out.println(turnValue + "   " + leftSpeed + "   " + rightSpeed);
    	Robot.chassis.setSpeeds(leftSpeed, rightSpeed);
    	
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

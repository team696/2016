package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.FastTurn;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.SetTelescopingArm;
import org.usfirst.frc.team696.robot.commands.Shift;
import org.usfirst.frc.team696.robot.commands.Shoot;
import org.usfirst.frc.team696.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team696.utilities.JoystickAnalogButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick controlBoard = new Joystick(1);
	public Joystick wheel = new Joystick(2);
	Button fastTurn = new JoystickButton(wheel, 6);
	Button shiftHighButton = new JoystickButton(wheel, 3);
	Button shiftLowButton = new JoystickButton(controlBoard, 2);
	Button pivotUpButton = new JoystickButton(controlBoard, 10);
	Button pivotDownButton = new JoystickButton(controlBoard, 11);
	Button shootButton = new JoystickButton(controlBoard, 6);
	Button shootingSpeedButton = new JoystickButton(controlBoard, 12);
	Button intakeSpeedButton = new JoystickButton(controlBoard, 13);
	Button stopWheelButton = new JoystickButton(controlBoard, 4);
	Button telescopingArmFullPosButton = new JoystickButton(controlBoard, 0);
	Button telescopingArmMidPosButton = new JoystickButton(controlBoard, 0);
	Button telescopingArmZeroPosButton = new JoystickButton(controlBoard, 0);
	Button intakePivotArmPosButton = new JoystickAnalogButton(controlBoard, 3, 0.75, 1);
	Button shootPivotArmPosButton = new JoystickAnalogButton(controlBoard, 3, -1, -0.75);
	
	public OI() {
		fastTurn.whenPressed(new FastTurn(true));
		fastTurn.whenReleased(new FastTurn(false));
		
		shiftHighButton.whenPressed(new Shift(false));
		shiftLowButton.whenPressed(new Shift(true));
		
		pivotUpButton.whileHeld(new SetPivot(true, 2.5));
		pivotDownButton.whileHeld(new SetPivot(true, -2.5));
		pivotUpButton.whenInactive(new SetPivot(true, 0));
		pivotDownButton.whenInactive(new SetPivot(true, 0));
		
		shootButton.whenPressed(new Shoot());
		shootingSpeedButton.whenPressed(new SetShooterSpeed(4500));
		intakeSpeedButton.whenPressed(new SetShooterSpeed(-3500));
		stopWheelButton.whenPressed(new SetShooterSpeed(0));
		
		intakePivotArmPosButton.whenPressed(new SetPivot(false, 0));
		shootPivotArmPosButton.whenPressed(new SetPivot(false, 160));
		
		telescopingArmFullPosButton.whenPressed(new SetTelescopingArm(2));
		telescopingArmMidPosButton.whenPressed(new SetTelescopingArm(1));
		telescopingArmZeroPosButton.whenPressed(new SetTelescopingArm(0));
	}
}


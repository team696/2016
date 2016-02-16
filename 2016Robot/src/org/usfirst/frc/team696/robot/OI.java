package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.FastTurn;
import org.usfirst.frc.team696.robot.commands.Pivot;
import org.usfirst.frc.team696.robot.commands.Shift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick controlBoard = new Joystick(1);
	Button fastTurn = new JoystickButton(controlBoard, 7);
	Button shiftHighButton = new JoystickButton(controlBoard, 9);
	Button shiftLowButton = new JoystickButton(controlBoard, 8);
	Button pivotUpButton = new JoystickButton(controlBoard, 10);
	Button pivotDownButton = new JoystickButton(controlBoard, 11);
	
	public OI() {
		fastTurn.whenPressed(new FastTurn(true));
		fastTurn.whenReleased(new FastTurn(false));
		shiftHighButton.whenPressed(new Shift(false));
		shiftLowButton.whenPressed(new Shift(true));
		pivotUpButton.whenPressed(new Pivot(false, 0.25));
		pivotUpButton.whenReleased(new Pivot(false, 0));
		pivotDownButton.whenPressed(new Pivot(false, -0.25));
		pivotDownButton.whenReleased(new Pivot(false, 0));
	}
}


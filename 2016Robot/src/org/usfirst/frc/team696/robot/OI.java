package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.FastTurn;
import org.usfirst.frc.team696.robot.commands.Shift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick Joystick = new Joystick(0);
	public Joystick controlBoard = new Joystick(1);
	Button shiftHigh = new JoystickButton(Joystick, 6);
	Button shiftLow = new JoystickButton(Joystick, 7);
	Button fastTurn = new JoystickButton(controlBoard, 7);
	
	public OI() {
		shiftHigh.whenPressed(new Shift(false));
		shiftLow.whenPressed(new Shift(true));
		fastTurn.whenPressed(new FastTurn(true));
		fastTurn.whenReleased(new FastTurn(false));
	}
}


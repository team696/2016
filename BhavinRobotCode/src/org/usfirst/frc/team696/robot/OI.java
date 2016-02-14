package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.ShiftHigh;
import org.usfirst.frc.team696.robot.commands.ShiftLow;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick control = new Joystick(0);
	Button shifting = new JoystickButton(control, 6);
	
	public OI() {
		shifting.whenPressed(new ShiftHigh());
		shifting.whenReleased(new ShiftLow());
	}
}


package org.usfirst.frc.team696.utilities;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class JoystickAnalogButton extends JoystickButton {

	double minVal = 0;
	double maxVal = 0;
	GenericHID joystick;
	int axisNum;
	
	public JoystickAnalogButton(GenericHID joystick, int axisNum, double minVal, double maxVal) {
		super(joystick, 1);
		this.joystick = joystick;
		this.axisNum = axisNum;
	}
	
	@Override
	public boolean get(){
		return (minVal < joystick.getRawAxis(axisNum)) && (joystick.getRawAxis(axisNum) < maxVal);
	}
	
}

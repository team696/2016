
package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.FastTurn;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.SetTelescopingArm;
import org.usfirst.frc.team696.robot.commands.Shift;
import org.usfirst.frc.team696.robot.commands.Shoot;
import org.usfirst.frc.team696.robot.commands.ZeroEncoders;
import org.usfirst.frc.team696.utilities.JoystickAnalogButton;
import org.usfirst.frc.team696.robot.commands.SetShooterSpeed;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick arduino = new Joystick(1);
	public Joystick wheel = new Joystick(2);
	Button fastTurn = new JoystickButton(wheel, 6);
	
	Button shiftHighButton = new JoystickButton(wheel, 3);
	Button shiftLowButton = new JoystickButton(wheel, 2);
	
	Button pivotUpButton = new JoystickAnalogButton(arduino, 1, -1.1, -0.7);
	Button pivotDownButton = new JoystickAnalogButton(arduino, 1, 0.7, 1.1);
	
	Button shootButton = new JoystickButton(arduino, 11);
	Button shootingSpeedButton = new JoystickAnalogButton(arduino, 3, 0.7, 1.1);
	Button intakeSpeedButton = new JoystickAnalogButton(arduino, 3, -1.1, -0.7);
	Button stopWheelButton = new JoystickAnalogButton(arduino, 3, -0.5, 0.5);
	
	Button telescopingArmFullPosButton = new JoystickButton(arduino, 4);
	Button telescopingArmMidPosButton = new JoystickButton(arduino, 5);
	Button telescopingArmFullRetractButton = new JoystickButton(arduino, 6);
	
	Button zeroAllEncodersButton = new JoystickButton(wheel, 7);
	
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
		
		
//		shootingSpeedButton.whenPressed(new SetShooterSpeed(4500));
//		intakeSpeedButton.whenPressed(new SetShooterSpeed(-3500));
//		stopWheelButton.whenPressed(new SetShooterSpeed(0));
		
		
		shootingSpeedButton.whenPressed(new SetShooterSpeed(false, 1));
		intakeSpeedButton.whenPressed(new SetShooterSpeed(false, -0.75));
		stopWheelButton.whileHeld(new SetShooterSpeed(false, 0));
		
//		intakePivotArmPosButton.whenPressed(new SetPivot(false, arduino.getRawAxis(1)));
//		shootPivotArmPosButton.whenPressed(new SetPivot(false, 160));
		
		telescopingArmFullPosButton.whenPressed(new SetTelescopingArm(2));
		telescopingArmMidPosButton.whenPressed(new SetTelescopingArm(1));
		
		zeroAllEncodersButton.whenPressed(new ZeroEncoders());
		
	}
}


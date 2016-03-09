
package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.FastTurn;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.SetTelescopingArm;
import org.usfirst.frc.team696.robot.commands.SetUseEncoder;
import org.usfirst.frc.team696.robot.commands.ShiftHigh;
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
	
	Button shootButton = new JoystickButton(arduino, 7);
	
//	Button highShootingSpeedButton = new JoystickAnalogButton(arduino, 3, 0.7, 1.1);
//	Button noShootingSpeedButton = new JoystickAnalogButton(arduino, 3, -0.5, 0.5);
//	Button lowShootingSpeedButton = new JoystickAnalogButton(arduino, 3, -1.1, -0.7);
	Button setShootingSpeedButton = new JoystickButton(arduino, 11);
	
	Button intakeSpeedButton = new JoystickAnalogButton(arduino, 2, -1.1, -0.7);
	Button noSpeedButton = new JoystickAnalogButton(arduino, 2, -0.5, 0.5);
	Button outtakeSpeedButton = new JoystickAnalogButton(arduino, 2, 0.7, 1.1);
	
	Button telescopingArmFullPosButton = new JoystickButton(arduino, 9);
	Button telescopingArmMidPosButton = new JoystickButton(arduino, 10);
	Button telescopingArmFullRetractButton = new JoystickButton(arduino, 8);
	
	Button setTelescopingAngle = new JoystickButton(arduino, 3);
	
	Button zeroAllEncodersButton = new JoystickButton(wheel, 7);
	
	Button enableEncoderButton = new JoystickButton(wheel, 10);
	Button disableEncoderButton = new JoystickButton(wheel, 9);
	
	public OI() {
		enableEncoderButton.whenPressed(new SetUseEncoder(true));
		disableEncoderButton.whenPressed(new SetUseEncoder(false));
		
		fastTurn.whenPressed(new FastTurn(true));
		fastTurn.whenReleased(new FastTurn(false));
		
		shiftHighButton.whenPressed(new ShiftHigh(true));
		shiftLowButton.whenPressed(new ShiftHigh(false));
		
		pivotUpButton.whileHeld(new SetPivot(true, 1));
		pivotDownButton.whileHeld(new SetPivot(true, -1));
		pivotUpButton.whenInactive(new SetPivot(true, 0));
		pivotDownButton.whenInactive(new SetPivot(true, 0));
		
		shootButton.whenPressed(new Shoot());
		
		
//		shootingSpeedButton.whenPressed(new SetShooterSpeed(4500));
//		intakeSpeedButton.whenPressed(new SetShooterSpeed(-3500));
//		stopWheelButton.whenPressed(new SetShooterSpeed(0));
		
		
		setShootingSpeedButton.whileHeld(new SetShooterSpeed(true, false, arduino.getRawAxis(3)));
		setShootingSpeedButton.whenReleased(new SetShooterSpeed(false, false, 0));
		
		intakeSpeedButton.whenPressed(new SetShooterSpeed(false, false, -0.75));
		outtakeSpeedButton.whenPressed(new SetShooterSpeed(false, false, 0.75));
		noSpeedButton.whenPressed(new SetShooterSpeed(false, false, 0));
		
//		intakePivotArmPosButton.whenPressed(new SetPivot(false, arduino.getRawAxis(1)));
//		shootPivotArmPosButton.whenPressed(new SetPivot(false, 160));
		
		telescopingArmFullPosButton.whenPressed(new SetTelescopingArm(2));
		telescopingArmMidPosButton.whenPressed(new SetTelescopingArm(1));
		
		setTelescopingAngle.whenPressed(new SetPivot(false, arduino.getRawAxis(0)));
		
		zeroAllEncodersButton.whenPressed(new ZeroEncoders());
		
	}
}


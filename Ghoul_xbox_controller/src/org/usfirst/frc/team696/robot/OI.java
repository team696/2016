
package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.FastTurn;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.SetTelescopingArm;
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
	public Joystick xbox = new Joystick(0);
//	public Joystick arduino = new Joystick(1);
//	public Joystick wheel = new Joystick(2);
//	Button fastTurn = new JoystickButton(wheel, 6);
	
	Button shiftHighButton = new JoystickButton(xbox, 2);
	Button shiftLowButton = new JoystickButton(xbox, 1);
	
//	Button pivotUpButton = new JoystickButton(xbox, 5);
//	Button pivotDownButton = new JoystickButton(xbox, 6);
//	Button pivotPresetButton = new JoystickButton(arduino, 3);
	
//	Button shootButton = new JoystickButton(xbox, 1);
	
//	Button highShootingSpeedButton = new JoystickAnalogButton(arduino, 3, 0.7, 1.1);
//	Button noShootingSpeedButton = new JoystickAnalogButton(arduino, 3, -0.5, 0.5);
//	Button lowShootingSpeedButton = new JoystickAnalogButton(arduino, 3, -1.1, -0.7);
//	Button setShootingSpeedButton = new JoystickButton(arduino, 11);
	
//	Button intakeSpeedButton = new JoystickButton(xbox, 1);
//	Button noSpeedButton = new JoystickAnalogButton(arduino, 2, -0.5, 0.5);
//	Button outtakeSpeedButton = new JoystickButton(xbox, 2);
//	Button portcullisShooterSpeedButton = new JoystickButton(arduino, 8);
	
//	Button telescopingArmFullPosButton = new JoystickButton(arduino, 5);
//	Button telescopingArmMidPosButton = new JoystickButton(arduino, 6);
//	Button telescopingArmFullRetractButton = new JoystickButton(arduino, 4);
//	Button manualRetract = new JoystickButton(arduino, 4);
//	Button manualExtend = new JoystickButton(arduino, 5);
	
//	Button zeroAllEncodersButton = new JoystickButton(wheel, 7);
	
	public OI() {
//		fastTurn.whenPressed(new FastTurn(true));
//		fastTurn.whenReleased(new FastTurn(false));
		
		shiftHighButton.whenPressed(new ShiftHigh(true));
		shiftLowButton.whenPressed(new ShiftHigh(false));
		
//		pivotUpButton.whileHeld(new SetPivot(2));
//		pivotDownButton.whileHeld(new SetPivot(-2));
//		pivotUpButton.whenInactive(new SetPivot(true));
//		pivotDownButton.whenInactive(new SetPivot(true));
//		pivotPresetButton.whenPressed(new SetPivot());
		
//		shootButton.whenPressed(new Shoot());
		
		
//		setShootingSpeedButton.whileHeld(new SetShooterSpeed(4500));
//		setShootingSpeedButton.whileHeld(new SetShooterSpeed(true, true, arduino.getRawAxis(3)));
//		setShootingSpeedButton.whileHeld(new SetShooterSpeed(true, false, arduino.getRawAxis(3)));
//		setShootingSpeedButton.whenReleased(new SetShooterSpeed(false, false, 0));
//		
//		intakeSpeedButton.whenPressed(new SetShooterSpeed(false, false, -0.9));
//		outtakeSpeedButton.whenPressed(new SetShooterSpeed(false, false, 0.9));
//		intakeSpeedButton.whenReleased(new SetShooterSpeed(false, false, 0));
//		outtakeSpeedButton.whenPressed(new SetShooterSpeed(false, false, 0));
//		noSpeedButton.whenPressed(new SetShooterSpeed(false, false, 0));
//		portcullisShooterSpeedButton.whenPressed(new SetShooterSpeed(false, 0.9, -0.9));
//		portcullisShooterSpeedButton.whenReleased(new SetShooterSpeed(false, 0, 0));
		
//		intakePivotArmPosButton.whenPressed(new SetPivot(false, arduino.getRawAxis(1)));
//		shootPivotArmPosButton.whenPressed(new SetPivot(false, 160));
		
//		telescopingArmFullPosButton.whenPressed(new SetTelescopingArm(2));
//		telescopingArmMidPosButton.whenPressed(new SetTelescopingArm(1));
//		telescopingArmFullRetractButton.whenPressed(new SetTelescopingArm(0));
//		manualExtend.whenPressed(new SetTelescopingArm(true, 1));
//		manualExtend.whenReleased(new SetTelescopingArm(true, 0));
//		manualRetract.whenPressed(new SetTelescopingArm(true, -40));
//		manualRetract.whenReleased(new SetTelescopingArm(true, 0));
		
//		zeroAllEncodersButton.whenPressed(new ZeroEncoders());
		
	}
}


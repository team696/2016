
package org.usfirst.frc.team696.robot;

import org.usfirst.frc.team696.robot.commands.FastTurn;
import org.usfirst.frc.team696.robot.commands.SafeMode;
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
	public Joystick arduino = new Joystick(1);
	public Joystick wheel = new Joystick(2);
	Button fastTurn = new JoystickButton(wheel, 6);
	
	Button shiftHighButton = new JoystickButton(wheel, 3);
	Button shiftLowButton = new JoystickButton(wheel, 2);
	
	Button pivotUpButton = new JoystickAnalogButton(arduino, 1, -1.1, -0.7);
	Button pivotDownButton = new JoystickAnalogButton(arduino, 1, 0.7, 1.1);
	
	Button shootButton = new JoystickButton(arduino, 7);
	
	Button intakeSpeedButton = new JoystickAnalogButton(arduino, 2, -1.1, -0.7);
	Button noSpeedButton = new JoystickAnalogButton(arduino, 2, -0.5, 0.5);
	Button outtakeSpeedButton = new JoystickAnalogButton(arduino, 2, 0.7, 1.1);
	Button portcullisShooterSpeedButton = new JoystickButton(arduino, 8);
	
	Button telescopingArmFullPosButton = new JoystickButton(arduino, 5);
	Button telescopingArmMidPosButton = new JoystickButton(arduino, 6);
	Button manualRetract = new JoystickButton(arduino, 4);
	
	Button shootingAngle = new JoystickButton(arduino, 9);
	Button climbingAngle = new JoystickButton(arduino, 10);
	Button zeroAngle = new JoystickButton(arduino, 11);
	Button generalDriving = new JoystickButton(arduino, 8);
	
	Button disableSafeMode = new JoystickButton(wheel, 9);
//	Button enableSafeMode = new JoystickButton(wheel, 10);
	
	Button zeroAllEncodersButton = new JoystickButton(wheel, 7);
	
	public OI() {
		fastTurn.whenPressed(new FastTurn(true));
		fastTurn.whenReleased(new FastTurn(false));
		
		shiftHighButton.whenPressed(new ShiftHigh(true));
		shiftLowButton.whenPressed(new ShiftHigh(false));
		
		pivotUpButton.whileHeld(new SetPivot(true, +1));
		pivotDownButton.whileHeld(new SetPivot(true, -1));
		pivotUpButton.whenReleased(new SetPivot(true));
		pivotDownButton.whenReleased(new SetPivot(true));
		
//		enableSafeMode.whenPressed(new SafeMode(true));
		disableSafeMode.whenPressed(new SafeMode(false));
		
		shootingAngle.whenPressed(new SetPivot(228, .7));
		climbingAngle.whenPressed(new SetPivot(340, .7));
		zeroAngle.whenPressed(new SetPivot(11, .7));
		zeroAngle.whenReleased(new SetPivot(11, .7));
		generalDriving.whenPressed(new SetPivot(100, 0.7));
		
		shootButton.whenPressed(new Shoot());
		
		intakeSpeedButton.whenPressed(new SetShooterSpeed(false, false, -0.9));
		outtakeSpeedButton.whenPressed(new SetShooterSpeed(false, false, 0.9));
		noSpeedButton.whenPressed(new SetShooterSpeed(false, false, 0));
		portcullisShooterSpeedButton.whenPressed(new SetShooterSpeed(false, 0.9, -0.9));
		portcullisShooterSpeedButton.whenReleased(new SetShooterSpeed(false, 0, 0));
		
		telescopingArmFullPosButton.whenPressed(new SetTelescopingArm(2));
		telescopingArmMidPosButton.whenPressed(new SetTelescopingArm(1));
		manualRetract.whenPressed(new SetTelescopingArm(true, -100));
//		manualRetract.whenReleased(new SetTelescopingArm(true, 0));
		
		
		zeroAllEncodersButton.whenPressed(new ZeroEncoders());
		
	}
}


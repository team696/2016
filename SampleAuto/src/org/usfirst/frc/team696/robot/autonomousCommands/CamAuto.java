package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team696.robot.commands.zeroPivot;
import org.usfirst.frc.team696.robot.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CamAuto extends CommandGroup {
    
    public  CamAuto() {
    	addSequential(new SetPivot(false, 0));
     	addParallel(new Drive(Robot.navX.getYaw(), 0.5, 1));
     	addSequential(new SetShooterSpeed(false, false, -0.75), 2);
     	addParallel(new SetShooterSpeed(false, false, 0));
     	addSequential(new Drive(Robot.navX.getYaw(), 1, -25));
     	//reorient itself
     	addSequential(new Drive(Robot.navX.getYaw()-180, 1, -3));
    	addParallel(new SetPivot(false, 5));
    	// 5  needs to be a preset for shooting in auto
    	addSequential(new AutoAdjust());
    	addSequential(new Shoot());
    	/*
    	 * PSEUDOCODE for auto
    	 * Set pivot false, zeroPivot
    	 * Drive through low bar
    	 * if area from cam is less than area of goal, keep moving forward and turn right by some degrees (measure with navx)
    	 * once the area is an acceptable number, catch the centerX and centerY
    	 * shoot, with whatever shooter speed is desired
    	 */
    }
}

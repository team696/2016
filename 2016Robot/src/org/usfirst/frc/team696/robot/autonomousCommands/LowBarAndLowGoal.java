package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team696.robot.commands.ShiftHigh;
import org.usfirst.frc.team696.robot.commands.Shoot;
import org.usfirst.frc.team696.robot.commands.ZeroPivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAndLowGoal extends CommandGroup {
    
    public  LowBarAndLowGoal() {
    	addParallel(new ShiftHigh(true));
    	addSequential(new ZeroPivot());
    	addSequential(new Drive(0, 16.25, 0.65));
    	
    	addSequential(new ShiftHigh(false));
    	addSequential(new Drive(0,0,0));
    	addSequential(new ShiftHigh(true));
    	
    	addSequential(new Drive(60, 7.5, 0.65));
    	
    	addSequential(new ShiftHigh(false));
    	addParallel(new SetShooterSpeed(false, 0.9, 0.9), 1);
    	addSequential(new Shoot());
    	addSequential(new SetShooterSpeed(false, 0, 0));
    	
    	addSequential(new ShiftHigh(true));
    	addSequential(new Drive(0, -5.25, 0.65));
    	
    	addSequential(new ShiftHigh(false));
    	addSequential(new Drive(0,0,0));
    	addSequential(new ShiftHigh(true));
    	
    	addSequential(new Drive(-58, -15, 0.65));
    	addSequential(new Drive(160, 0, 0));
//    	addSequential(new Drive(0, 11, 0.65));
    }
}


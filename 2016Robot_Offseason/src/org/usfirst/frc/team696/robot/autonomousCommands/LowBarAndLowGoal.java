package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team696.robot.commands.ShiftHigh;
import org.usfirst.frc.team696.robot.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAndLowGoal extends CommandGroup {
    
    public  LowBarAndLowGoal() {
    	//ensure shifted high
    	addParallel(new ShiftHigh(true));
    	//zero the pivot
    	addSequential(new SetPivot(11, .7));
    	//drive forward under low bar
    	addSequential(new Drive(0, 16.25, 0.65));
    	
    	//stopping sequence
    	addSequential(new ShiftHigh(false));
    	addSequential(new Drive(0,0,0));
    	addSequential(new ShiftHigh(true));
    	
    	//turn while driving forward onto batter
    	addSequential(new Drive(65, 9, 0.65));
    	
    	//shooting sequence
    	addSequential(new ShiftHigh(false));
    	addParallel(new SetShooterSpeed(false, 0.9, 0.9), 1);
    	addSequential(new Shoot());
    	addSequential(new SetShooterSpeed(false, 0, 0));
    	
    	//back up off batter
    	addSequential(new ShiftHigh(true));
    	addSequential(new Drive(0, -7.3, 0.65));
    	
    	//stopping sequence
    	addSequential(new ShiftHigh(false));
    	addSequential(new Drive(0,0,0));
    	addSequential(new ShiftHigh(true));
    	
    	//return back under low bar
    	addSequential(new Drive(-63, -15, 0.65));
    	//turn around at end 
    	addSequential(new Drive(120, 0, 0));
//    	addSequential(new Drive(0, 11, 0.65));
    }
}


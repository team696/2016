package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.ShiftHigh;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraightForward extends CommandGroup {
    
    public  DriveStraightForward() {
    	addParallel(new SetPivot(45, 0.2));
    	addSequential(new ShiftHigh(false));
    	addSequential(new Drive(0, 50, 0.75));
    }
}

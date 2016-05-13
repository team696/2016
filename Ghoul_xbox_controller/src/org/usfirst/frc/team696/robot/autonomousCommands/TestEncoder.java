package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.ShiftHigh;
import org.usfirst.frc.team696.robot.subsystems.PivotArmSystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestEncoder extends CommandGroup {
    
    public  TestEncoder() {
//    	addParallel(new SetPivot(value));
    	addSequential(new ShiftHigh(true));
//    	addSequential(new Drive(Robot.navX.getYaw(), 0, 0));
//    	addSequential(new ShiftHigh(false));
    	addSequential(new Drive(0, -5, 0.5));
    	addSequential(new Drive(0,5, 0.5));
    	addSequential(new ShiftHigh(false));
    	addSequential(new Drive(0,0,0.3));
    }
}

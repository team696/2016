package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.ShiftHigh;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraightBackward extends CommandGroup {
    
    public  DriveStraightBackward() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
//    	addParallel(new SetPivot(210));
    	addSequential(new ShiftHigh(false));
    	addSequential(new Drive(Robot.navX.getYaw(), -50, 0.75));
    }
}

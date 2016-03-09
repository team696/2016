package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.robot.commands.Drive;
import org.usfirst.frc.team696.robot.commands.SetPivot;
import org.usfirst.frc.team696.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team696.robot.commands.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAutoPickUp extends CommandGroup {
    
    public  LowBarAutoPickUp() {
    	addSequential(new SetPivot(0));
    	addParallel(new Drive(Robot.navX.getYaw(), 0.5, 1));
    	addSequential(new SetShooterSpeed(false, false, -0.75), 2);
    	addParallel(new SetShooterSpeed(false, false, 0));
//    	addSequential(new Drive(Robot.navX.getYaw(), 1, -25));
    	addParallel(new SetPivot(169));
//    	addSequential(new Drive(Robot.navX.getYaw() - 120, 0.5, 0));
    	addSequential(new SetShooterSpeed(false, false, 1));
    	addSequential(new Shoot(), 2);
    	addSequential(new SetShooterSpeed(false, false, 0));
    	
    }
}

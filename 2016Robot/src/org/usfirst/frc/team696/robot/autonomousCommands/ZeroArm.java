package org.usfirst.frc.team696.robot.autonomousCommands;

import org.usfirst.frc.team696.robot.commands.ZeroPivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ZeroArm extends CommandGroup {
    
    public  ZeroArm() {
    	addSequential(new ZeroPivot());
    	
    }
}

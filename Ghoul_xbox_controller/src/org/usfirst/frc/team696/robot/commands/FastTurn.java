package org.usfirst.frc.team696.robot.commands;


import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FastTurn extends Command {

    public FastTurn(boolean startFastTurn) {
        Robot.fastTurn = startFastTurn;
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

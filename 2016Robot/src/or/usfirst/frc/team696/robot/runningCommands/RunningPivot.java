package or.usfirst.frc.team696.robot.runningCommands;

import org.usfirst.frc.team696.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunningPivot extends Command {

    public RunningPivot() {
    	requires(Robot.pivotArm);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.pivotArm.setTargetAngle(Robot.targetAngle);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

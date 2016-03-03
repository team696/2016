package or.usfirst.frc.team696.robot.runningCommands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.PIDControl;
import org.usfirst.frc.team696.utilities.Util;

/**
 *
 */
public class RunningTelescopingArm extends Command {
	
	double maxDistance = 900;
	double current = 0;
	double target = 0;
	double speed = 0;
	double error = 0;
	
    public RunningTelescopingArm() {
    	requires(Robot.telescopingArmSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	target = Robot.telescopingTargetDistance;
    	
    	current = Robot.telescopingEncoder.get();
    	
    	error = target - current;
    	
    	speed = 0.3 * Util.signOf(error);
    	
    	if(maxDistance < current && speed > 0 || current < 10 && speed < 0)speed = 0.05 * Util.signOf(speed);
    	
    	if(speed > 0)Robot.telescopingArmSystem.ratchet(false);
    	else Robot.telescopingArmSystem.ratchet(true);
    	
    	
    	
    	Robot.telescopingArmSystem.set(speed);
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
   
    }
    
}
package or.usfirst.frc.team696.robot.runningCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team696.robot.Robot;
import org.usfirst.frc.team696.utilities.PIDControl;
import org.usfirst.frc.team696.utilities.Util;

/**
 *
 */
public class RunningTelescopingArm extends Command {
	
	double maxDistance = 1000;
	double current = 0;
	double target = 0;
	double speed = 0;
	double oldSpeed = 0;
	double error = 0;
	Timer timer = new Timer();
	
    public RunningTelescopingArm() {
    	requires(Robot.telescopingArmSystem);
    	Robot.telescopingArmSystem.ratchet(false);
    }

    protected void initialize() {
    }

    protected void execute() {
    	target = Robot.telescopingTargetDistance;
    	
    	current = Robot.telescopingEncoder.get();
    	
    	
    	
    	error = target - current;
    	error = Util.deadZone(error, -30, 30, 0);
    	
    	speed = 1 * Util.signOf(error);

    	speed = Util.constrain(speed, -0.7, 0.7);
    	
    	if(Math.abs(error) < 300)speed = Util.constrain(speed, -0.5, 0.5);
    	
    	if(Math.abs(error) < 80)speed = Util.constrain(speed, -0.2, 0.2);
    	
    	if(maxDistance < current && speed > 0 || current < 10 && speed < 0)speed = 0;
    	
    	if(error == 0)speed = 0;
    	
    	if(speed < 0){
    		Robot.telescopingArmSystem.ratchet(true);
    		Robot.pivotArm.ratchet(true);
    	}else if(speed > 0){
    		Robot.telescopingArmSystem.ratchet(false);
    		Robot.pivotArm.ratchet(false);
    	}else{
    		Robot.telescopingArmSystem.ratchet(true);
    	}
    	
    	if(!Robot.telescopingArmSystem.getRatchet() && speed > 0)speed = 0;
    	
    	Robot.telescopingArmSystem.set(speed);
    	
    	oldSpeed = speed;
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
   
    }
    
}